package me.mahdiyar.digipay.payment.contract.domain.request;

import lombok.Data;
import me.mahdiyar.digipay.payment.contract.domain.PaymentMeans;
import me.mahdiyar.digipay.payment.contract.domain.enums.ResourceType;

import javax.validation.constraints.NotNull;

@Data
public class DoPaymentRequestDto {
    private Long amount;
    @NotNull
    private String sourceResource;
    @NotNull
    private ResourceType sourceResourceType;
    @NotNull
    private String destResource;
    @NotNull
    private ResourceType destResourceType;
    @NotNull
    private PaymentMeans paymentMeans;
}
