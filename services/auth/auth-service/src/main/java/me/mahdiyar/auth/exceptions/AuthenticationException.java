package me.mahdiyar.auth.exceptions;

import me.alidg.errors.annotation.ExceptionMapping;
import me.mahdiyar.base.exception.BaseException;
import org.springframework.http.HttpStatus;

/**
 * @author Seyyed Mahdiyar Zerehpoush
 */
@ExceptionMapping(statusCode = HttpStatus.UNAUTHORIZED, errorCode = "Unauthorized")
public class AuthenticationException extends BaseException {

}
