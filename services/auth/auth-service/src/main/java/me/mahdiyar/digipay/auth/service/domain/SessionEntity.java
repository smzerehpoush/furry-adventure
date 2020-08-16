package me.mahdiyar.digipay.auth.service.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import me.mahdiyar.digipay.base.jpa.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Null;

/**
 * @author Seyyed Mahdiyar Zerehpoush
 */
@Entity
@Table(name = "sessions")
@Data
@NoArgsConstructor
public class SessionEntity extends BaseEntity {
    @Null
    @Column(name = "user_id")
    private String userId;

    public SessionEntity(String userId) {
        this.userId = userId;
    }
}