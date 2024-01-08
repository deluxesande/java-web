package com.amigos.schoolmanagementsystem.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amigos.schoolmanagementsystem.model.Book;
import com.amigos.schoolmanagementsystem.service.BookService;

@RestController
@RequestMapping(path = "api/v1/book")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping()
    public List<Book> getBooks() {
        return bookService.getBooks();
    }

    @PostMapping
    public Book addNewBook(@RequestBody Book book) {
        return bookService.addNewBook(book);
    }

    @DeleteMapping(path = "{bookId}")
    public boolean deleteBook(Long bookId) {
        return bookService.deleteBook(bookId);
    }

    @PutMapping(path = "{bookId}")
    public Book updateBook(@PathVariable("bookId") Long bookId, @RequestBody Book updatedBook) {
        if (bookId == null) {
            throw new IllegalArgumentException("Book ID cannot be null");
        }
        updatedBook.setId(bookId); // Ensure the ID is set to the one from the path variable
        return bookService.updateBook(updatedBook);
    }

}
