package com.example.cache;

import com.example.cache.cache.DeleteThread;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(CacheApplication.class, args);
		Thread thread = new DeleteThread();
		thread.start();
	}
}
