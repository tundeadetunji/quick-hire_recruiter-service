package io.github.tundeadetunji.recruiter_service.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/resilience4j")
@RequiredArgsConstructor
@Tag(name = "Resilience4j", description = "Circuit breakers, retries, and rate limiters")
public class Resilience4jActuatorController {

    private final WebClient.Builder webClientBuilder;

    @Operation(summary = "Get circuit breaker states")
    @GetMapping("/circuitbreakers")
    public Mono<String> circuitBreakers() {
        return webClientBuilder.build()
                .get()
                .uri("http://localhost:8080/actuator/resilience4j.circuitbreakers")
                .retrieve()
                .bodyToMono(String.class);
    }

    @Operation(summary = "Get retry states")
    @GetMapping("/retries")
    public Mono<String> retries() {
        return webClientBuilder.build()
                .get()
                .uri("http://localhost:8080/actuator/resilience4j.retries")
                .retrieve()
                .bodyToMono(String.class);
    }

    @Operation(summary = "Get rate limiter states")
    @GetMapping("/ratelimiters")
    public Mono<String> ratelimiters() {
        return webClientBuilder.build()
                .get()
                .uri("http://localhost:8080/actuator/resilience4j.ratelimiters")
                .retrieve()
                .bodyToMono(String.class);
    }
}
