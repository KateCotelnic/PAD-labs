package com.example.newTripService;

import com.example.newTripService.entity.User;
import com.example.newTripService.entity.enums.TripType;
import com.example.newTripService.entity.enums.UserType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;
import java.util.stream.Stream;

@SpringBootApplication
//@EnableEurekaClient
//@EnableResourceServer
public class NewTripServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewTripServiceApplication.class, args);
	}

//	private final Flux<User> users = Flux.fromStream(
//			Stream.generate(() -> {
//					return new User(1L, "location", "destination", TripType.DELIVERY, UserType.PASSENGER, 2L);
//			})
//	);
//
//	@Bean
//	Flux<User> users (){
//		return this.users.publish().autoConnect();
//	}
//@Bean
//@LoadBalanced
//public WebClient.Builder loadBelencedWebClientBuilder() {
//	return WebClient.builder();
//}
}
