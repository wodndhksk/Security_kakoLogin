package com.fish.encyclopedia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EncyclopediaApplication {

	public static void main(String[] args) {
		SpringApplication.run(EncyclopediaApplication.class, args);
	}

}
