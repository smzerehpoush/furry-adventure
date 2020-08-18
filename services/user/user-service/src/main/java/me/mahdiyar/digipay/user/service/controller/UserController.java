package me.mahdiyar.digipay.user.service.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import me.mahdiyar.digipay.base.exceptions.BaseException;
import me.mahdiyar.digipay.user.service.contract.domain.BaseUser;
import me.mahdiyar.digipay.user.service.contract.domain.User;
import me.mahdiyar.digipay.user.service.contract.domain.request.CreateUserRequestDto;
import me.mahdiyar.digipay.user.service.service.UserService;
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

    @GetMapping("{id}/mobile-no")
    public ResponseEntity<String> getUserMobileNo(@PathVariable("id") String id) throws BaseException {
        return ok(userService.getUserMobileNo(id));
    }

    @GetMapping
    public ResponseEntity<List<BaseUser>> getAll() {
        return ok(userService.getAll());
    }

    @GetMapping("search")
    public ResponseEntity<BaseUser> searchUsers(@RequestParam("username") String username) throws BaseException {
        return ok(userService.searchUsers(username));
    }

    @PostMapping
    public ResponseEntity<BaseUser> create(@Valid @RequestBody CreateUserRequestDto requestDto) throws BaseException {
        return ok(userService.create(requestDto));
    }

}
