package me.mahdiyar.digipay.payment.service.service.payment_provider1;

import me.mahdiyar.digipay.payment.service.infrastructure.PaymentProvider;
import me.mahdiyar.digipay.payment.service.infrastructure.model.dto.request.BasePaymentRequestDto;
import me.mahdiyar.digipay.payment.service.infrastructure.model.dto.response.BasePaymentResponseDto;
import me.mahdiyar.digipay.payment.service.service.payment_provider1.model.dto.request.PaymentRequestDto;

/**
 * @author mahdiyar
 */
public class PaymentProvider1 implements PaymentProvider {
    @Override
    public BasePaymentResponseDto pay(BasePaymentRequestDto requestDto) {
        PaymentRequestDto paymentRequestDto = new PaymentRequestDto();
        return null;
    }
}
