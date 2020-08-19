package me.mahdiyar.digipay.payment.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Seyyed Mahdiyar Zerehpoush
 */
@FeignClient(name = "user-service", url = "user-service:8081/api/v1/users")
public interface UserClient {
    @GetMapping("{user-id}/mobile-no")
    Long getUserMobileNo(@PathVariable("user-id") String userId);
}
