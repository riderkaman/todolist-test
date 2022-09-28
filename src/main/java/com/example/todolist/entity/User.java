package com.example.todolist.entity;

import com.example.todolist.constants.UserStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
