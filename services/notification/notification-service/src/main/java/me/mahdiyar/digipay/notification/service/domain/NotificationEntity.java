package me.mahdiyar.digipay.notification.service.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import me.mahdiyar.digipay.base.jpa.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Seyyed Mahdiyar Zerehpoush
 */
@Entity
@Table(name = "notifications")
@NoArgsConstructor
@Data
public class NotificationEntity extends BaseEntity {
    @Column(name = "sender_user_id")
    private String senderUserId;
    @Column(name = "mobile_no")
    private Long mobileNo;
    @Column(name = "message")
    private String message;
}
