package com.example.config;

import com.example.filter.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class GatewayConfig {

    @Autowired
    AuthenticationFilter filter;


    // It should redirect the request to ToDoService instead of just returning 200OK
    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("ToDoServiceList", r -> r.path("/todo/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://todoservice"))
                .route("auth-service", r -> r.path("/auth/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://auth-service"))
                .build();
    }

}