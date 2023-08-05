package com.example.demo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@OpenAPIDefinition(info=@Info(title="Emplyoee Management Web-Service"))
@SpringBootApplication
@EnableCaching
public class EmplyoeeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmplyoeeServiceApplication.class, args);
	}

}
