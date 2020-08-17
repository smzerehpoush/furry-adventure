package me.mahdiyar.digipay.payment.contract.domain.response;

import lombok.Data;
import me.mahdiyar.digipay.payment.contract.domain.enums.PaymentStatus;
import me.mahdiyar.digipay.payment.contract.domain.enums.ResourceType;

import java.time.LocalDateTime;

@Data
public class DoPaymentResponseDto {
    private LocalDateTime transactionTime;
    private long amount;
    private ResourceType sourceResourceType;
    private String sourceResource;
    private ResourceType destResourceType;
    private String destResource;
    private PaymentStatus paymentStatus;
    private String resultMessage;
    private String rrn;
    private String traceId;
}
