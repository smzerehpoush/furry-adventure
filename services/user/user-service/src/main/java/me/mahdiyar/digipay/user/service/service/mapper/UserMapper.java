package me.mahdiyar.digipay.user.service.service.mapper;

import me.mahdiyar.digipay.user.service.contract.domain.User;
import me.mahdiyar.digipay.user.service.domain.UserEntity;

public class UserMapper {
    private UserMapper() {
    }

    public static User map(UserEntity userEntity) {
        return new User(userEntity.getId(),
                userEntity.getUsername(),
                userEntity.getHashedPassword(),
                userEntity.getCreationTime(),
                userEntity.getMobileNo());
    }
}
