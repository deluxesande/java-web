package com.amigos.schoolmanagementsystem.repository;

import java.util.Optional;

import com.amigos.schoolmanagementsystem.model.School;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {
    @Query("SELECT s FROM School s WHERE s.name = ?1")
    Optional<School> findSchoolByName(String name); // Optional is a container object which may or may not contain a
                                                    // non-null value. If a value is present, isPresent() will return
                                                    // true and get() will return the value.
}
