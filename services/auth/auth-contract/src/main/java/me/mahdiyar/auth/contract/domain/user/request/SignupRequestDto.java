package me.mahdiyar.auth.contract.domain.user.request;

import lombok.Data;

@Data
public class SignupRequestDto {
    private String username;
    private String password;
}
