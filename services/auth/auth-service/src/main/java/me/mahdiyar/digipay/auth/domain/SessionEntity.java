package me.mahdiyar.digipay.auth.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import me.mahdiyar.base.jpa.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Null;

/**
 * @author Seyyed Mahdiyar Zerehpoush
 */
@Entity
@Table(name = "sessions")
@Getter
@NoArgsConstructor
public class SessionEntity extends BaseEntity<SessionEntity> {
    @Null
    @Column(name = "user_id")
    private String userId;

    public SessionEntity(String userId) {
        this.userId = userId;
    }
}