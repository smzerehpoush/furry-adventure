package me.mahdiyar.digipay.payment.service.service.payment_provider2.client;

import me.mahdiyar.digipay.payment.service.infrastructure.model.dto.response.BasePaymentResponseDto;
import me.mahdiyar.digipay.payment.service.service.payment_provider2.model.dto.request.PaymentRequestDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Seyyed Mahdiyar Zerehpoush
 */
//@FeignClient(name = "payment-provider2")
public interface PaymentProvider2Client {
    @PostMapping
    BasePaymentResponseDto doPayment(@RequestBody PaymentRequestDto requestDto);
}
