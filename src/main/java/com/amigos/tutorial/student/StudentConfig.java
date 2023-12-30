package com.amigos.tutorial.student;

import java.time.LocalDate;
import java.time.Month;

import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            Student john = new Student("John", "john@gmail.com", LocalDate.of(2000, Month.JANUARY, 5), 21);
            Student alex = new Student("Alex", "alex@gmail.com", LocalDate.of(2002, Month.JANUARY, 5), 19);

            studentRepository.saveAll(List.of(john, alex));
        };
    }
}
