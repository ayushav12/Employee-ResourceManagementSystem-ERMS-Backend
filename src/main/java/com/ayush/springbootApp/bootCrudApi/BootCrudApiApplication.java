package com.ayush.springbootApp.bootCrudApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class BootCrudApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootCrudApiApplication.class, args);
	}

}
