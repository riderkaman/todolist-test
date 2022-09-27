package com.example.todolist.controller;

import com.example.todolist.config.SecurityConfig;
import com.example.todolist.form.UserRegisterForm;
import com.example.todolist.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.net.BindException;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Import(SecurityConfig.class)
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private WebApplicationContext ctx;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
                .addFilter(new CharacterEncodingFilter("UTF-8", true))
                .build();
    }

    // TODO 테스트는 다시 짜볼것. 현재 무조건 통과하는 기대하지 않았던 동작을 하고 있음.
    @Test
    public void 메인페이지() throws Exception {

        UserRegisterForm userRegisterForm = new UserRegisterForm();
        userRegisterForm.setMemberId("admin");
        userRegisterForm.setPassword("1");
        userRegisterForm.setPasswordConfirm("1");
        userRegisterForm.setNickname("어드민");

        String params = objectMapper.writeValueAsString(userRegisterForm);

        mockMvc.perform(post("/user/register").content(params))
                .andDo(print())
                ;
    }

    @Test
    public void 메인페이지_파라미터_이상() throws Exception {

        UserRegisterForm userRegisterForm = new UserRegisterForm();
        userRegisterForm.setMemberId("a");
        userRegisterForm.setPassword("1");
        userRegisterForm.setPasswordConfirm("1");
        userRegisterForm.setNickname("어드민");

        String params = objectMapper.writeValueAsString(userRegisterForm);

        mockMvc.perform(post("/user/register").content(params))
                .andExpect(model().hasErrors())
                .andDo(print())
        ;
    }

}