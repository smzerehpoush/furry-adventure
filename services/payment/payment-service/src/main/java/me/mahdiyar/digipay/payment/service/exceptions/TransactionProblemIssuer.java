package me.mahdiyar.digipay.payment.service.exceptions;

/**
 * @author Seyyed Mahdiyar Zerehpoush
 */
public enum TransactionProblemIssuer {
    Application,
    RemoteService,
    Unknown,
    TimeOut
}