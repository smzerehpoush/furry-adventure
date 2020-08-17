package me.mahdiyar.digipay.payment.service.infrastructure.model.builder;

import me.mahdiyar.digipay.payment.contract.domain.PaymentMeans;
import me.mahdiyar.digipay.payment.service.domain.PaymentEntity;
import me.mahdiyar.digipay.payment.service.infrastructure.model.dto.request.BasePaymentRequestDto;

/**
 * @author Seyyed Mahdiyar Zerehpoush
 */
public class BasePaymentRequestBuilder {
    private BasePaymentRequestBuilder() {
    }

    public static BasePaymentRequestDto build(PaymentEntity paymentEntity, PaymentMeans paymentMeans) {
        return new BasePaymentRequestDto()
                .setAmount(paymentEntity.getAmount())
                .setCvv2(paymentMeans.getCvv2())
                .setExp(paymentMeans.getExp())
                .setPin(paymentMeans.getPin())
                .setSourcePan(paymentEntity.getSourceResource())
                .setDestinationPan(paymentEntity.getDestResource())
                .setTrackingNumber(String.valueOf(paymentEntity.getPaymentId()));

    }
}
