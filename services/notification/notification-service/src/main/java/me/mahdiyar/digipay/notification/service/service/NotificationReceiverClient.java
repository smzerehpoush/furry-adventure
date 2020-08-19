package me.mahdiyar.digipay.notification.service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.mahdiyar.digipay.notification.contract.domain.request.Notification;
import me.mahdiyar.digipay.notification.service.service.infrastructure.NotificationProvider;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @author Seyyed Mahdiyar Zerehpoush
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationReceiverClient {
    private final NotificationProvider notificationProvider;

    @RabbitListener(queues = "${notification-service.queue.name}")
    public void receiveMessage(final Notification notification) {
        logger.info("received notification from queue : {}", notification);
        notificationProvider.sendNotification(notification);
    }
}
