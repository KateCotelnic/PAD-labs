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
									gatewayFilterSpec.setPath("/currentTrip"))
							.uri("http://localhost:9292/"))
//					.route("myTrip", routeSpec -> routeSpec
//							.path("/myTrip")
//							.filters(gatewayFilterSpec ->
//									gatewayFilterSpec.setPath("/myTrip"))
//							.uri("http://localhost:9292/"))
//					.route("finish", routeSpec -> routeSpec
//							.path("/finish")
//							.filters(gfs ->
//									gfs.setPath("/finishTrip"))
//							.uri("http://localhost:9292/"))
//					.route("getPassenger", routeSpec -> routeSpec
//							.path("/passenger")
//							.filters(gfs ->
//									gfs.setPath("/getPassenger"))
//							.uri("http://localhost:9393/"))
//					.route("getDriver", routeSpec -> routeSpec
//							.path("/driver")
//							.filters(gfs ->
//									gfs.setPath("/getDriver"))
//							.uri("http://localhost:9393/"))
//					.route("insertToDB", routeSpec -> routeSpec
//							.path("/insert")
//							.filters(gfs ->
//									gfs.setPath("/insert"))
//							.uri("http://localhost:9393/"))
					.build();
		}
	}
