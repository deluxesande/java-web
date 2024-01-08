package com.amigos.schoolmanagementsystem.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "classroom", uniqueConstraints = {
        @jakarta.persistence.UniqueConstraint(name = "classroom_name_unique", columnNames = "name")
})
public class Classroom {
    @Id
    @SequenceGenerator(name = "classroom_sequence", sequenceName = "classroom_sequence", allocationSize = 1)
    @GeneratedValue(generator = "classroom_sequence", strategy = jakarta.persistence.GenerationType.SEQUENCE)

    @Column(name = "id", updatable = false)
    private Long id;
    @Column(name = "name", nullable = false, columnDefinition = "TEXT")
    private String name;
    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    // Default constructor
    public Classroom() {
    }

    // Constructor with all fields
    public Classroom(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toString() {
        return "Classroom{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
