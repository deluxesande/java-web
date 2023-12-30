package com.amigos.tutorial.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Long is the type of the primary key
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
