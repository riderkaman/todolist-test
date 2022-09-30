package com.example.todolist.service;

import com.example.todolist.constants.TodoStatus;
import com.example.todolist.entity.Todo;
import com.example.todolist.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class TodoServiceTest {

    @Autowired
    private TodoService todoService;

    @Autowired
    private TodoRepository todoRepository;

    @Test
    void 날짜_입력_자동으로_되는지_테스트() {
        // Given
        LocalDateTime now = LocalDateTime.of(2022, 9, 30, 0, 0, 0);
        todoService.writeTodo("testContent", null);

        // When
        List<Todo> todoList = todoRepository.findAll();

        // Then
        Todo todo = todoList.get(0);

        System.out.println(todo.getCreatedAt());
        assertThat(now).isBefore(LocalDateTime.now());
    }



}