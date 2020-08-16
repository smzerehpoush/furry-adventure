package me.mahdiyar.digipay.payment.contract.domain.request;

import lombok.Data;
import me.mahdiyar.digipay.payment.contract.domain.enums.ResourceType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CreateResourceRequestDto {
    @NotNull
    private ResourceType resourceType;
    @NotBlank
    private String resource;
    @NotBlank
    private String title;

}
