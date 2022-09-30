package com.example.todolist.controller;

import com.example.todolist.dto.TodoDto;
import com.example.todolist.entity.User;
import com.example.todolist.form.TodoWriteForm;
import com.example.todolist.service.TodoService;
import com.example.todolist.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/todo")
public class TodoController {

    private final TodoService todoService;

    private final UserService userService;

    @GetMapping("/list_view")
    public String listView() {
        return "todo/list_view";
    }

    @GetMapping("/write")
    public String writeForm(Model model, TodoWriteForm todoWriteForm) {
        model.addAttribute("todoWriteForm", todoWriteForm);
        return "todo/write_form";
    }

    @PostMapping("/write")
    public String write(@Valid TodoWriteForm todoWriteForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "todo/write_form";
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userService.findOneUser(userDetails.getUsername());

        todoService.writeTodo(todoWriteForm.getContent(), user);;

        return "todo/list_view";
    }

    @GetMapping("/list")
    @ResponseBody
    public List<TodoDto> todoList(String sortSep) {

        List<TodoDto> todoList = new ArrayList<>();

        if ("ALL".equals(sortSep)) {
            todoList = todoService.findAllTodo();
        } else if ("RECENT".equals(sortSep)) {
            todoList = todoService.findLastOneTodo();
        }

        return todoList;
    }
}
