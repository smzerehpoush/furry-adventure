package me.mahdiyar.digipay.auth.contract.domain.user.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginRequestDto {
    private String username;
    private String password;
}
