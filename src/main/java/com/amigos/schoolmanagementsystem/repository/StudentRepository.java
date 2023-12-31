package com.amigos.schoolmanagementsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.amigos.schoolmanagementsystem.model.Student;

// Long is the type of the primary key
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // SELECT * FROM student WHERE email = ?
    @Query("SELECT s FROM Student s WHERE s.email = ?1")
    Optional<Student> findStudentByEmail(String email);
}
