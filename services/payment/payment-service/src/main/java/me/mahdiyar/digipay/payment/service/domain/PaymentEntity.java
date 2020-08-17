package me.mahdiyar.digipay.payment.service.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import me.mahdiyar.digipay.base.jpa.BaseEntity;
import me.mahdiyar.digipay.payment.contract.domain.enums.PaymentProvider;
import me.mahdiyar.digipay.payment.contract.domain.enums.PaymentStatus;
import me.mahdiyar.digipay.payment.contract.domain.enums.ResourceType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "payments")
@Data
@NoArgsConstructor
public class PaymentEntity extends BaseEntity {
    @GenericGenerator(
            name = "paymentSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "seq_payment"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @GeneratedValue(generator = "paymentSequenceGenerator", strategy = GenerationType.SEQUENCE)
    @Column(name = "payment_id")
    protected Long paymentId;

    @Column(name = "amount")
    protected Long amount;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "payment_status")
    private PaymentStatus paymentStatus;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "finish_time")
    protected Date finishTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "switch_req_date")
    protected Date switchRequestDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "switch_res_date")
    protected Date switchResponseDate;

    @Column(name = "switch_result_code")
    protected Long switchResultCode;

    @Column(name = "switch_result_message")
    protected String switchResultMessage;

    @Column(name = "rrn")
    private String rrn;

    @Column(name = "trace_id")
    private String traceId;

    @Column(name = "ip")
    private String ip;

    @Column(name = "under_process")
    private boolean underProcess;

    @Column(name = "session_id")
    private String sessionId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "source_resource_id")
    private String sourceResourceId;

    @Column(name = "source_resource")
    private String sourceResource;

    @Enumerated(EnumType.STRING)
    @Column(name = "source_resource_type")
    private ResourceType sourceResourceType;

    @Column(name = "dest_resource")
    private String destResource;

    @Enumerated(EnumType.STRING)
    @Column(name = "dest_resource_type")
    private ResourceType destResourceType;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_provider")
    private PaymentProvider paymentProvider;
}
