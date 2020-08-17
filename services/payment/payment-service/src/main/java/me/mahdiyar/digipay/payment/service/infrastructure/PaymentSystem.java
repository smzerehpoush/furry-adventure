package me.mahdiyar.digipay.payment.service.infrastructure;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.mahdiyar.digipay.payment.contract.domain.PaymentMeans;
import me.mahdiyar.digipay.payment.service.domain.PaymentEntity;
import me.mahdiyar.digipay.payment.service.exceptions.PaymentException;
import me.mahdiyar.digipay.payment.service.infrastructure.model.builder.BasePaymentRequestBuilder;
import me.mahdiyar.digipay.payment.service.infrastructure.model.dto.request.BasePaymentRequestDto;
import me.mahdiyar.digipay.payment.service.infrastructure.model.dto.response.BasePaymentResponseDto;

import java.util.Date;

/**
 * @author mahdiyar
 */

@RequiredArgsConstructor
@Slf4j
public class PaymentSystem {
    private final PaymentProvider paymentProvider;

    public BasePaymentResponseDto pay(PaymentEntity paymentEntity, PaymentMeans paymentMeans) throws PaymentException {
        logger.info("trying to do a payment with id : {}, provider :{}", paymentEntity.getId(), paymentProvider);
        BasePaymentRequestDto requestDto = BasePaymentRequestBuilder.build(paymentEntity, paymentMeans);
        paymentEntity.setSwitchRequestDate(new Date());
        BasePaymentResponseDto responseDto = paymentProvider.pay(requestDto);
        logger.info("payment with id : {}, provider :{} done.", paymentEntity.getId(), paymentProvider);
        paymentEntity.setSwitchResponseDate(new Date());
        return responseDto;
    }
}
