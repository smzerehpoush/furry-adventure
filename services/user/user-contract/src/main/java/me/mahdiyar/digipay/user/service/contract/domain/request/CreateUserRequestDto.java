package me.mahdiyar.digipay.user.service.contract.domain.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateUserRequestDto {
    @NotBlank
    private String username;
    @NotBlank
    private String hashedPassword;
}
