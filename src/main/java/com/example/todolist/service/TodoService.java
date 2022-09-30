package com.example.todolist.service;

import com.example.todolist.constants.TodoStatus;
import com.example.todolist.dto.TodoDto;
import com.example.todolist.entity.Todo;
import com.example.todolist.entity.User;
import com.example.todolist.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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


    public List<TodoDto> findAllTodo() {

        List<Todo> all = todoRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));

        List<TodoDto> todoDtos = getTodoDtos(all);

        return todoDtos;
    }

    public List<TodoDto> findLastOneTodo() {

        List<Todo> all = todoRepository.findByLastOne();

        List<TodoDto> todoDtos = getTodoDtos(all);

        return todoDtos;
    }

    private List<TodoDto> getTodoDtos(List<Todo> all) {
        List<TodoDto> todoDtos = new ArrayList<>();
        for (Todo todo : all) {
            TodoDto dto = new TodoDto(todo.getId(), todo.getContent(), todo.getTodoStatus().getValue());
            todoDtos.add(dto);
        }
        return todoDtos;
    }
}
