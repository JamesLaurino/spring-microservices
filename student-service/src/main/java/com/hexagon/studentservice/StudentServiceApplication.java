package com.hexagon.studentservice;

import com.hexagon.studentservice.model.Student;
import com.hexagon.studentservice.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class StudentServiceApplication implements CommandLineRunner {

	@Autowired
	private StudentRepository studentRepository;

	public static void main(String[] args) {
		SpringApplication.run(StudentServiceApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

	@Override
	public void run(String... args) throws Exception {

		studentRepository.deleteAll();

		Student student1 = Student.builder()
				.id(UUID.randomUUID().toString())
				.name("James")
				.age(30)
				.gender("M")
				.schoolId(1)
				.build();

		Student student2 = Student.builder()
				.id(UUID.randomUUID().toString())
				.name("Billy")
				.age(25)
				.gender("M")
				.schoolId(1)
				.build();

		Student student3 = Student.builder()
				.id(UUID.randomUUID().toString())
				.name("Julie")
				.age(23)
				.gender("F")
				.schoolId(2)
				.build();

		studentRepository.saveAll(List.of(student1, student2,student3));
	}
}