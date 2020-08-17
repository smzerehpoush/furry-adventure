package me.mahdiyar.digipay.payment.service.service.payment_provider2;

import lombok.extern.slf4j.Slf4j;
import me.mahdiyar.digipay.payment.service.infrastructure.PaymentProvider;
import me.mahdiyar.digipay.payment.service.infrastructure.model.dto.request.BasePaymentRequestDto;
import me.mahdiyar.digipay.payment.service.infrastructure.model.dto.response.BasePaymentResponseDto;
import me.mahdiyar.digipay.payment.service.service.payment_provider2.model.dto.request.PaymentRequestDto;
import me.mahdiyar.digipay.payment.service.service.payment_provider2.model.mapper.PaymentRequestMapper;
import org.springframework.stereotype.Component;

/**
 * @author mahdiyar
 */
@Component
@Slf4j
public class PaymentProvider2 implements PaymentProvider {
    @Override
    public BasePaymentResponseDto pay(BasePaymentRequestDto requestDto) {
        PaymentRequestDto paymentRequestDto = PaymentRequestMapper.map(requestDto);
        logger.info("trying to send payment request to provider1 : {}", paymentRequestDto);
        return new BasePaymentResponseDto();
    }
}
