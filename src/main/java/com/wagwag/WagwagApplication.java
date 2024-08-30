package com.wagwag;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class WagwagApplication {

	public static void main(String[] args) {
		SpringApplication.run(WagwagApplication.class, args);
	}

}
