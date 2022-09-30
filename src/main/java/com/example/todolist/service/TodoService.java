package com.example.todolist.service;

import com.example.todolist.constants.TodoStatus;
import com.example.todolist.dto.TodoDto;
import com.example.todolist.entity.Todo;
import com.example.todolist.entity.User;
import com.example.todolist.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class TodoService {

    private final TodoRepository todoRepository;

    private final UserService userService;

    public void writeTodo(String content, User user) {
        Todo todo = Todo.builder()
                .content(content)
                .todoStatus(TodoStatus.PLAN)
                .user(user)
                .build();

        todoRepository.save(todo);
    }


    @Transactional(readOnly = true)
    public List<TodoDto> findAllTodo() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userService.findOneUser(userDetails.getUsername());

        List<Todo> all = todoRepository.findByUserId(user.getId());

        List<TodoDto> todoDtos = getTodoDtos(all);

        return todoDtos;
    }

    @Transactional(readOnly = true)
    public List<TodoDto> findLastOneTodo() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userService.findOneUser(userDetails.getUsername());

        List<Todo> all = todoRepository.findByLastOne(user.getId());

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

    public void changeTodoStatus(String status, String idStr) {
        Long id = Long.parseLong(idStr);
        Todo todo = todoRepository.findById(id).get();

        switch (status) {
            case "PLAN":
                todo.changeTodoStatusPlan();
                break;
            case "PROGRESS":
                todo.changeTodoStatusProgress();
                break;
            case "COMPLETE":
                todo.changeTodoStatusComplete();
                break;
            default:
                todo.changeTodoStatusPlan();
                break;
        }

    }
}
