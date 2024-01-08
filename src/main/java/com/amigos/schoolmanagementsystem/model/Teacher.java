package com.amigos.schoolmanagementsystem.model;

import java.time.LocalDate;
import java.time.Period;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "teacher", uniqueConstraints = {
        @jakarta.persistence.UniqueConstraint(name = "teacher_email_unique", columnNames = "email")
})
public class Teacher {
    @Id
    @SequenceGenerator(name = "teacher_sequence", sequenceName = "teacher_sequence", allocationSize = 1)
    @GeneratedValue(generator = "teacher_sequence", strategy = jakarta.persistence.GenerationType.SEQUENCE)

    @Column(name = "id", updatable = false)
    private Long id;
    @Column(name = "first_name", nullable = false, columnDefinition = "TEXT")
    private String firstName;
    @Column(name = "last_name", nullable = false, columnDefinition = "TEXT")
    private String lastName;
    @Column(name = "dob")
    private LocalDate dob;
    @Transient
    @Column(name = "age", nullable = false, columnDefinition = "INTEGER")
    private Integer age;
    @Column(name = "email", nullable = false, columnDefinition = "TEXT")
    private String email;
    @Column(name = "salary", nullable = false, columnDefinition = "INTEGER")
    private Integer salary;
    @Column(name = "code", nullable = false, columnDefinition = "INTEGER")
    private Integer code; // To be auto-generated

    // Default constructor
    public Teacher() {
    }

    // Constructor with all fields
    public Teacher(Long id, String firstName, String lastName, String email, Integer salary,
            Integer code, LocalDate dob) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.email = email;
        this.salary = salary;
        this.code = code;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    public String getEmail() {
        return email;
    }

    public Integer getSalary() {
        return salary;
    }

    public Integer getCode() {
        return code;
    }

    public LocalDate getDob() {
        return this.dob;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", email='" + email + '\''
                + '\'' +
                ", salary=" + salary +
                ", code=" + code +
                '}';
    }
}
