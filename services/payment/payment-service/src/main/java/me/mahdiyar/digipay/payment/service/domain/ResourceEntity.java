package me.mahdiyar.digipay.payment.service.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import me.mahdiyar.digipay.base.jpa.BaseEntity;
import me.mahdiyar.digipay.payment.contract.domain.enums.ResourceType;

import javax.persistence.*;

@Entity
@Table(name = "resources")
@NoArgsConstructor
@Data
public class ResourceEntity extends BaseEntity {
    @Column(name = "user_id")
    private String userId;

    @Enumerated(EnumType.STRING)
    @Column(name = "resource_type")
    private ResourceType resourceType;

    @Column(name = "resource")
    private String resource;

    @Column(name = "title")
    private String title;

    @Column(name = "deleted", columnDefinition = "boolean default false")
    private boolean deleted;

}
