package com.SAIB.IdeationPlatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class IdeationPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(IdeationPlatformApplication.class, args);
	}

}
