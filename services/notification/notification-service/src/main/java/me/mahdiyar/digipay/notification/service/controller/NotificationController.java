package me.mahdiyar.digipay.notification.service.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import me.mahdiyar.digipay.notification.contract.domain.request.SendNotificationRequestDto;
import me.mahdiyar.digipay.notification.service.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.ResponseEntity.ok;

/**
 * @author mahdiyar
 */
@Api
@RestController
@RequestMapping("api/v1/notification")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    @PostMapping
    public ResponseEntity<Void> sendNotification(@RequestBody @Valid SendNotificationRequestDto requestDto) {
        return ok(notificationService.sendNotification(requestDto));
    }
}
