package me.mahdiyar.digipay.payment.service.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import me.mahdiyar.digipay.auth.service.base.annotation.WithUser;
import me.mahdiyar.digipay.auth.service.base.domain.BaseUserCredential;
import me.mahdiyar.digipay.base.exceptions.BaseException;
import me.mahdiyar.digipay.payment.contract.domain.request.DoPaymentRequestDto;
import me.mahdiyar.digipay.payment.contract.domain.response.DoPaymentResponseDto;
import me.mahdiyar.digipay.payment.service.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static org.springframework.http.ResponseEntity.ok;


@Api
@RestController
@RequestMapping("api/v1/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<DoPaymentResponseDto> doPayment(
            @WithUser BaseUserCredential baseUserCredential,
            @Valid DoPaymentRequestDto requestDto,
            HttpServletRequest servletRequest) throws BaseException {
        return ok(paymentService.doPayment(baseUserCredential, requestDto, servletRequest));
    }
}
