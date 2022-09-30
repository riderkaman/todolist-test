package com.example.todolist.service;

import com.example.todolist.constants.TodoStatus;
import com.example.todolist.entity.Todo;
import com.example.todolist.entity.User;
import com.example.todolist.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public void writeTodo(String content, User user) {
        Todo todo = Todo.builder()
                .content(content)
                .todoStatus(TodoStatus.PLAN)
                .user(user)
                .build();

        todoRepository.save(todo);
    }


}
