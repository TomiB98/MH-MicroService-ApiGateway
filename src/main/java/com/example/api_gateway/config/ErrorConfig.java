package com.example.api_gateway.config;

import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

@Configuration
public class ErrorConfig {

    @Bean
    public ErrorWebExceptionHandler globalErrorHandler() {
        return (exchange, ex) -> {
            if (ex instanceof org.springframework.web.server.ResponseStatusException) {
                // Devuelve una respuesta con el estado y un mensaje adecuado
                return ServerResponse.status(HttpStatus.SERVICE_UNAVAILABLE)
                        .bodyValue("Service Unavailable: " + ex.getMessage()).then();
            }
            // Si la excepción es de otro tipo, devuelve el error tal cual
            return ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .bodyValue("Internal Server Error: " + ex.getMessage()).then();
        };
    }
}
