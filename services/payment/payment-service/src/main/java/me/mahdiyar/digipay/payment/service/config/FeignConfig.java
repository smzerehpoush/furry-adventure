package me.mahdiyar.digipay.payment.service.config;

import me.mahdiyar.digipay.auth.service.base.interceptor.FeignAuthenticationInterceptor;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableFeignClients
@Configuration
public class FeignConfig {
    @Bean
    FeignAuthenticationInterceptor feignAuthenticationInterceptor() {
        return new FeignAuthenticationInterceptor();
    }
}
