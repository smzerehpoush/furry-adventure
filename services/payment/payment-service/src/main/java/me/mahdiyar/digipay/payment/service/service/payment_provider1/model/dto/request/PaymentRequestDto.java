package me.mahdiyar.digipay.payment.service.service.payment_provider1.model.dto.request;

import lombok.Data;

/**
 * @author mahdiyar
 */
@Data
public class PaymentRequestDto {
    private String source;
    private String dest;
    private String cvv2;
    private String expDate;
    private String pin;
    private long amount;
}
