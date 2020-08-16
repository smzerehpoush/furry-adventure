package me.mahdiyar.digipay.user.service.mapper;

import me.mahdiyar.digipay.user.domain.UserEntity;
import me.mahdiyar.digipay.user.contract.domain.User;

public class UserMapper {
    private UserMapper() {
    }

    public static User map(UserEntity userEntity) {
        return new User(userEntity.getId(),
                userEntity.getUsername(),
                userEntity.getHashedPassword(),
                userEntity.getCreationTime());
    }
}
