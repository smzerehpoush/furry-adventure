package me.mahdiyar.digipay.payment.service.repository;

import me.mahdiyar.digipay.base.jpa.BaseRepository;
import me.mahdiyar.digipay.payment.service.domain.PaymentEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends BaseRepository<PaymentEntity> {
}
