package com.example.todolist.constants;

import lombok.Getter;

@Getter
public enum TodoStatus {

    PLAN("PLAN"),
    PROGRESS("PROGRESS"),
    COMPLETE("COMPLETE");

    private String value;

    TodoStatus(String value) {
        this.value = value;
    }
}
