package me.mahdiyar.digipay.auth.service.exceptions;

import me.alidg.errors.annotation.ExceptionMapping;
import me.mahdiyar.digipay.base.exceptions.BaseException;
import org.springframework.http.HttpStatus;

/**
 * @author Seyyed Mahdiyar Zerehpoush
 */
@ExceptionMapping(statusCode = HttpStatus.UNAUTHORIZED, errorCode = "Unauthorized")
public class AuthenticationException extends BaseException {

}
