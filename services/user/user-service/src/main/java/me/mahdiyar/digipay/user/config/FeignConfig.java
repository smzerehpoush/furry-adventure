package me.mahdiyar.digipay.user.config;

import me.mahdiyar.auth.base.interceptor.FeignAuthenticationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
    @Bean
    FeignAuthenticationInterceptor feignAuthenticationInterceptor() {
        return new FeignAuthenticationInterceptor();
    }
}
