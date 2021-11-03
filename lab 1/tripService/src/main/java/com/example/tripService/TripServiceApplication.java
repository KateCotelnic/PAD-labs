package com.example.tripService;

import com.example.tripService.entity.Trip;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.stream.Stream;

@SpringBootApplication
@EnableEurekaClient
public class TripServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TripServiceApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public WebClient.Builder webClientBuilder(){
		return WebClient.builder();
	}
}
