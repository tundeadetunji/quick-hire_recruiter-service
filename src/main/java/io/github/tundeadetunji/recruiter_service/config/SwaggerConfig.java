package io.github.tundeadetunji.recruiter_service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .info(new Info()
                        .title("Recruiter Service API")
                        .description("Handles job posts, applications, and recruiter interactions")
                        .version("1.0.0"));
    }

    /*@Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("recruiter")
                .pathsToMatch(
                        "/api/v1/recruiter/**",
                        "/api/v1/job/**",
                        "/api/v1/post/**"
                )
                .build();
    }*/
}
