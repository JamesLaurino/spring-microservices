package com.hexagon.cloudgateway;

import com.hexagon.cloudgateway.dto.UserDto;
import com.hexagon.cloudgateway.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class CloudGatewayApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(CloudGatewayApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		userRepository.deleteAll()
				.doOnTerminate(() -> System.out.println("All user is deleted"))
				.subscribe(); // Pour lancer la tâche réactive

		UserDto userDto = UserDto.builder()
				.id(1)
				.password(passwordEncoder().encode("1234"))
				.username("admin")
				.role("ROLE_ADMIN")
				.build();

		UserDto userDto2 = UserDto.builder()
				.id(2)
				.password(passwordEncoder().encode("1234"))
				.username("james")
				.role("ROLE_USER")
				.build();


		System.out.println("Inserting user with ID: " + userDto.getId());
		userRepository.save(userDto)
				.doOnTerminate(() -> System.out.println("User saved"))
				.subscribe();

		System.out.println("Inserting user with ID: " + userDto2.getId());
		userRepository.save(userDto2)
				.doOnTerminate(() -> System.out.println("User saved"))
				.subscribe();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
}
