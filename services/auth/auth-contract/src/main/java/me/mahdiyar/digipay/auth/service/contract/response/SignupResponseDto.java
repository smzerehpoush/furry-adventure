package me.mahdiyar.digipay.auth.service.contract.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import me.mahdiyar.digipay.user.service.contract.domain.BaseUser;

@Data
@RequiredArgsConstructor
public class SignupResponseDto {
    private final BaseUser user;
    private final String plainToken;
}
