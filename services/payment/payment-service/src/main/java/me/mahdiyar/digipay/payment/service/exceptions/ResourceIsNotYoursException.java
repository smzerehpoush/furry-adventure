package me.mahdiyar.digipay.payment.service.exceptions;

import me.alidg.errors.annotation.ExceptionMapping;
import me.mahdiyar.digipay.base.exceptions.BaseException;
import org.springframework.http.HttpStatus;

@ExceptionMapping(statusCode = HttpStatus.FORBIDDEN, errorCode = "RESOURCE_IS_NOT_YOURS")
public class ResourceIsNotYoursException extends BaseException {
}
