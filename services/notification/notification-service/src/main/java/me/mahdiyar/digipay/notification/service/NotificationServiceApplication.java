package me.mahdiyar.digipay.notification.service;

import io.micrometer.core.instrument.MeterRegistry;
import me.mahdiyar.digipay.auth.service.base.config.UserAuthConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
//@EnableDiscoveryClient
public class NotificationServiceApplication {

    @Value("${spring.application.name}")
    private String serviceName;

    public static void main(String[] args) {
        SpringApplication.run(NotificationServiceApplication.class, args);
    }

    @Bean
    public UserAuthConfig userAuthConfig() {
        return new UserAuthConfig();
    }

    @Bean
    MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
        return registry -> registry.config()
                .commonTags("application", "digipay" + "-" + serviceName);
    }
}
