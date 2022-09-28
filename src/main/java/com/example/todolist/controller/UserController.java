package com.example.todolist.controller;

import com.example.todolist.entity.User;
import com.example.todolist.form.UserRegisterForm;
import com.example.todolist.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
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
        } catch(DataIntegrityViolationException e) {
            bindingResult.reject("signupFailed", e.getMessage());
            return "user/register_form";
        } catch(Exception e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", e.getMessage());
            return "user/register_form";
        }

        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(Authentication authentication) {
        try {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User user = userService.findOneUser(userDetails.getUsername());
            if (user == null) {
                return "user/login_form";
            } else {
                return "redirect:/";
            }
        } catch (NullPointerException e) {
            return "user/login_form";
        }
    }

    @GetMapping("/withdraw")
    public String withdrawForm(Authentication authentication, Model model) {
        try {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User user = userService.findOneUser(userDetails.getUsername());
            if (user == null) {
                return "redirect:/";
            }
            model.addAttribute("nickname", user.getNickname());
        } catch (NullPointerException e) {
            return "redirect:/";
        }

        return "user/withdraw_form";
    }

    @PostMapping("/withdraw")
    public String withdraw(Authentication authentication, @RequestParam("password") String password) {
        try {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User user = userService.findOneUser(userDetails.getUsername());
            if (user == null) {
                return "redirect:/";
            }

            if (userService.isCorrectPassword(user, password)) {
                userService.userWithdraw(user);
            } else {
                return "redirect:/";
            }
        } catch (NullPointerException e) {
            return "redirect:/";
        }

        SecurityContextHolder.clearContext();
        return "redirect:/user/withdrawResult";
    }

    @GetMapping("/withdrawResult")
    public String withdrawResult() {
        return "user/withdraw_result";
    }

}
