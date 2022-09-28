package com.example.todolist.controller;

import com.example.todolist.form.UserRegisterForm;
import com.example.todolist.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/register")
    public String registerUser(Model model, UserRegisterForm userRegisterForm) {
        model.addAttribute("userRegisterForm", userRegisterForm);
        return "user/register_form";
    }

    @PostMapping("/register")
    public String registerUser(@Valid UserRegisterForm userRegisterForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user/register_form";
        }

        if (!userRegisterForm.getPassword().equals(userRegisterForm.getPasswordConfirm())) {
            bindingResult.rejectValue("passwordConfirm", "passwordNotConfirm",
                    "패스워드가 일치하지 않습니다.");
        }

        try {
            userService.registerUser(userRegisterForm.getMemberId(), userRegisterForm.getPassword(), userRegisterForm.getNickname());
        }catch(DataIntegrityViolationException e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
            return "user/register_form";
        }catch(Exception e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", e.getMessage());
            return "user/register_form";
        }

        return "redirect:/";
    }


}
