package com.hexagon.schoolservice;

import com.hexagon.schoolservice.entity.School;
import com.hexagon.schoolservice.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SchoolServiceApplication implements CommandLineRunner
{
	@Autowired
	private SchoolRepository schoolRepository;

	public static void main(String[] args) {
		SpringApplication.run(SchoolServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		schoolRepository.deleteAll();

		School school1 = School.builder()
				.id(1)
				.schoolName("UCL")
				.location("MONS")
				.principalName("Blondel")
				.build();

		School school2 = School.builder()
				.id(2)
				.schoolName("ULG")
				.location("LIEGE")
				.principalName("")
				.build();

		schoolRepository.save(school1);
		schoolRepository.save(school2);
	}
}
