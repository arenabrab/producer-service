package com.example.producerservice.config;

import com.example.producerservice.records.Pet;
import com.example.producerservice.records.Provider;
import com.github.javafaker.Faker;
import lombok.NonNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.Supplier;

@Configuration
public class ProducerConfig {

    @Bean
    Faker faker() {
        return Faker.instance();
    }

    @Bean
    public RouterFunction<ServerResponse> route() {
        return RouterFunctions.route()
                .GET("/v1/get", ProducerConfig::getV1)
                .GET("/v2/get", this::getV2)
                .build();
    }

    private static Mono<ServerResponse> getV1(ServerRequest request) {
        return ServerResponse.ok().body(Mono.just("Hello from V1"), String.class);
    }

    private Mono<ServerResponse> getV2(ServerRequest request) {
        return ServerResponse.ok().body(Mono.fromSupplier(getProvider), Provider.class);
    }

    private final Supplier<Provider> getProvider =
            () -> new Provider(
                faker().name().fullName(),
                faker().number().randomDigit(),
                new Pet(
                        faker().animal().name(),
                        faker().number().randomDigit(),
                        faker().number().randomDigit(),
                        faker().backToTheFuture().character(),
                        false));

}
