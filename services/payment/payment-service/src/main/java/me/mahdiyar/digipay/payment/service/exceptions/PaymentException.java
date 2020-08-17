package me.mahdiyar.digipay.payment.service.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Seyyed Mahdiyar Zerehpoush
 */
@RequiredArgsConstructor
@Getter
public class PaymentException extends Exception {
    private final Exception exception;
    private final String errorMessage;
    private final TransactionProblemIssuer transactionProblemIssuer;
    private final boolean unknownState;
}
