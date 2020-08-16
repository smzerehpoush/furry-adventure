package me.mahdiyar.digipay.auth.service.contract.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import me.mahdiyar.digipay.user.service.contract.domain.BaseUser;

@Data
@AllArgsConstructor
public class LoginResponseDto {
    private BaseUser user;
    private String plainToken;
}
