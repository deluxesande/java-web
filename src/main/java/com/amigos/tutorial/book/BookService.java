package com.amigos.tutorial.book;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.amigos.tutorial.student.Student;
import com.amigos.tutorial.student.StudentRepository;

import jakarta.transaction.Transactional;

@Service
public class BookService {
    private final StudentRepository studentRepository;
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository, StudentRepository studentRepository) {
        this.bookRepository = bookRepository;
        this.studentRepository = studentRepository;
    }

    public List<Book> getBooks() {
        return bookRepository.findAll(); // SELECT * FROM book;
    }

    public void addNewBook(Book book) {
        Optional<Book> bookByName = bookRepository.findBookByName(book.getName());
        if (bookByName.isPresent()) {
            throw new IllegalStateException("Book already exists");
        }
        bookRepository.save(book);
    }

    public void deleteBook(Long bookId) {
        boolean exists = bookRepository.existsById(bookId);
        if (!exists) {
            throw new IllegalStateException("Book with id " + bookId + " does not exist");
        }
        bookRepository.deleteById(bookId);
    }

    @Transactional
    public void updateBook(Long bookId, String name, String author) {
        // Checking if book with id exists
        // Throws IllegalStateException if book with id does not exist
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalStateException("Book with id " + bookId + " does not exist"));

        // Checking if name is not null and not empty and is different from the current
        // name
        if (name != null && name.length() > 0 && !book.getName().equals(name)) {
            book.setName(name);
        }

        // Checking if author is not null and not empty and is different from the
        // current author
        if (author != null && author.length() > 0 && !book.getAuthor().equals(author)) {
            book.setAuthor(author);
        }
    }

    public void updateStudent(Long bookId, Long studentId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalStateException("Book with id " + bookId + " does not exist"));

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("Student not found"));

        book.setStudent(student);

        bookRepository.save(book);
    }
}
