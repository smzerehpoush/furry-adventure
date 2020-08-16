package me.mahdiyar.auth.exceptions;

import me.alidg.errors.annotation.ExceptionMapping;
import me.mahdiyar.base.exceptions.BaseException;
import org.springframework.http.HttpStatus;

@ExceptionMapping(statusCode = HttpStatus.BAD_REQUEST, errorCode = "USERNAME_EXISTS")
public class UsernameExistsException extends BaseException {
}
