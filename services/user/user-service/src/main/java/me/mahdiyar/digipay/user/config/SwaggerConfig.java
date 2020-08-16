package me.mahdiyar.digipay.user.config;

import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@RequiredArgsConstructor
public class SwaggerConfig {

    @Value("${spring.application.name}")
    private String serviceId;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.regex("/api/.*"))
                .build()
                .apiInfo(apiInfoBuilder())
                .securitySchemes(Lists.newArrayList(apiKey()));
    }

    private ApiInfo apiInfoBuilder() {
        return new ApiInfoBuilder()
                .title(serviceId + "Service Api Documentation")
                .description("Documentation automatically generated.\n" +
                        "HTTP status codes:\n" +
                        "\tcode = 200, message = Successful request.\n" +
                        "\tcode = 400, message = Check your request, especially the dictation of fields.\n" +
                        "\tcode = 401, message = You are not authorized to access the resource.\n" +
                        "\tcode = 403, message = Accessing the resource you were trying to reach is forbidden.\n" +
                        "\tcode = 404, message = The resource you were trying to reach is not found.\n" +
                        "\tcode = 500, message = Internal Server Error. Its not your fault. :D"
                )
                .version("1.0.0")
                .build();
    }

    @Bean
    SecurityScheme apiKey() {
        return new ApiKey("JWT", "Authorization", "header");
    }
}
