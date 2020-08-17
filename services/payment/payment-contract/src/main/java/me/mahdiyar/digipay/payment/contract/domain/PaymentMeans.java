package me.mahdiyar.digipay.payment.contract.domain;

import lombok.Data;

@Data
public class PaymentMeans {
    private String pin;
    private String cvv2;
    private String exp;
}
