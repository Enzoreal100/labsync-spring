package com;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LabsyncApplication {

	public static void main(String[] args) {

        SpringApplication.run(LabsyncApplication.class, args);
	}

	@Bean
	ApplicationRunner applicationRunner() {
		return args -> {
			System.out.println("\n=== Application Started ===");
			System.out.println("Swagger UI: http://localhost:8080/swagger");
			System.out.println("H2 Console: http://localhost:8080/h2-console");
			System.out.println("Health Check: http://localhost:8080/api/health");
			System.out.println("============================\n");
		};
	}
}