package me.mahdiyar.digipay.auth.service.service;


import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.mahdiyar.digipay.auth.service.base.TokenProvider;
import me.mahdiyar.digipay.auth.service.client.UserClient;
import me.mahdiyar.digipay.auth.service.contract.request.LoginRequestDto;
import me.mahdiyar.digipay.auth.service.contract.request.SignupRequestDto;
import me.mahdiyar.digipay.auth.service.contract.response.LoginResponseDto;
import me.mahdiyar.digipay.auth.service.contract.response.SignupResponseDto;
import me.mahdiyar.digipay.auth.service.domain.SessionEntity;
import me.mahdiyar.digipay.auth.service.exceptions.UserNotFoundException;
import me.mahdiyar.digipay.auth.service.exceptions.UsernameExistsException;
import me.mahdiyar.digipay.auth.service.repository.SessionRepo;
import me.mahdiyar.digipay.auth.service.service.exceptions.IncorrectUsernameOrPasswordExceptions;
import me.mahdiyar.digipay.base.exceptions.BaseException;
import me.mahdiyar.digipay.user.service.contract.domain.BaseUser;
import me.mahdiyar.digipay.user.service.contract.domain.User;
import me.mahdiyar.digipay.user.service.contract.domain.request.CreateUserRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;


@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserClient userClient;
    private final SessionRepo sessionRepo;

    public LoginResponseDto login(LoginRequestDto request, HttpServletResponse response) throws BaseException {
        User user = getUser(request.getUsername());
        if (!passwordEncoder.matches(request.getPassword(), user.getHashedPassword()))
            throw new IncorrectUsernameOrPasswordExceptions();
        SessionEntity sessionEntity = createSession(user.getId());
        final String token = TokenProvider.generateTokenForUser(user.getId(), sessionEntity.getId());
        setAuthenticationInfoInResponse(response, token);
        BaseUser baseUser = new BaseUser(user.getId(), user.getUsername());
        return new LoginResponseDto(baseUser, token);
    }

    private User getUser(String username) throws UserNotFoundException {
        logger.info("trying to get user from user-service with username : {}", username);
        try {
            BaseUser baseUser = userClient.searchUser(username);
            logger.info("trying to get user from user-service with id : {}", baseUser.getId());
            return userClient.getById(baseUser.getId());
        } catch (FeignException ex) {
            if (ex.status() == HttpStatus.NOT_FOUND.value())
                throw new UserNotFoundException();
            throw ex;
        }
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
        BaseUser user;
        try {
            user = userClient.searchUser(request.getUsername());
        } catch (FeignException ex) {
            if (ex.status() != HttpStatus.NOT_FOUND.value())
                throw new UsernameExistsException();
            user = null;
        }
        if (user != null)
            throw new UsernameExistsException();
        final String hashedPassword = passwordEncoder.encode(request.getPassword());
        user = signup(request.getUsername(), hashedPassword);
        final SessionEntity sessionEntity = createSession(user.getId());
        final String token = TokenProvider.generateTokenForUser(user.getId(), sessionEntity.getId());
        setAuthenticationInfoInResponse(response, token);
        return new SignupResponseDto(user, token);
    }

    private BaseUser signup(String username, String hashedPassword) {
        CreateUserRequestDto requestDto = new CreateUserRequestDto();
        requestDto.setUsername(username);
        requestDto.setHashedPassword(hashedPassword);
        try {
            return userClient.create(requestDto);

        } catch (Exception ex) {
            logger.error("error in signing user up ", ex);
            throw ex;
        }
    }

}
