package me.mahdiyar.auth.base.exception;

import me.alidg.errors.annotation.ExceptionMapping;
import me.mahdiyar.base.exceptions.BaseException;
import org.springframework.http.HttpStatus;

@ExceptionMapping(statusCode = HttpStatus.UNAUTHORIZED, errorCode = "InvalidAccessToken")
public class InvalidAccessTokenException extends BaseException {
}
