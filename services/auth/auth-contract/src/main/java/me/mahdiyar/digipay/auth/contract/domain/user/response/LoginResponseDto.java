package me.mahdiyar.digipay.auth.contract.domain.user.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import me.mahdiyar.digipay.user.contract.domain.BaseUser;

@Data
@AllArgsConstructor
public class LoginResponseDto {
    private BaseUser user;
    private String plainToken;
}
