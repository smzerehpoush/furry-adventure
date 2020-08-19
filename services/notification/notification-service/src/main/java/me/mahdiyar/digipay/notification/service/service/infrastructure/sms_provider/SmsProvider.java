package me.mahdiyar.digipay.notification.service.service.infrastructure.sms_provider;

import lombok.RequiredArgsConstructor;
import me.mahdiyar.digipay.notification.contract.domain.request.Notification;
import me.mahdiyar.digipay.notification.service.client.SmsProviderClient;
import me.mahdiyar.digipay.notification.service.service.infrastructure.NotificationProvider;
import me.mahdiyar.digipay.notification.service.service.infrastructure.sms_provider.model.NotificationRequestDto;
import org.springframework.stereotype.Component;

/**
 * @author Seyyed Mahdiyar Zerehpoush
 */
@Component
@RequiredArgsConstructor
public class SmsProvider implements NotificationProvider {
    private final SmsProviderClient client;

    @Override
    public void sendNotification(Notification notification) {
        NotificationRequestDto requestDto = buildRequest(notification);
        client.sendSms(requestDto);
    }

    private NotificationRequestDto buildRequest(Notification notification) {
        return new NotificationRequestDto()
                .setMsg(notification.getMessage())
                .setTarget("0" + notification.getMobileNo());
    }
}
