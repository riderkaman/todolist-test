package com.example.todolist.constants;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum UserStatus {
    USE("USE"),
    WITHDRAW("WITHDRAW");

    private String value;

    UserStatus(String value) {
        this.value = value;
    }
}
