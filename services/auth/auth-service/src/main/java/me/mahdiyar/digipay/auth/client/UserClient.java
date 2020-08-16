package me.mahdiyar.digipay.auth.client;

import me.mahdiyar.digipay.user.contract.domain.BaseUser;
import me.mahdiyar.digipay.user.contract.domain.User;
import me.mahdiyar.digipay.user.contract.domain.request.CreateUserRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@FeignClient(name = "user-service", url = "http://localhost:8081/api/v1/users")
public interface UserClient {
    @GetMapping("{id}")
    User getById(@PathVariable("id") String id);

    @GetMapping("search")
    BaseUser searchUser(@RequestParam("username") String username);

    @PostMapping
    BaseUser create(@Valid @RequestBody CreateUserRequestDto requestDto);
}
