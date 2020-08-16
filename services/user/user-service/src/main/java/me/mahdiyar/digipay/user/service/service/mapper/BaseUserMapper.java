package me.mahdiyar.digipay.user.service.service.mapper;

import me.mahdiyar.digipay.user.service.contract.domain.BaseUser;
import me.mahdiyar.digipay.user.service.domain.UserEntity;

public class BaseUserMapper {
    private BaseUserMapper() {
    }

    public static BaseUser map(UserEntity userEntity) {
        return new BaseUser(userEntity.getId(),
                userEntity.getUsername());
    }
}
