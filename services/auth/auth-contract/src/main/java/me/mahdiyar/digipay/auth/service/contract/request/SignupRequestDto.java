package me.mahdiyar.digipay.auth.service.contract.request;

import lombok.Data;

@Data
public class SignupRequestDto {
    private String username;
    private Long mobileNo;
    private String password;
}
