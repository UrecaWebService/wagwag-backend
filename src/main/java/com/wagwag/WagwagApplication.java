package com.wagwag;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableFeignClients // Feign Client를 사용할 것임을 알려줍니다.
@EnableJpaAuditing
public class WagwagApplication {

	public static void main(String[] args) {
		SpringApplication.run(WagwagApplication.class, args);
	}

}
