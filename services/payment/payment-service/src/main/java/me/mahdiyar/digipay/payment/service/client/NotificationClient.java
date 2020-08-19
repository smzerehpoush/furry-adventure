package me.mahdiyar.digipay.payment.service.client;

import me.mahdiyar.digipay.notification.contract.domain.request.SendNotificationRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * @author Seyyed Mahdiyar Zerehpoush
 */
@FeignClient(name = "notification-service", url = "http://localhost:8084/api/v1/notification")
public interface NotificationClient {
    @GetMapping
    void sendNotificationToUser(@RequestBody @Valid SendNotificationRequestDto requestDto);
}
