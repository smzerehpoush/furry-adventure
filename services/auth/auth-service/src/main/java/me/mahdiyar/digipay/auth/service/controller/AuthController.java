package me.mahdiyar.digipay.auth.service.controller;

import lombok.RequiredArgsConstructor;
import me.mahdiyar.digipay.auth.service.contract.request.LoginRequestDto;
import me.mahdiyar.digipay.auth.service.contract.response.LoginResponseDto;
import me.mahdiyar.digipay.auth.service.contract.response.SignupResponseDto;
import me.mahdiyar.digipay.base.exceptions.BaseException;
import me.mahdiyar.digipay.auth.service.contract.request.SignupRequestDto;
import me.mahdiyar.digipay.auth.service.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Seyyed Mahdiyar Zerehpoush
 */
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<SignupResponseDto> signup(@RequestBody SignupRequestDto request, HttpServletResponse response
    ) throws BaseException {
        return ResponseEntity.ok(authService.signup(request, response));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> authenticate(
            @RequestBody LoginRequestDto request, HttpServletResponse response) throws BaseException {
        return ResponseEntity.ok(authService.login(request, response));
    }
}
