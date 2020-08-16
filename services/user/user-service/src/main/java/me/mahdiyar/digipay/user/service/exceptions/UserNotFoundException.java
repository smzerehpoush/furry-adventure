package me.mahdiyar.digipay.user.service.exceptions;

import me.alidg.errors.annotation.ExceptionMapping;
import me.mahdiyar.base.exceptions.BaseException;
import org.springframework.http.HttpStatus;

@ExceptionMapping(statusCode = HttpStatus.NOT_FOUND, errorCode = "USER_NOT_FOUND")
public class UserNotFoundException extends BaseException {
}
