package me.mahdiyar.auth.contract.domain.user.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import me.mahdiyar.digipay.user.contract.domain.BaseUser;

@Data
@RequiredArgsConstructor
public class LoginResponseDto {
    private final BaseUser user;
    private final String plainToken;
}
