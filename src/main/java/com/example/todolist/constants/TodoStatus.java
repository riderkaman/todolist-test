package com.example.todolist.constants;

import lombok.Getter;

@Getter
public enum TodoStatus {

    PLAN("USE"),
    PROGRESS("WITHDRAW"),
    COMPLETE("COMPLETE");

    private String value;

    TodoStatus(String value) {
        this.value = value;
    }
}
