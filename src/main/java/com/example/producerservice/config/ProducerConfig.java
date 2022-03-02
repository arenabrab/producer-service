package com.example.producerservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Configuration
public class ProducerConfig {

    @Bean
    public RouterFunction<ServerResponse> route() {
        return RouterFunctions.route()
                .GET("/v1/get", ProducerConfig::getV1)
                .GET("/v2/get", ProducerConfig::getV2)
                .build();
    }

    private static Mono<ServerResponse> getV1(ServerRequest request) {
        return ServerResponse.ok().body(Mono.just("Hello from V1"), String.class);
    }

    private static Mono<ServerResponse> getV2(ServerRequest request) {
        return ServerResponse.ok().body(Mono.just("Hello from V3"), String.class);
    }

}
