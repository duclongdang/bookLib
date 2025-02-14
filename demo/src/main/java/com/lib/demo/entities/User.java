package com.lib.demo.entities;

import java.time.LocalDate;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;
import lombok.*;


@Table(name = "User")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long userId;
    @Column(name = "email")
    private String email;
    @Column(name="user_name")
    private String username;
    @Column(name="password")
    private String password;
    @Column(name="status")
    private String status;
    @Column(name="avatar")
    private String avatar;
    @Column(name = "phone")
    private String phone;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    @Column
    private LocalDateTime createdAt;
    @ManyToOne
    private Role role ;
    @OneToMany(mappedBy = "user")
    private List<Notification> notifications;
    @ManyToMany
    @JoinTable(name = "waitlist" ,joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name="book_id"))
    private Set<Book> books;
    @OneToMany(mappedBy = "user")
    private List<Checkout> checkouts;
    @OneToMany(mappedBy = "user")
    private List<Hold> holds;

//    public Long getUserId() {
//        return userId;
//    }
//
//    public void setUserId(Long userId) {
//        this.userId = userId;
//    }
//
//    public Set<Role> getRoles() {
//        return roles;
//    }
//
//    public void setRoles(Set<Role> roles) {
//        this.roles = roles;
//    }
//
//    public LocalDate getDateOfBirth() {
//        return dateOfBirth;
//    }
//
//    public void setDateOfBirth(LocalDate dateOfBirth) {
//        this.dateOfBirth = dateOfBirth;
//    }
//
//    public String getPhone() {
//        return phone;
//    }
//
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }
//
//    public String getAvatar() {
//        return avatar;
//    }
//
//    public void setAvatar(String avatar) {
//        this.avatar = avatar;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
}

