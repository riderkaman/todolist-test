package com.example.todolist.controller;

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

import javax.validation.Valid;

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

}
