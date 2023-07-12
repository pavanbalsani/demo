package com.example.demo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(info=@Info(title="Emplyoee Management Web-Service"))
@SpringBootApplication
public class EmplyoeeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmplyoeeServiceApplication.class, args);
	}

}
