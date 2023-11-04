package it.discovery.book.web;

import it.discovery.book.domain.Book;
import it.discovery.book.persistence.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("books")
public class BookController {

    private String libraryName = "IT-Discovery Shop";

    private final BookRepository bookRepository;

    @GetMapping("library")
    public String getLibraryName() {
        return libraryName;
    }

    @GetMapping
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("{id}")
    public Book getBook(@PathVariable int id) {
        return bookRepository.findById(id).orElseThrow();
    }

    @PostMapping
    public Book saveBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @PutMapping("{id}")
    public void updateBook(@RequestBody Book book) {
        bookRepository.save(book);
    }


}
