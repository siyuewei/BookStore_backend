package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(r -> r.path("/microservice/**")
//						.filters(f -> f.rewritePath("/microservice",""))
						.uri("lb://BOOK-SERVICE")
				)
				.route(r -> r.path("/api/**")
						.uri("lb://MAIN-SERVICE")
				)
				.route(r -> r.path("/websocket/**")
//						.filters(f -> f.rewritePath("/web",""))
						.uri("lb://MAIN-SERVICE")
				).route(r -> r.path("/graphql/**")
								.uri("lb://MAIN-SERVICE")
				)
				.build();
	}
}