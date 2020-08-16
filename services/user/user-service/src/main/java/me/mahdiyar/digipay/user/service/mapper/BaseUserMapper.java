package me.mahdiyar.digipay.user.service.mapper;

import me.mahdiyar.digipay.user.contract.domain.BaseUser;
import me.mahdiyar.digipay.user.domain.UserEntity;

public class BaseUserMapper {
    private BaseUserMapper() {
    }

    public static BaseUser map(UserEntity userEntity) {
        return new BaseUser(userEntity.getId(),
                userEntity.getUsername());
    }
}
