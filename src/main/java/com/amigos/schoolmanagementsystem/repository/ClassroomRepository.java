package com.amigos.schoolmanagementsystem.repository;

import org.springframework.web.bind.annotation.ResponseBody;

import com.amigos.schoolmanagementsystem.model.Classroom;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@ResponseBody
public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
    @Query("SELECT c FROM Classroom c WHERE c.name = ?1")
    Optional<Classroom> findClassroomByName(String name);
}
