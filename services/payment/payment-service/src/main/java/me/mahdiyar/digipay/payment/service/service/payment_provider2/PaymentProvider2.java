package me.mahdiyar.digipay.payment.service.service.payment_provider2;

import me.mahdiyar.digipay.payment.service.infrastructure.PaymentProvider;
import me.mahdiyar.digipay.payment.service.infrastructure.model.dto.request.BasePaymentRequestDto;
import me.mahdiyar.digipay.payment.service.infrastructure.model.dto.response.BasePaymentResponseDto;
import me.mahdiyar.digipay.payment.service.service.payment_provider2.request.PaymentRequestDto;

/**
 * @author mahdiyar
 */
public class PaymentProvider2 implements PaymentProvider {
    @Override
    public BasePaymentResponseDto pay(BasePaymentRequestDto requestDto) {
        PaymentRequestDto paymentRequestDto = new PaymentRequestDto();
        return null;
    }
}
