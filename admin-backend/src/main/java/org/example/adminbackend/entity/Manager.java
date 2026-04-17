package org.example.adminbackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "manager")
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String loginId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ManagerGrade grade;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public Manager(String loginId, String password, String name, ManagerGrade grade) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.grade = grade;
        this.createdAt = LocalDateTime.now();
    }

    public void changePassword(String password) {
        this.password = password;
    }

    public void changeGrade(ManagerGrade grade) {
        this.grade = grade;
    }
}