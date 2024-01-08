package com.amigos.schoolmanagementsystem.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "parent", uniqueConstraints = {
        @jakarta.persistence.UniqueConstraint(name = "parent_email_unique", columnNames = "email")
})
public class Parent {
    @Id
    @SequenceGenerator(name = "parent_sequence", sequenceName = "parent_sequence", allocationSize = 1)
    @GeneratedValue(generator = "parent_sequence", strategy = jakarta.persistence.GenerationType.SEQUENCE)

    @Column(name = "id", updatable = false)
    private Long id;
    @Column(name = "first_name", nullable = false, columnDefinition = "TEXT")
    private String firstName;
    @Column(name = "last_name", nullable = false, columnDefinition = "TEXT")
    private String lastName;
    @Column(name = "email", nullable = false, columnDefinition = "TEXT")
    private String email;
    @Transient
    @Column(name = "age", nullable = false, columnDefinition = "INTEGER")
    private Integer age;
    @Column(name = "occupation", nullable = false, columnDefinition = "TEXT")
    private String occupation;
    @Column(name = "address", nullable = false, columnDefinition = "TEXT")
    private String address;
    @Column(name = "phone", nullable = false, columnDefinition = "TEXT")
    private String phone;
    @Column(name = "dob")
    private Integer dob;

    public Parent() {

    }

    public Parent(Long id, String firstName, String lastName, String email, String occupation, String address,
            String phone, Integer dob) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.occupation = occupation;
        this.address = address;
        this.phone = phone;
        this.dob = dob;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getOccupation() {
        return occupation;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public Integer getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public Integer getDob() {
        return dob;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setDob(Integer dob) {
        this.dob = dob;
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

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String toString() {
        return "Parent{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", occupation='" + occupation + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", dob='" + dob + '\'' +
                '}';
    }
}
