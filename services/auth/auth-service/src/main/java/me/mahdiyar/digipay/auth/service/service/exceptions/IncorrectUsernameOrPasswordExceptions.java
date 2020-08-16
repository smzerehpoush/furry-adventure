package me.mahdiyar.digipay.auth.service.service.exceptions;

import me.alidg.errors.annotation.ExceptionMapping;
import me.mahdiyar.digipay.base.exceptions.BaseException;
import org.springframework.http.HttpStatus;

@ExceptionMapping(statusCode = HttpStatus.NOT_FOUND, errorCode = "INCORRECT_USERNAME_PASSWORD")
public class IncorrectUsernameOrPasswordExceptions extends BaseException {
}
