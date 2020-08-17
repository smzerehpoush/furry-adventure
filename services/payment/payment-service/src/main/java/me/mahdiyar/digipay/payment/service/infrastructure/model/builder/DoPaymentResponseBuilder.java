package me.mahdiyar.digipay.payment.service.infrastructure.model.builder;

import me.mahdiyar.digipay.payment.contract.domain.response.DoPaymentResponseDto;
import me.mahdiyar.digipay.payment.service.domain.PaymentEntity;
import me.mahdiyar.digipay.payment.service.infrastructure.model.dto.response.BasePaymentResponseDto;

/**
 * @author Seyyed Mahdiyar Zerehpoush
 */
public class DoPaymentResponseBuilder {
    private DoPaymentResponseBuilder() {
    }

    public static DoPaymentResponseDto build(
            PaymentEntity paymentEntity, String resultMessage, BasePaymentResponseDto basePaymentResponseDto) {
        return build(paymentEntity, resultMessage)
                .setRrn(basePaymentResponseDto.getRrn());
    }

    public static DoPaymentResponseDto build(
            PaymentEntity paymentEntity, String resultMessage) {
        return new DoPaymentResponseDto()
                .setTransactionTime(paymentEntity.getCreationTime())
                .setAmount(paymentEntity.getAmount())
                .setSourceResourceType(paymentEntity.getSourceResourceType())
                .setSourceResource(paymentEntity.getSourceResource())
                .setDestResource(paymentEntity.getDestResource())
                .setDestResourceType(paymentEntity.getDestResourceType())
                .setPaymentStatus(paymentEntity.getPaymentStatus())
                .setTraceId(String.valueOf(paymentEntity.getPaymentId()))
                .setResultMessage(resultMessage);
    }
}
