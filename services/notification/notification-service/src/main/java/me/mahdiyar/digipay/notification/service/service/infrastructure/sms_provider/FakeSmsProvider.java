package me.mahdiyar.digipay.notification.service.service.infrastructure.sms_provider;

import lombok.extern.slf4j.Slf4j;
import me.mahdiyar.digipay.notification.service.client.SmsProviderClient;
import me.mahdiyar.digipay.notification.service.service.infrastructure.sms_provider.model.NotificationRequestDto;
import org.springframework.stereotype.Component;

/**
 * @author Seyyed Mahdiyar Zerehpoush
 */
@Component
@Slf4j
public class FakeSmsProvider implements SmsProviderClient {
    @Override
    public void sendSms(NotificationRequestDto requestDto) {
        logger.info("trying to send a fake sms : {}", requestDto);
    }
}
