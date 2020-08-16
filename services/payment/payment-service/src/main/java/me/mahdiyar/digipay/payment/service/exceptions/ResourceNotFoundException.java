package me.mahdiyar.digipay.payment.service.exceptions;

import me.alidg.errors.annotation.ExceptionMapping;
import me.mahdiyar.digipay.base.exceptions.BaseException;
import org.springframework.http.HttpStatus;

@ExceptionMapping(statusCode = HttpStatus.NOT_FOUND, errorCode = "RESOURCE_NOT_FOUND")
public class ResourceNotFoundException extends BaseException {
}
