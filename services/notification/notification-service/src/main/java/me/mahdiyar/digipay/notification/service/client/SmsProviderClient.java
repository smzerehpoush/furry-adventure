package me.mahdiyar.digipay.notification.service.client;

import me.mahdiyar.digipay.notification.service.service.infrastructure.sms_provider.model.NotificationRequestDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Seyyed Mahdiyar Zerehpoush
 */
public interface SmsProviderClient {
    @PostMapping
    void sendSms(@RequestBody NotificationRequestDto requestDto);
}
