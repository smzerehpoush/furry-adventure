package me.mahdiyar.digipay.payment.service.infrastructure.model.dto.response;

import lombok.Data;

/**
 * @author mahdiyar
 */
@Data
public class BasePaymentResponseDto {
    private Long resultCode;
    private String resultMessage;
    private String rrn;
}
