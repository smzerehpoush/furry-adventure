package me.mahdiyar.digipay.payment.contract.domain;

import lombok.Data;
import me.mahdiyar.digipay.payment.contract.domain.enums.ResourceType;

import java.time.LocalDateTime;

@Data
public class Resource {
    private String id;
    private LocalDateTime creationTime;
    private String userId;
    private ResourceType resourceType;
    private String value;
    private String title;
}
