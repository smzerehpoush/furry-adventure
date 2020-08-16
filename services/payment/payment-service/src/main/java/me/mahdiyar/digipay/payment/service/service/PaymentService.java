package me.mahdiyar.digipay.payment.service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.mahdiyar.digipay.auth.service.base.domain.BaseUserCredential;
import me.mahdiyar.digipay.payment.contract.domain.request.DoPaymentRequestDto;
import me.mahdiyar.digipay.payment.contract.domain.response.DoPaymentResponseDto;
import me.mahdiyar.digipay.payment.service.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public DoPaymentResponseDto doPayment(
            BaseUserCredential baseUserCredential, DoPaymentRequestDto requestDto, HttpServletRequest servletRequest) {
        return null;
    }
}
