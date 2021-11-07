package com.example.newTripService.controller;

import com.example.newTripService.config.WebSocketConfiguration;
import com.example.newTripService.dto.CacheDTO;
import com.example.newTripService.dto.UserDTO;
import com.example.newTripService.dto.UserRequestDTO;
import com.example.newTripService.entity.User;
import com.example.newTripService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

//    private final WebClient.Builder userBuilder;

//    public UserController(WebClient.Builder userBuilder) {
//        this.userBuilder = userBuilder;
//    }

    private Map<String,String> tokens = new HashMap<>();

    @Autowired
    private UserService userService;

    @Autowired
    WebSocketConfiguration configuration;

//        private final Flux <User> userFlux;

//    private final Map<Long, AtomicInteger> countPerSecond = new ConcurrentHashMap<>();

    @PostMapping(
//            produces = MediaType.TEXT_EVENT_STREAM_VALUE,
            value = "/newTrip"
    )
    UserDTO post(@RequestHeader("username") String username, @RequestHeader("token") String token, @RequestBody UserDTO userDTO){
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        System.out.println("username: " + username);
//        System.out.println("token: " + token);
//        System.out.println("request: " + userDTO);
//        headers.set("token", token);
//        HttpEntity<Object> request = new HttpEntity<>(userRequestNewDTO, headers);
//        String url = "http://localhost:9393/getResponse";
//        ResponseEntity<Object> response;
//        try {
//            response = restTemplate.postForEntity(url, request, Object.class);
//            System.out.println(response);
//            System.out.println("status: " + response.getStatusCode());
//            if (response.getStatusCode() == HttpStatus.OK) {
//                System.out.println("Got response from cache");
//                return response;
//            }
//        var now = System.currentTimeMillis();
//        var second = (now / 1000);
//        var countForTheCurrentSecond = this.countPerSecond.compute(second, (aLong, atomicInteger) -> {
//            if (atomicInteger == null) {
//                atomicInteger = new AtomicInteger(0);
//            }
//            atomicInteger.incrementAndGet();
//            return atomicInteger;
//        });
//        System.out.println("There have been "+ countForTheCurrentSecond.get() + " requests for the second " + second);
//        System.out.println("username: " + username);
//        System.out.println("token: " + token);
        if(tokens.containsKey(username) && tokens.get(username).equals(token)) {
            RestTemplate restTemplate = configuration.getRestTemplate();
//            setTimeout(restTemplate, 1);
            HttpHeaders headers = new HttpHeaders();
            headers.set("token", token);
            System.out.println(userDTO);
            UserDTO userDTOSend = UserDTO.builder()
                    .id(userDTO.getId())
                    .destination(userDTO.getDestination())
                    .userType(userDTO.getUserType())
                    .location(userDTO.getLocation())
                    .tripType(userDTO.getTripType())
                    .build();
            UserDTO user = userService.addUser(userDTO);
            CacheDTO cacheDTO = CacheDTO.builder()
                    .endpoint("http://localhost:9999/new")
                    .body(userDTOSend)
                    .response(new ResponseEntity<>(user, HttpStatus.OK))
                    .build();
            HttpEntity<CacheDTO> request = new HttpEntity<>(cacheDTO, headers);
            String url = "http://localhost:9393/caching";
            restTemplate.postForEntity(url, request, Void.class);
            System.out.println(request);
            return user;
        }
        return null;
    }

    @PostMapping("/token")
    void postToken(@RequestBody UserRequestDTO requestDTO){
//        System.out.println("username: " + requestDTO.getUsername());
//        System.out.println("token: " + requestDTO.getToken());
        tokens.put(requestDTO.getUsername(), requestDTO.getToken());
        System.out.println(tokens);
    }

    @GetMapping("/getAllFromNewTrip")
    List<User> getAll(){
        return userService.getAll();
    }

//    @GetMapping(
//            produces = MediaType.TEXT_EVENT_STREAM_VALUE,
//            value = "/newTrip"
//    )
//    Flux <User> get(){
//        return this.userFlux;
//    }
//    @RequestMapping(value = "/newTrip", method = RequestMethod.POST)
//    public String newUser(@ModelAttribute("userUpdateDTO") UserDTO userDTO) {
//        userService.addUser(userDTO);
//        return "user added";
//    }
//    @RequestMapping(value = "/newTrip", method = RequestMethod.GET)
//    public User getUser() {
//        return userService.getUser();
//    }
}
