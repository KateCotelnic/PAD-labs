package com.example.taxiApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class TaxiAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaxiAppApplication.class, args);}

		@Bean
		RouteLocator gateway (RouteLocatorBuilder rlb){
			return rlb
					.routes()
					.route(rs -> rs.path("/default")
							.filters(f -> f.filters((exchange, chain) -> chain.filter(exchange)))
							.uri("https://spring.io/guides"))
					.route("error", rs -> rs
							.path("/error")
							.filters(fs -> fs.retry(6))
							.uri("lb:/new"))
					.route("newTrip", routeSpec -> routeSpec
							.path("/new")
							.filters(fl -> fl
									.circuitBreaker(cbc -> cbc.setFallbackUri("forward:/default"))
									.setPath("lb:/newTrip"))
							.uri("http://localhost:9191")
					)
					.route("trip", routeSpec -> routeSpec
							.path("/trip")
							.filters(gatewayFilterSpec ->
									gatewayFilterSpec.setPath("lb:/currentTrip")
											.circuitBreaker(cbc -> cbc.setFallbackUri("forward:/default")))
							.uri("http://localhost:9292/"))
					.route("myTrip", routeSpec -> routeSpec
							.path("/myTrip/{id}")
							.filters(gatewayFilterSpec ->
									gatewayFilterSpec.setPath("lb:/myTrip/{id}")
											.circuitBreaker(cbc -> cbc.setFallbackUri("forward:/default")))
							.uri("http://localhost:9292/"))
					.route("finish", routeSpec -> routeSpec
							.path("/finish")
							.filters(gfs ->
									gfs.setPath("lb:/finishTrip")
											.circuitBreaker(cbc -> cbc.setFallbackUri("forward:/default")))
							.uri("http://localhost:9292/"))
					.route("getPassenger", routeSpec -> routeSpec
							.path("/passenger")
							.filters(gfs ->
									gfs.setPath("lb:/getPassenger"))
							.uri("http://localhost:9393/"))
					.route("getDriver", routeSpec -> routeSpec
							.path("/driver")
							.filters(gfs ->
									gfs.setPath("lb:/getDriver"))
							.uri("http://localhost:9393/"))
					.route("insertToDB", routeSpec -> routeSpec
							.path("/insert")
							.filters(gfs ->
									gfs.setPath("lb:/insert"))
							.uri("http://localhost:9393/"))
					.build();
		}
	}
