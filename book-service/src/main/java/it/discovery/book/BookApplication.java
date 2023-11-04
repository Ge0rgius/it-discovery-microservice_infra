package it.discovery.book;

import it.discovery.book.domain.Book;
import it.discovery.book.persistence.BookRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookApplication.class, args);
    }

    @Bean
    public ApplicationRunner applicationRunner(
            BookRepository bookRepository) {
        return args -> {
            Book book = new Book();
            book.setName("Thinking in Java");
            book.setPages(1150);
            book.setYear(2006);
            bookRepository.save(book);
        };
    }
}
