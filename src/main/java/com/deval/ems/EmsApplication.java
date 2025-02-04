package com.deval.ems;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@OpenAPIDefinition(
		info = @Info(
				title = "Employee Management System",
				version = "1.0",
				description = "A simple backend for managing employees"
		)
)
@SpringBootApplication
@ImportResource("applicationContext.xml")
public class EmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmsApplication.class, args);
	}

}
