package com.example.forum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ForumApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForumApplication.class, args);

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String rawPassword = "password123"; // Contraseña original
		String hashedPassword = encoder.encode(rawPassword);
		System.out.println("Hashed Password: " + hashedPassword);
	}

}
