package me.mahdiyar.digipay.payment.service.service.payment_provider2;

import me.mahdiyar.digipay.payment.service.infrastructure.model.dto.response.BasePaymentResponseDto;
import me.mahdiyar.digipay.payment.service.service.payment_provider2.client.PaymentProvider2Client;
import me.mahdiyar.digipay.payment.service.service.payment_provider2.model.dto.request.PaymentRequestDto;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @author Seyyed Mahdiyar Zerehpoush
 */
@Component
public class FakePaymentProvider2ClientImpl implements PaymentProvider2Client {
    @Override
    public BasePaymentResponseDto doPayment(PaymentRequestDto requestDto) {
        Random random = new Random(System.currentTimeMillis());
        return new BasePaymentResponseDto()
                .setResultCode(0L)
                .setResultMessage("موفق")
                .setRrn(String.valueOf(random.nextLong()));
    }
}
