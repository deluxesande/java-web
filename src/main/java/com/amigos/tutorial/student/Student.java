package com.amigos.tutorial.student;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import com.amigos.tutorial.book.Book;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity // Hikari
@Table(name = "student", uniqueConstraints = {
        @jakarta.persistence.UniqueConstraint(name = "student_email_unique", columnNames = "email")
})
public class Student {
    @Id
    @SequenceGenerator(name = "student_sequence", sequenceName = "student_sequence", allocationSize = 1)

    @GeneratedValue(generator = "student_sequence", strategy = jakarta.persistence.GenerationType.SEQUENCE)

    @Column(name = "id", updatable = false)
    private Long id;
    @Column(name = "name", nullable = false, columnDefinition = "TEXT")
    private String name;
    @Column(name = "email", nullable = false, columnDefinition = "TEXT")
    private String email;
    @Column(name = "dob")
    private LocalDate dob;
    @Transient
    @Column(name = "age", nullable = false, columnDefinition = "INTEGER")
    private Integer age;

    // Relations
    @OneToMany(mappedBy = "student")
    @JsonManagedReference // Used to avoid infinite recursion
    private List<Book> books;

    public Student() {

    }

    public Student(Long id, String name, String email, LocalDate dob) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dob = dob;
    }

    public Student(String name, String email, LocalDate dob) {
        this.name = name;
        this.email = email;
        this.dob = dob;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return this.dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Integer getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<Book> getBooks() {
        return this.books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ",name='" +
                name +
                '\'' +
                ", email='" +
                email + '\'' +
                ", dob=" +
                dob + ", age=" +
                age + '}';
    }
}
