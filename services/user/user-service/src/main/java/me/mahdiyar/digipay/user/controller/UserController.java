package me.mahdiyar.digipay.user.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import me.mahdiyar.base.exceptions.BaseException;
import me.mahdiyar.digipay.user.contract.domain.BaseUser;
import me.mahdiyar.digipay.user.contract.domain.User;
import me.mahdiyar.digipay.user.contract.domain.request.CreateUserRequestDto;
import me.mahdiyar.digipay.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@Api
@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("{id}")
    public ResponseEntity<User> getById(@PathVariable("id") String id) throws BaseException {
        return ok(userService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<BaseUser>> getAll() {
        return ok(userService.getAll());
    }

    @GetMapping("search")
    public ResponseEntity<List<BaseUser>> searchUsers(@RequestParam("username") String username) {
        return ok(userService.searchUsers(username));
    }

    @PostMapping
    public ResponseEntity<BaseUser> create(@Valid @RequestBody CreateUserRequestDto requestDto) throws BaseException {
        return ok(userService.create(requestDto));
    }

}
