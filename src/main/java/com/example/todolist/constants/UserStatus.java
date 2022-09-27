package com.example.todolist.constants;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum UserStatus {
    USE("사용중"),
    WITHDRAW("탈퇴");

    private String code;
    private String value;

    UserStatus(String value) {
        this.value = value;
    }
}
