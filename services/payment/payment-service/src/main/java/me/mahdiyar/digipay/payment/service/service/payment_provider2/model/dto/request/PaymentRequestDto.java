package me.mahdiyar.digipay.payment.service.service.payment_provider2.model.dto.request;

import lombok.Data;

/**
 * @author mahdiyar
 */
@Data
public class PaymentRequestDto {
    private String source;
    private String target;
    private String cvv2;
    private String expire;
    private String pin2;
    private long amount;
}
