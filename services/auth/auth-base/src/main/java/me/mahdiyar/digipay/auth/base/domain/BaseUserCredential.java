package me.mahdiyar.digipay.auth.base.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseUserCredential {
    private String userId;
    private String sessionId;
}
