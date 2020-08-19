package me.mahdiyar.digipay.notification.service.repository;

import me.mahdiyar.digipay.base.jpa.BaseRepository;
import me.mahdiyar.digipay.notification.service.domain.NotificationEntity;
import org.springframework.stereotype.Repository;

/**
 * @author Seyyed Mahdiyar Zerehpoush
 */
@Repository
public interface NotificationRepository extends BaseRepository<NotificationEntity> {
}
