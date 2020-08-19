package me.mahdiyar.digipay.payment.service.service.payment_provider1;

import me.mahdiyar.digipay.payment.service.infrastructure.model.dto.response.BasePaymentResponseDto;
import me.mahdiyar.digipay.payment.service.service.payment_provider1.client.PaymentProvider1Client;
import me.mahdiyar.digipay.payment.service.service.payment_provider1.model.dto.request.PaymentRequestDto;

import java.util.Random;

/**
 * @author Seyyed Mahdiyar Zerehpoush
 */
public class FakePaymentProvider1ClientImpl implements PaymentProvider1Client {
    @Override
    public BasePaymentResponseDto doPayment(PaymentRequestDto requestDto) {
        Random random = new Random(System.currentTimeMillis());
        return new BasePaymentResponseDto()
                .setResultCode(0L)
                .setResultMessage("موفق")
                .setRrn(String.valueOf(random.nextLong()));
    }
}
