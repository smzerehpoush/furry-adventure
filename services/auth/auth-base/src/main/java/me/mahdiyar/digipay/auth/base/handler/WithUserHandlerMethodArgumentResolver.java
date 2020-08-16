package me.mahdiyar.digipay.auth.base.handler;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import me.mahdiyar.digipay.auth.base.TokenProvider;
import me.mahdiyar.digipay.auth.base.annotation.WithUser;
import me.mahdiyar.digipay.auth.base.domain.BaseUserCredential;
import me.mahdiyar.digipay.auth.base.exception.InvalidAccessTokenException;
import org.springframework.core.MethodParameter;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
public class WithUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(WithUser.class)
                && parameter.getParameterType().equals(BaseUserCredential.class);
    }

    @Override

    public Object resolveArgument(
            @Nullable MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest
            , WebDataBinderFactory binderFactory) throws InvalidAccessTokenException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) webRequest.getNativeRequest();
        String authorizationHeader = extractAuthorizationHeader(httpServletRequest);
        if (StringUtils.hasText(authorizationHeader) && authorizationHeader.startsWith(TokenProvider.BEARER)) {
            authorizationHeader = authorizationHeader.replace(TokenProvider.BEARER, "");
        } else {
            throw new InvalidAccessTokenException();
        }

        // Parse token and get claims
        Claims claims = TokenProvider.parse(authorizationHeader);

        return new BaseUserCredential()
                .setUserId(claims.getSubject())
                .setSessionId(claims.get(TokenProvider.SESSION_ID_KEY, String.class));
    }

    private String extractAuthorizationHeader(HttpServletRequest httpServletRequest) {
        String authorizationHeader = httpServletRequest.getHeader(TokenProvider.AUTHORIZATION_HEADER);
        if (!StringUtils.isEmpty(authorizationHeader))
            return authorizationHeader;
        if (httpServletRequest.getCookies() == null || httpServletRequest.getCookies().length == 0)
            return null;
        for (Cookie cookie : httpServletRequest.getCookies()) {
            if (TokenProvider.AUTHORIZATION_HEADER.equals(cookie.getName()))
                return cookie.getValue();
        }
        return null;
    }
}