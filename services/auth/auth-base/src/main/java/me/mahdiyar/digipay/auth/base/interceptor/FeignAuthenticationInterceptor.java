package me.mahdiyar.digipay.auth.base.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * purpose of this interceptor is to add Authorization header to every request
 */
public class FeignAuthenticationInterceptor implements RequestInterceptor {
    public static final String AUTHORIZATION_HEADER = "Authorization";

    @Override
    public void apply(RequestTemplate template) {
        ServletRequestAttributes requestAttributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if (requestAttributes == null) {
            return;
        }

        HttpServletRequest request = requestAttributes.getRequest();
        template.header(AUTHORIZATION_HEADER, request.getHeader(AUTHORIZATION_HEADER));
    }
}