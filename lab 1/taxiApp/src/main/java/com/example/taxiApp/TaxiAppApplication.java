package com.example.taxiApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.web.server.SecurityWebFilterChain;

@SpringBootApplication
//@EnableOAuth2Sso
//@EnableZuulProxy
@EnableEurekaClient
public class TaxiAppApplication {

    public static void main(String[] args) {

        SpringApplication.run(TaxiAppApplication.class, args);
    }

//    @Bean
//    RedisRateLimiter redisRateLimiter() {
//        return new RedisRateLimiter(5, 10);
//    }
//
//    @Bean
//    SecurityWebFilterChain authorization(ServerHttpSecurity hhtp){
//        return hhtp
//                .httpBasic(Customizer.withDefaults())
//                .csrf(ServerHttpSecurity.CsrfSpec::disable)
//                .authorizeExchange(ae -> ae
//                        .pathMatchers("/new").authenticated()
//                        .anyExchange()
//                        .permitAll())
//                .build();
//    }
//
//    @Bean
//    MapReactiveUserDetailsService authentication(){
//        return new MapReactiveUserDetailsService(User.withDefaultPasswordEncoder()
//                .username("kate")
//                .password("kk")
//                .roles("USER")
//                .build());
//    }

    @Bean
    RouteLocator gateway(RouteLocatorBuilder rlb) {
        return rlb
                .routes()
                .route(rs -> rs.path("/default")
                        .filters(f -> f.filters((exchange, chain) -> chain.filter(exchange)))
                        .uri("http://localhost:9999/login"))
                .route("error", rs -> rs
                        .path("/error")
                        .filters(fs -> fs.retry(6))
                        .uri("lb:/login"))
                .route("newTrip", routeSpec -> routeSpec
                                .path("/new")
                                .filters(fl -> fl
//                                        .requestRateLimiter(rlc -> rlc.setRateLimiter(redisRateLimiter())
//                                                .setKeyResolver(exchange -> {
//                                                    return exchange.getPrincipal().map(principal -> principal.getName()).switchIfEmpty(Mono.empty());
//                                                }))
                                        .circuitBreaker(cbc -> cbc.setFallbackUri("forward:/default"))
                                        .setPath("lb:/newTrip"))
                                .uri("http://localhost:9393")
                )
//                .route("newTripAll", routeSpec -> routeSpec
//                                .path("/getAllFromNewTrip")
//                                .filters(fl -> fl
//                                        .circuitBreaker(cbc -> cbc.setFallbackUri("forward:/default"))
//                                        .setPath("lb:/getAllFromNewTrip"))
//                                .uri("http://localhost:9191")
//                )
//                .route("newTripStatus", routeSpec -> routeSpec
//                                .path("/newStatus")
//                                .filters(fl -> fl
//                                        .circuitBreaker(cbc -> cbc.setFallbackUri("forward:/default"))
//                                        .setPath("lb:/newTripStatus"))
//                                .uri("http://localhost:9191")
//                )
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
                .route("payment", routeSpec -> routeSpec
                        .path("/payment")
                        .filters(gatewayFilterSpec ->
                                gatewayFilterSpec.setPath("lb:/payment")
                                        .circuitBreaker(cbc -> cbc.setFallbackUri("forward:/default")))
                        .uri("http://localhost:9292/"))
                .route("finish", routeSpec -> routeSpec
                        .path("/finish")
                        .filters(gfs ->
                                gfs.setPath("lb:/finishTrip")
                                        .circuitBreaker(cbc -> cbc.setFallbackUri("forward:/default")))
                        .uri("http://localhost:9292/"))
                .route("getPassenger", routeSpec -> routeSpec
                        .path("/getPassenger/{id}")
                        .filters(gfs ->
                                gfs.setPath("lb:/getPassenger/{id}"))
                        .uri("http://localhost:8080/"))
                .route("getDriver", routeSpec -> routeSpec
                        .path("/getDriver/{id}")
                        .filters(gfs ->
                                gfs.setPath("lb:/getDriver/{id}"))
                        .uri("http://localhost:8080/"))
                .route("insertToDB", routeSpec -> routeSpec
                        .path("/insertTrip")
                        .filters(gfs ->
                                gfs.setPath("lb:/insertTrip"))
                        .uri("http://localhost:8080/"))
                .build();
    }

}
