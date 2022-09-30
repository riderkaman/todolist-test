package com.example.todolist.dto;

import com.example.todolist.constants.TodoStatus;
import lombok.Getter;

import javax.persistence.*;

@Getter
public class TodoDto {

    private Long id;

    private String content;

    private String todoStatus;

    public TodoDto(Long id, String content, String todoStatus) {
        this.id = id;
        this.content = content;
        this.todoStatus = todoStatus;
    }
}
