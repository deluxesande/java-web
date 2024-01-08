package com.amigos.schoolmanagementsystem.service;

import java.util.List;
import java.util.Optional;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.amigos.schoolmanagementsystem.model.Book;
import com.amigos.schoolmanagementsystem.repository.BookRepository;

import jakarta.transaction.Transactional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getBooks() {
        return bookRepository.findAll(); // SELECT * FROM book;
    }

    public Book addNewBook(Book book) {
        Optional<Book> bookByName = bookRepository.findBookByName(book.getName());
        if (bookByName.isPresent()) {
            throw new IllegalStateException("Book already exists");
        }
        return bookRepository.save(book);
    }

    public boolean deleteBook(Long bookId) {
        boolean exists = bookRepository.existsById(bookId);
        if (!exists) {
            throw new IllegalStateException("Book with id " + bookId + " does not exist");
        }
        bookRepository.deleteById(bookId);

        return exists;
    }

    @Transactional
    public Book updateBook(Book updatedBook) {
        Long bookId = updatedBook.getId();
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new NoSuchElementException("Book with id " + bookId + " does not exist"));

        Optional.ofNullable(updatedBook.getName())
                .filter(StringUtils::hasText)
                .filter(n -> !n.equals(book.getName()))
                .ifPresent(book::setName);

        Optional.ofNullable(updatedBook.getAuthor())
                .filter(StringUtils::hasText)
                .filter(a -> !a.equals(book.getAuthor()))
                .ifPresent(book::setAuthor);

        return bookRepository.save(book);
    }
}
