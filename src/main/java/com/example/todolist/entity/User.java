package com.example.todolist.entity;

import com.example.todolist.constants.UserStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = {"todoList"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(unique = true, updatable = false)
    private String memberId;

    private String password;

    @Column(unique = true)
    private String nickname;

    @Column(name = "user_status")
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Todo> todoList = new ArrayList<>();

    @Builder
    public User(String memberId, String password, String nickname, UserStatus userStatus) {
       this.memberId = memberId;
       this.password = password;
       this.nickname = nickname;
       this.userStatus = userStatus;
    }

    public void withdraw() {
        this.userStatus = userStatus.WITHDRAW;
    }
}
