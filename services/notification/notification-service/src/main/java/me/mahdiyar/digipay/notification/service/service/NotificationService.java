package me.mahdiyar.digipay.notification.service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.mahdiyar.digipay.notification.contract.domain.request.Notification;
import me.mahdiyar.digipay.notification.contract.domain.request.SendNotificationRequestDto;
import me.mahdiyar.digipay.notification.service.domain.NotificationEntity;
import me.mahdiyar.digipay.notification.service.repository.NotificationRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author Seyyed Mahdiyar Zerehpoush
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final RabbitTemplate rabbitTemplate;
    @Value("${notification-service.exchange.name}")
    public String exchangeName;

    public Void sendNotification(SendNotificationRequestDto requestDto) {
        NotificationEntity notificationEntity = notificationRepository.save(buildNotificationEntity(requestDto));
        Notification notification = buildNotification(notificationEntity);
        rabbitTemplate.convertAndSend(exchangeName, "notification.sms", notification);
        return null;
    }

    private Notification buildNotification(NotificationEntity notificationEntity) {
        return new Notification(notificationEntity.getId(),
                notificationEntity.getMobileNo(), notificationEntity.getMessage());

    }

    private NotificationEntity buildNotificationEntity(SendNotificationRequestDto requestDto) {
        return new NotificationEntity()
                .setMessage(requestDto.getMessage())
                .setMobileNo(requestDto.getMobileNo())
                .setSenderUserId(requestDto.getSenderUserId());
    }
}
