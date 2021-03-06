package me.mahdiyar.digipay.user.service.contract.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User extends BaseUser {
    private String hashedPassword;
    private Long mobileNo;
    private LocalDateTime creationTime;

    public User(String id, String username, String hashedPassword, LocalDateTime creationTime, Long mobileNo) {
        super(id, username);
        this.hashedPassword = hashedPassword;
        this.creationTime = creationTime;
        this.mobileNo = mobileNo;
    }
}
