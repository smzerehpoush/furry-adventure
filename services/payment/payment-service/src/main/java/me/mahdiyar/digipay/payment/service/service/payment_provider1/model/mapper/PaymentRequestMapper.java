package me.mahdiyar.digipay.payment.service.service.payment_provider1.model.mapper;

import me.mahdiyar.digipay.payment.service.infrastructure.model.dto.request.BasePaymentRequestDto;
import me.mahdiyar.digipay.payment.service.service.payment_provider1.model.dto.request.PaymentRequestDto;

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
                .setDest(baseRequestDto.getDestinationPan())
                .setExpDate(baseRequestDto.getExp().replace("/", ""))
                .setPin(baseRequestDto.getPin())
                .setSource(baseRequestDto.getSourcePan());

    }
}
