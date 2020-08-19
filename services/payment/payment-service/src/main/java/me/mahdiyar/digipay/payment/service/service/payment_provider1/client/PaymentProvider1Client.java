package me.mahdiyar.digipay.payment.service.service.payment_provider1.client;

import me.mahdiyar.digipay.payment.service.infrastructure.model.dto.response.BasePaymentResponseDto;
import me.mahdiyar.digipay.payment.service.service.payment_provider1.model.dto.request.PaymentRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Seyyed Mahdiyar Zerehpoush
 */
@FeignClient(name = "payment-provider1")
public interface PaymentProvider1Client {
    @PostMapping
    BasePaymentResponseDto doPayment(@RequestBody PaymentRequestDto requestDto);
}
