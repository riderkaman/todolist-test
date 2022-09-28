package com.example.todolist.service;

import com.example.todolist.constants.UserStatus;
import com.example.todolist.entity.User;
import com.example.todolist.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
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

        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("이미 등록된 사용자입니다.");
        }

        return user;
    }

    public User findOneUser(String memberId) {
        return userRepository.findByMemberId(memberId).orElse(null);
    }

    public boolean isCorrectPassword(User user, String password) {
        return passwordEncoder.matches(password, user.getPassword());
    }

    public void userWithdraw(User user) {
        user.withdraw();
        userRepository.save(user);
    }

}
