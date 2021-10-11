package com.example.taxiApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TaxiAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaxiAppApplication.class, args);}

		@Bean
		RouteLocator gateway (RouteLocatorBuilder rlb){
			return rlb
					.routes()
					.route("error", rs -> rs
							.path("/error")
							.filters(fs -> fs.retry(6))
							.uri("lb:/new"))
					.route("newTrip", routeSpec -> routeSpec
							.path("/new")
							.filters(gatewayFilterSpec ->
									gatewayFilterSpec.setPath("/newTrip"))
							.uri("http://localhost:9191/")
					)
					.route("trip", routeSpec -> routeSpec
							.path("/trip")
							.filters(gatewayFilterSpec ->
									gatewayFilterSpec.setPath("/tripInfo"))
							.uri("http://localhost:9292/"))
					.route("payment", routeSpec -> routeSpec
							.path("/pay")
							.filters(gatewayFilterSpec ->
									gatewayFilterSpec.setPath("/payment"))
							.uri("http://localhost:9393/"))
					.build();
		}
	}
