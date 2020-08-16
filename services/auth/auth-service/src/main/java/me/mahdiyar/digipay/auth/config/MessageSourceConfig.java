package me.mahdiyar.digipay.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.nio.charset.StandardCharsets;

@Configuration
public class MessageSourceConfig {

    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasename("i18n/messages");  // name of the resource bundle
        source.setUseCodeAsDefaultMessage(true);
        source.setDefaultEncoding(StandardCharsets.UTF_8.name());
        return source;
    }
}