package com.amigos.tutorial.book;

import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookConfig {
    @Bean
    CommandLineRunner bookCommandLineRunner(BookRepository bookRepository) {
        return args -> {
            Book book1 = new Book("The Alchemist", "Paulo Coelho");
            Book book2 = new Book("The Monk Who Sold His Ferrari", "Robin Sharma");
            Book book3 = new Book("The Power of Now", "Eckhart Tolle");
            Book book4 = new Book("The Secret", "Rhonda Byrne");

            bookRepository.saveAll(List.of(book1, book2, book3, book4));
        };
    }
}
