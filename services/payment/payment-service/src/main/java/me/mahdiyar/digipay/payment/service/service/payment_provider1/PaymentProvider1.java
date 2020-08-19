package me.mahdiyar.digipay.payment.service.service.payment_provider1;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.mahdiyar.digipay.payment.service.infrastructure.PaymentProvider;
import me.mahdiyar.digipay.payment.service.infrastructure.model.dto.request.BasePaymentRequestDto;
import me.mahdiyar.digipay.payment.service.infrastructure.model.dto.response.BasePaymentResponseDto;
import me.mahdiyar.digipay.payment.service.service.payment_provider1.client.PaymentProvider1Client;
import me.mahdiyar.digipay.payment.service.service.payment_provider1.model.dto.request.PaymentRequestDto;
import me.mahdiyar.digipay.payment.service.service.payment_provider1.model.mapper.PaymentRequestMapper;
import org.springframework.stereotype.Component;

/**
 * @author mahdiyar
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentProvider1 implements PaymentProvider {
    private final PaymentProvider1Client paymentProvider1Client;

    @Override
    public BasePaymentResponseDto pay(BasePaymentRequestDto requestDto) {
        PaymentRequestDto paymentRequestDto = PaymentRequestMapper.map(requestDto);
        logger.info("trying to send payment request to provider1 : {}", paymentRequestDto);
        return paymentProvider1Client.doPayment(paymentRequestDto);
    }
}
