package me.mahdiyar.digipay.payment.service.infrastructure;

import me.mahdiyar.digipay.payment.service.infrastructure.model.dto.request.BasePaymentRequestDto;
import me.mahdiyar.digipay.payment.service.infrastructure.model.dto.response.BasePaymentResponseDto;

public interface PaymentProvider {
    BasePaymentResponseDto pay(BasePaymentRequestDto requestDto);
}
