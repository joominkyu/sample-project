package org.example.adminbackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String loginId;

    private String password;

    @Enumerated(EnumType.STRING)
    private MemberGrade grade;

    public void changeGrade(MemberGrade grade) {
        this.grade = grade;
    }
}