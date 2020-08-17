package me.mahdiyar.digipay.payment.service.infrastructure.model.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author mahdiyar
 */
@Data
public class BasePaymentRequestDto {
    private String sourcePan;
    private String destinationPan;
    private String cvv2;
    private String pin;
    private String exp;
    private long amount;
    private String trackingNumber;
}
