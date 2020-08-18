package me.mahdiyar.digipay.payment.service.service;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.mahdiyar.digipay.auth.service.base.domain.BaseUserCredential;
import me.mahdiyar.digipay.base.util.IpUtils;
import me.mahdiyar.digipay.notification.contract.domain.request.SendNotificationRequestDto;
import me.mahdiyar.digipay.payment.contract.domain.enums.PaymentProvider;
import me.mahdiyar.digipay.payment.contract.domain.enums.PaymentStatus;
import me.mahdiyar.digipay.payment.contract.domain.request.DoPaymentRequestDto;
import me.mahdiyar.digipay.payment.contract.domain.response.DoPaymentResponseDto;
import me.mahdiyar.digipay.payment.service.client.NotificationClient;
import me.mahdiyar.digipay.payment.service.client.UserClient;
import me.mahdiyar.digipay.payment.service.domain.PaymentEntity;
import me.mahdiyar.digipay.payment.service.domain.PaymentHistoryEntity;
import me.mahdiyar.digipay.payment.service.domain.ResourceEntity;
import me.mahdiyar.digipay.payment.service.exceptions.PaymentException;
import me.mahdiyar.digipay.payment.service.exceptions.ResourceNotFoundException;
import me.mahdiyar.digipay.payment.service.infrastructure.PaymentSystem;
import me.mahdiyar.digipay.payment.service.infrastructure.model.builder.DoPaymentResponseBuilder;
import me.mahdiyar.digipay.payment.service.infrastructure.model.dto.response.BasePaymentResponseDto;
import me.mahdiyar.digipay.payment.service.repository.PaymentHistoryRepository;
import me.mahdiyar.digipay.payment.service.repository.PaymentRepository;
import me.mahdiyar.digipay.payment.service.service.payment_provider1.PaymentProvider1;
import me.mahdiyar.digipay.payment.service.service.payment_provider2.PaymentProvider2;
import org.springframework.context.MessageSource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {
    private final ResourceService resourceService;
    private final PaymentRepository paymentRepository;
    private final PaymentHistoryRepository paymentHistoryRepository;
    private final PaymentProvider1 paymentProvider1;
    private final PaymentProvider2 paymentProvider2;
    private final TaskExecutor notificationTaskExecutor;
    private final UserClient userClient;
    private final NotificationClient notificationClient;
    private final MessageSource messageSource;

    public DoPaymentResponseDto doPayment(
            BaseUserCredential baseUserCredential, DoPaymentRequestDto requestDto, HttpServletRequest servletRequest)
            throws ResourceNotFoundException {
        ResourceEntity resourceEntity = resourceService.findById(requestDto.getSourceResourceId());
        PaymentEntity paymentEntity = initiateRequest(baseUserCredential, requestDto, servletRequest, resourceEntity);
        final PaymentSystem paymentSystem = buildPaymentService(paymentEntity.getPaymentProvider());
        try {
            logger.info("trying to do a payment with id :{}", paymentEntity.getId());
            BasePaymentResponseDto responseDto = paymentSystem.pay(paymentEntity, requestDto.getPaymentMeans());
            logger.info("payment done with id :{}", paymentEntity.getId());
            processSwitchResponse(paymentEntity, responseDto);
            return DoPaymentResponseBuilder.build(paymentEntity, "OK", responseDto);
        } catch (PaymentException ex) {
            if (ex.isUnknownState()) {
                paymentUnknown(paymentEntity);
            } else {
                paymentFailed(paymentEntity);
            }
            return DoPaymentResponseBuilder.build(paymentEntity, ex.getErrorMessage());
        } catch (Exception ex) {
            paymentFailed(paymentEntity);
            return DoPaymentResponseBuilder.build(paymentEntity, "Failed");
        } finally {
            finalizePayment(paymentEntity);
        }
    }

    private void processSwitchResponse(PaymentEntity paymentEntity, BasePaymentResponseDto responseDto) {
        paymentEntity.setRrn(responseDto.getRrn());
        paymentEntity.setSwitchResultCode(responseDto.getResultCode());
        paymentEntity.setSwitchResultMessage(responseDto.getResultMessage());
        if (Objects.equals(0L, responseDto.getResultCode())) {
            paymentSuccess(paymentEntity);
        } else {
            paymentFailed(paymentEntity);
        }
    }

    private void paymentSuccess(PaymentEntity paymentEntity) {
        logger.info("set payment status to SUCCESS with id : {}", paymentEntity.getId());
        paymentEntity.setPaymentStatus(PaymentStatus.SUCCESSFUL);
        logPayment(paymentEntity);
        notificationTaskExecutor.execute(() -> sentNotificationToCustomer(paymentEntity));
    }

    private void sentNotificationToCustomer(PaymentEntity paymentEntity) {
        String mobileNo = findUserMobileNo(paymentEntity);
        if (mobileNo == null)
            return;
        SendNotificationRequestDto requestDto = new SendNotificationRequestDto()
                .setMobileNo(mobileNo)
                .setMessage(createReceiptMessage(paymentEntity));
        notificationClient.sendNotificationToUser(requestDto);
    }

    private String createReceiptMessage(PaymentEntity paymentEntity) {
        final String messageTemplate =
                messageSource.getMessage("RECEIPT_MESSAGE_TEMPLATE", null, null,
                        Locale.getDefault());
        return messageTemplate == null ? null :
                messageTemplate.replace("{amount}", String.valueOf(paymentEntity.getAmount()))
                        .replace("{date}", paymentEntity.getCreationTime().toString());
    }

    private String findUserMobileNo(PaymentEntity paymentEntity) {
        try {
            return userClient.getUserMobileNo(paymentEntity.getUserId());
        } catch (FeignException ex) {
            logger.error("error in getting user mobile-no from user-service. user-id : {}",
                    paymentEntity.getUserId(), ex);
            return null;
        }
    }

    private void paymentFailed(PaymentEntity paymentEntity) {
        logger.info("set payment status to FAILED with id : {}", paymentEntity.getId());
        paymentEntity.setPaymentStatus(PaymentStatus.FAILED);
        logPayment(paymentEntity);
    }

    private void paymentUnknown(PaymentEntity paymentEntity) {
        logger.info("set payment status to UNKNOWN with id : {}", paymentEntity.getId());
        paymentEntity.setPaymentStatus(PaymentStatus.UNKNOWN);
        logPayment(paymentEntity);
    }

    private PaymentSystem buildPaymentService(PaymentProvider paymentProvider) {
        switch (paymentProvider) {
            case TYPE1:
                return new PaymentSystem(paymentProvider1);
            case TYPE2:
            default:
                return new PaymentSystem(paymentProvider2);
        }
    }

    private PaymentEntity initiateRequest(
            BaseUserCredential baseUserCredential, DoPaymentRequestDto requestDto, HttpServletRequest servletRequest,
            ResourceEntity resourceEntity) {
        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setUnderProcess(true);
        paymentEntity.setUserId(baseUserCredential.getUserId());
        paymentEntity.setSessionId(baseUserCredential.getSessionId());
        paymentEntity.setAmount(requestDto.getAmount());
        paymentEntity.setSourceResource(resourceEntity.getResource());
        paymentEntity.setSourceResourceType(resourceEntity.getResourceType());
        paymentEntity.setDestResource(requestDto.getDestResource());
        paymentEntity.setDestResourceType(requestDto.getDestResourceType());
        paymentEntity.setIp(IpUtils.getRequestIpAddress(servletRequest));
        logger.info("trying to initiate payment request : {}", paymentEntity);
        paymentEntity.setPaymentProvider(findPaymentProvider(resourceEntity.getResource()));
        return paymentRepository.save(paymentEntity);
    }

    private PaymentProvider findPaymentProvider(String sourceResource) {
        if (!StringUtils.isEmpty(sourceResource) && sourceResource.startsWith("6037"))
            return PaymentProvider.TYPE1;
        else return PaymentProvider.TYPE2;
    }

    private void finalizePayment(PaymentEntity paymentEntity) {
        paymentEntity.setUnderProcess(false);
        paymentEntity.setFinishTime(new Date());
        paymentEntity = paymentRepository.saveAndFlush(paymentEntity);
        logPayment(paymentEntity);
    }

    private void logPayment(PaymentEntity paymentEntity) {
        PaymentHistoryEntity paymentHistoryEntity = new PaymentHistoryEntity();
        paymentHistoryEntity.setPayment(paymentEntity);
        paymentHistoryEntity.setStatus(paymentEntity.getPaymentStatus());
        paymentHistoryRepository.save(paymentHistoryEntity);
    }
}
