package me.mahdiyar.digipay.payment.service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.mahdiyar.digipay.auth.service.base.domain.BaseUserCredential;
import me.mahdiyar.digipay.base.util.IpUtils;
import me.mahdiyar.digipay.payment.contract.domain.enums.PaymentProvider;
import me.mahdiyar.digipay.payment.contract.domain.request.DoPaymentRequestDto;
import me.mahdiyar.digipay.payment.contract.domain.response.DoPaymentResponseDto;
import me.mahdiyar.digipay.payment.service.domain.PaymentEntity;
import me.mahdiyar.digipay.payment.service.domain.PaymentHistoryEntity;
import me.mahdiyar.digipay.payment.service.repository.PaymentHistoryRepository;
import me.mahdiyar.digipay.payment.service.repository.PaymentRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentHistoryRepository paymentHistoryRepository;

    public DoPaymentResponseDto doPayment(
            BaseUserCredential baseUserCredential, DoPaymentRequestDto requestDto, HttpServletRequest servletRequest) {
        PaymentEntity paymentEntity = initiateRequest(baseUserCredential, requestDto, servletRequest);
        try {

        } catch (Exception ex) {

        } finally {
            finalizePayment(paymentEntity);
        }
        return null;
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
