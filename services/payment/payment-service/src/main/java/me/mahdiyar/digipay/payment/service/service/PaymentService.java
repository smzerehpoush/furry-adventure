package me.mahdiyar.digipay.payment.service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.mahdiyar.digipay.auth.service.base.domain.BaseUserCredential;
import me.mahdiyar.digipay.base.util.IpUtils;
import me.mahdiyar.digipay.payment.contract.domain.enums.PaymentProvider;
import me.mahdiyar.digipay.payment.contract.domain.enums.PaymentStatus;
import me.mahdiyar.digipay.payment.contract.domain.request.DoPaymentRequestDto;
import me.mahdiyar.digipay.payment.contract.domain.response.DoPaymentResponseDto;
import me.mahdiyar.digipay.payment.service.domain.PaymentEntity;
import me.mahdiyar.digipay.payment.service.domain.PaymentHistoryEntity;
import me.mahdiyar.digipay.payment.service.exceptions.PaymentException;
import me.mahdiyar.digipay.payment.service.infrastructure.PaymentSystem;
import me.mahdiyar.digipay.payment.service.infrastructure.model.builder.DoPaymentResponseBuilder;
import me.mahdiyar.digipay.payment.service.infrastructure.model.dto.response.BasePaymentResponseDto;
import me.mahdiyar.digipay.payment.service.repository.PaymentHistoryRepository;
import me.mahdiyar.digipay.payment.service.repository.PaymentRepository;
import me.mahdiyar.digipay.payment.service.service.payment_provider1.PaymentProvider1;
import me.mahdiyar.digipay.payment.service.service.payment_provider2.PaymentProvider2;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentHistoryRepository paymentHistoryRepository;
    private final PaymentProvider1 paymentProvider1;
    private final PaymentProvider2 paymentProvider2;

    public DoPaymentResponseDto doPayment(
            BaseUserCredential baseUserCredential, DoPaymentRequestDto requestDto, HttpServletRequest servletRequest) {
        PaymentEntity paymentEntity = initiateRequest(baseUserCredential, requestDto, servletRequest);
        final PaymentSystem paymentSystem = buildPaymentService(paymentEntity.getPaymentProvider());
        try {
            logger.info("trying to do a payment with id :{}", paymentEntity.getId());
            BasePaymentResponseDto responseDto = paymentSystem.pay(paymentEntity, requestDto.getPaymentMeans());
            logger.info("payment done with id :{}", paymentEntity.getId());
            return DoPaymentResponseBuilder.build(paymentEntity, "OK", responseDto);
        } catch (PaymentException ex) {
            if (ex.isUnknownState()) {
                unknownPaymentStatus(paymentEntity);
            } else {
                failedPaymentStatus(paymentEntity);
            }
            return DoPaymentResponseBuilder.build(paymentEntity, ex.getErrorMessage());
        } catch (Exception ex) {
            failedPaymentStatus(paymentEntity);
            return DoPaymentResponseBuilder.build(paymentEntity, "Failed");
        } finally {
            finalizePayment(paymentEntity);
        }
    }

    private void failedPaymentStatus(PaymentEntity paymentEntity) {
        logger.info("set payment status to FAILED with id : {}", paymentEntity.getId());
        paymentEntity.setPaymentStatus(PaymentStatus.FAILED);
        logPayment(paymentEntity);
    }

    private void unknownPaymentStatus(PaymentEntity paymentEntity) {
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
            BaseUserCredential baseUserCredential, DoPaymentRequestDto requestDto, HttpServletRequest servletRequest) {
        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setUnderProcess(true);
        paymentEntity.setUserId(baseUserCredential.getUserId());
        paymentEntity.setSessionId(baseUserCredential.getSessionId());
        paymentEntity.setAmount(requestDto.getAmount());
        paymentEntity.setSourceResource(requestDto.getSourceResource());
        paymentEntity.setSourceResourceType(requestDto.getSourceResourceType());
        paymentEntity.setDestResource(requestDto.getDestResource());
        paymentEntity.setDestResourceType(requestDto.getDestResourceType());
        paymentEntity.setIp(IpUtils.getRequestIpAddress(servletRequest));
        logger.info("tryihng to initiate payment request : {}", paymentEntity);
        paymentEntity.setPaymentProvider(findPaymentProvider(requestDto.getSourceResource()));
        return paymentRepository.save(paymentEntity);
    }

    private PaymentProvider findPaymentProvider(String sourceResource) {
        if (!StringUtils.isEmpty(sourceResource) && sourceResource.startsWith("6037"))
            return PaymentProvider.TYPE1;
        else return PaymentProvider.TYPE2;
    }

    private void finalizePayment(PaymentEntity paymentEntity) {
        paymentEntity.setUnderProcess(false);
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
