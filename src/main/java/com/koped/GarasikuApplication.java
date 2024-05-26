package com.koped;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.koped.model.User;
import com.koped.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class GarasikuApplication implements CommandLineRunner {
	
	private final UserRepository userRepo;
	private final BCryptPasswordEncoder passEncoder;

	public static void main(String[] args) {
		SpringApplication.run(GarasikuApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		if (userRepo.findByUsername("Admin") == null) {
	        User user = new User();
	        
	        user.setUsername("Admin");
	        user.setPassword(passEncoder.encode("admin123"));
	        user.setEmail("admin@gmail.com");
	        user.setRole("Admin");
	        
	        userRepo.save(user);
	    }
		
	}

}
