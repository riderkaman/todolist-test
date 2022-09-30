package com.example.todolist.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/todo")
public class TodoController {


    @GetMapping("/list_view")
    public String listView() {
        return "todo/list_view";
    }




}
