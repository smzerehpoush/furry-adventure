package me.mahdiyar.digipay.payment.service.service.payment_provider2.model.mapper;

import me.mahdiyar.digipay.payment.service.infrastructure.model.dto.request.BasePaymentRequestDto;
import me.mahdiyar.digipay.payment.service.service.payment_provider2.model.dto.request.PaymentRequestDto;

/**
 * @author Seyyed Mahdiyar Zerehpoush
 */
public class PaymentRequestMapper {
    private PaymentRequestMapper() {

    }

    public static PaymentRequestDto map(BasePaymentRequestDto baseRequestDto) {
        return new PaymentRequestDto()
                .setAmount(baseRequestDto.getAmount())
                .setCvv2(baseRequestDto.getCvv2())
                .setTarget(baseRequestDto.getDestinationPan())
                .setExpire(baseRequestDto.getExp())
                .setPin2(baseRequestDto.getPin())
                .setSource(baseRequestDto.getSourcePan());

    }
}
