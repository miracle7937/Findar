package com.findar.findar;

import com.findar.findar.models.User;
import com.findar.findar.repository.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FindarApplication {

	public static void main(String[] args) {
		SpringApplication.run(FindarApplication.class, args);
	}
	@Bean
	ApplicationRunner init(UserRepository userRepository) {
		return (ApplicationArguments args) ->  dataSetup(userRepository);
	}
	private void dataSetup(UserRepository userRepository) {
		User miracle = new User("miracle.ebuka@gmail.com", "$2a$10$9J.BhFdgiLEVzfhzWAmNTu0GbYkNbYjGw4h4UVnPM8//OVEdJaouK", "ROLE_ADMIN");
		User test = new User("test@gmail.com", "$2a$10$YWDqYU0XJwwBogVycbfPFOnzU7vsG/XvAyQlrN34G/oA1SbhRW.W.", "ROLE_USER");
		userRepository.save(miracle);
		userRepository.save(test);


	}

}
