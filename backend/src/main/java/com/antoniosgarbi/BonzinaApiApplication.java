package com.antoniosgarbi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

@SpringBootApplication
public class BonzinaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BonzinaApiApplication.class, args);
	}

	@Bean
	public Argon2PasswordEncoder getPasswordEncoder() {
		return new Argon2PasswordEncoder();
	}

}
