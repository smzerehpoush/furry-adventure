package me.mahdiyar.digipay.notification.service.service.infrastructure;

import me.mahdiyar.digipay.notification.contract.domain.request.Notification;

/**
 * @author Seyyed Mahdiyar Zerehpoush
 */
public interface NotificationProvider {
    void sendNotification(Notification notification);
}
