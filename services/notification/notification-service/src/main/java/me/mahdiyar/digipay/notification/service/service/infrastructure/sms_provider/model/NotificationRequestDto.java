package me.mahdiyar.digipay.notification.service.service.infrastructure.sms_provider.model;

import lombok.Data;

/**
 * @author Seyyed Mahdiyar Zerehpoush
 */
@Data
public class NotificationRequestDto {
    private String msg;
    private String target;
}
