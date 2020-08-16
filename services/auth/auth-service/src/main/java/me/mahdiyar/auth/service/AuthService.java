package me.mahdiyar.auth.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.mahdiyar.auth.base.TokenProvider;
import me.mahdiyar.auth.client.UserClient;
import me.mahdiyar.auth.contract.domain.user.request.LoginRequestDto;
import me.mahdiyar.auth.contract.domain.user.request.SignupRequestDto;
import me.mahdiyar.auth.contract.domain.user.response.LoginResponseDto;
import me.mahdiyar.auth.contract.domain.user.response.SignupResponseDto;
import me.mahdiyar.auth.domain.SessionEntity;
import me.mahdiyar.auth.exceptions.UsernameExistsException;
import me.mahdiyar.auth.repository.SessionRepo;
import me.mahdiyar.auth.service.exceptions.IncorrectUsernameOrPasswordExceptions;
import me.mahdiyar.base.exceptions.BaseException;
import me.mahdiyar.digipay.user.contract.domain.BaseUser;
import me.mahdiyar.digipay.user.contract.domain.User;
import org.springframework.data.util.Pair;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Objects;


@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserClient userClient;
    private final SessionRepo sessionRepo;

    public LoginResponseDto login(LoginRequestDto request, HttpServletResponse response) throws BaseException {
        BaseUser baseUser = userClient.searchUser(request.getUsername());
        User user = userClient.getById(baseUser.getId());
        final String hashedPassword = passwordEncoder.encode(request.getPassword());
        if (baseUser == null || !Objects.equals(user.getHashedPassword(), hashedPassword))
            throw new IncorrectUsernameOrPasswordExceptions();
        SessionEntity sessionEntity = createSession(user.getId());
        final String token = TokenProvider.generateTokenForUser(user.getId(), sessionEntity.getId());
        setAuthenticationInfoInResponse(response, token);
        return new LoginResponseDto(baseUser, token);
    }

    private void setAuthenticationInfoInResponse(HttpServletResponse response, String plainToken) {
        response.addHeader(TokenProvider.AUTHORIZATION_HEADER, TokenProvider.BEARER + plainToken);
        response.addCookie(createCookie(plainToken));
    }

    private Cookie createCookie(String plainToken) {
        Cookie cookie = new Cookie(TokenProvider.AUTHORIZATION_HEADER, plainToken);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(Integer.MAX_VALUE);
        return cookie;
    }

    private SessionEntity createSession(String userId) {
        SessionEntity sessionEntity = new SessionEntity(userId);
        return sessionRepo.save(sessionEntity);
    }

    public SignupResponseDto signup(SignupRequestDto request, HttpServletResponse response) throws BaseException {
        BaseUser baseUser = userClient.searchUser(request.getUsername());
        if (baseUser != null)
            throw new UsernameExistsException();
        final String hashedPassword = passwordEncoder.encode(request.getPassword());
        signup(request.getUsername(), hashedPassword);
        final Pair<?, String> sessionPair;
        final BaseUserDto userDto;
        final UserEntity userEntity = userClient.login(request.getUsername(), hashedPassword);
        sessionPair = createSession(userEntity);
        userDto = new BaseUserDto(userEntity);
        setAuthenticationInfoInResponse(response, sessionPair.getSecond());
        return new SignupResponseDto(userDto, sessionPair.getSecond());
    }

    private void signup(String username, String hashedPassword) {
        userClient.create()
    }

    public SessionEntity authenticate(HttpServletRequest request) throws AuthenticationException {
        final String plainToken = extractPlainToken(request);
        return authenticate(plainToken);
    }

    private String extractPlainToken(HttpServletRequest request) throws AuthenticationException {
        logger.info("trying to extract plain token from request");
        String bearerTokenFromHeader = extractTokenFromHeader(request);
        if (bearerTokenFromHeader != null && !StringUtils.isEmpty(bearerTokenFromHeader))
            return bearerTokenFromHeader
                    .replace(AUTHORIZATION_HEADER_BASE, "")
                    .replace(AUTHORIZATION_HEADER_BASE.toLowerCase(), "");
        logger.info("request does not have authorization header");
        String bearerTokenFromCookie = extractTokenFromCookie(request);
        if (bearerTokenFromCookie != null && !StringUtils.isEmpty(bearerTokenFromCookie))
            return bearerTokenFromCookie;
        logger.info("request does not have authorization cookie");
        throw new AuthenticationException();
    }

    private String extractTokenFromCookie(HttpServletRequest request) {
        if (request == null || request.getCookies() == null)
            return null;
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals(AUTHORIZATION_HEADER)) {
                return cookie.getValue();
            }
        }
        return null;
    }

    private String extractTokenFromHeader(HttpServletRequest request) {
        return request == null ? null : request.getHeader(AUTHORIZATION_HEADER);
    }

    private SessionEntity authenticate(String plainToken) throws AuthenticationException {
        final SessionEntity sessionEntity = sessionRepo.findByToken(hash(plainToken))
                .orElseThrow(AuthenticationException::new);
        sessionEntity.setLastActivityDate(new Date());
        sessionRepo.saveAndFlush(sessionEntity);
        return sessionEntity;
    }
}
