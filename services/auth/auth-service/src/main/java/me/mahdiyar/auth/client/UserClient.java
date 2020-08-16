package me.mahdiyar.auth.client;

import me.mahdiyar.digipay.user.contract.domain.BaseUser;
import me.mahdiyar.digipay.user.contract.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("user-service/api/v1/user")
public interface UserClient {
    @GetMapping("{id}")
    User getById(@PathVariable("id") String id);

    @GetMapping("user/search")
    BaseUser searchUser(@RequestParam("username") String username);

    @PostMapping
    BaseUser createUser(@RequestParam("username") String username);

}
