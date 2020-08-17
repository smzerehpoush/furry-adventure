package me.mahdiyar.digipay.payment.service.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import me.mahdiyar.digipay.payment.contract.domain.enums.PaymentStatus;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
@Data
@NoArgsConstructor
public class PaymentHistoryEntity {
    @Id
    @GenericGenerator(
            name = "paymentHistorySequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "seq_payment_history"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @GeneratedValue(generator = "paymentHistorySequenceGenerator", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", length = 15)
    public Long id;

    @CreationTimestamp
    @Column(name = "creation_time")
    private LocalDateTime creationTime;
    @ManyToOne(optional = false)
    @JoinColumn(name = "payment_id")
    private PaymentEntity payment;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
}
