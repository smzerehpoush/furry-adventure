package me.mahdiyar.digipay.user.contract.domain;

import lombok.Data;

@Data
public class BaseUser {
    private String id;
    private String username;

    public BaseUser(String id, String username) {
        this.id = id;
        this.username = username;
    }
}
