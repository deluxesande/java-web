package com.amigos.tutorial.student;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amigos.tutorial.book.Book;
import com.amigos.tutorial.book.BookRepository;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final BookRepository bookRepository;

    public StudentService(StudentRepository studentRepository, BookRepository bookRepository) {
        this.studentRepository = studentRepository;
        this.bookRepository = bookRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll(); // SELECT * FROM student;

    }

    public void addNewStudent(Student student) {
        Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());
        if (studentByEmail.isPresent()) {
            throw new IllegalStateException("Email taken");
        }
        studentRepository.save(student);
    }

    // Adding a book to a student
    public void addBook(Long studentId, Book book) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("Student not found"));
        book.setStudent(student);

        bookRepository.save(book);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if (!exists) {
            throw new IllegalStateException("Student with id " + studentId + " does not exist");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("Student with id " + studentId + " does not exist"));
        if (name != null && name.length() > 0 && !student.getName().equals(name)) {
            student.setName(name);
        }
        if (email != null && email.length() > 0 && !student.getEmail().equals(email)) {
            Optional<Student> studentByEmail = studentRepository.findStudentByEmail(email);
            if (studentByEmail.isPresent()) {
                throw new IllegalStateException("Email taken");
            }
            student.setEmail(email);
        }
    }

    // Deleting a book from a student
    @Transactional
    public void deleteBook(Long studentId, Long bookId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("Student with id " + studentId + " does not exist"));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalStateException("Book with id " + bookId + " does not exist"));

        student.getBooks().remove(book);
        studentRepository.save(student);
    }
}
