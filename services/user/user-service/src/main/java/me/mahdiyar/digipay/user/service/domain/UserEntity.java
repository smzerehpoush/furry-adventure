package me.mahdiyar.digipay.user.service.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import me.mahdiyar.digipay.base.jpa.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {
    @Column(name = "username")
    private String username;
    @Column(name = "hashed_password")
    private String hashedPassword;
    @Column(name = "mobile_no")
    private Long mobileNo;

    public UserEntity(String username, String hashedPassword, Long mobileNo) {
        this.username = username;
        this.hashedPassword = hashedPassword;
        this.mobileNo = mobileNo;
    }
}
