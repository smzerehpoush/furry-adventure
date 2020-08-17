package me.mahdiyar.digipay.payment.service.infrastructure;

import lombok.RequiredArgsConstructor;
import me.mahdiyar.digipay.payment.service.infrastructure.model.dto.request.BasePaymentRequestDto;
import me.mahdiyar.digipay.payment.service.infrastructure.model.dto.response.BasePaymentResponseDto;

/**
 * @author mahdiyar
 */

@RequiredArgsConstructor
public class PaymentSystem {
    private final PaymentProvider paymentProvider;

    public BasePaymentResponseDto pay(BasePaymentRequestDto requestDto) {
        return paymentProvider.pay(requestDto);
    }
}
