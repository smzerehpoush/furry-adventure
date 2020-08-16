package me.mahdiyar.digipay.payment.service.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import me.mahdiyar.digipay.base.jpa.BaseEntity;
import me.mahdiyar.digipay.payment.contract.domain.enums.ResourceType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "resources")
@NoArgsConstructor
@Data
public class ResourceEntity extends BaseEntity {
    @GenericGenerator(
            name = "resourceSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "seq_resource"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @GeneratedValue(generator = "resourceSequenceGenerator", strategy = GenerationType.SEQUENCE)
    @Column(name = "resource_id", length = 15)
    public Long resourceId;

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
