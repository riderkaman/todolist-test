package com.example.todolist.service;

import com.example.todolist.constants.UserStatus;
import com.example.todolist.entity.User;
import com.example.todolist.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User registerUser(String memberId, String password, String nickname) {
        String encPassword = passwordEncoder.encode(password);
        User user = new User(memberId, encPassword, nickname, UserStatus.USE);

        userRepository.save(user);
        return user;
    }
}
