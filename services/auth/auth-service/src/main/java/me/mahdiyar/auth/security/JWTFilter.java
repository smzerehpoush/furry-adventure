package me.mahdiyar.auth.security;

import me.mahdiyar.auth.base.TokenProvider;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Filters incoming requests and installs a Spring Security principal if a header corresponding to a valid user is
 * found.
 */
@Component
public class JWTFilter extends GenericFilterBean {
    private static final String ORIGIN = "Origin";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        String authenticationToken = resolveToken(httpServletRequest);

        if (StringUtils.hasText(authenticationToken) && TokenProvider.validateToken(authenticationToken)) {
            JwtAuthenticationToken token = new JwtAuthenticationToken(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(token);
        }

        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        if (httpServletRequest.getHeader(ORIGIN) != null) {
            httpServletResponse.addHeader("Access-Control-Allow-Headers", "origin, content-type, accept, x-requested-with, authorization");
            httpServletResponse.addHeader("Access-Control-Expose-Headers", "origin, content-type, accept, x-requested-with, authorization");
        }

        if (httpServletRequest.getMethod().equals("OPTIONS")) {
            httpServletResponse.getWriter().print("OK");
            httpServletResponse.getWriter().flush();
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private String resolveToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(TokenProvider.AUTHORIZATION_HEADER);

        if (StringUtils.hasText(authorizationHeader) && authorizationHeader.startsWith(TokenProvider.BEARER)) {
            return authorizationHeader.replace(TokenProvider.BEARER, "");
        }

        return null;
    }
}
