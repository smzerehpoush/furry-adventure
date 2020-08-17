package me.mahdiyar.digipay.payment.service.repository;

import me.mahdiyar.digipay.payment.service.domain.PaymentHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentHistoryRepository extends JpaRepository<PaymentHistoryEntity, Long> {
}
