package me.mahdiyar.digipay.auth.service.config;

import me.mahdiyar.digipay.auth.service.base.interceptor.FeignAuthenticationInterceptor;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients
public class FeignConfig {
    @Bean
    FeignAuthenticationInterceptor feignAuthenticationInterceptor() {
        return new FeignAuthenticationInterceptor();
    }
}
