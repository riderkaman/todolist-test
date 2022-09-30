package com.example.todolist.config;

import com.example.todolist.entity.User;
import com.example.todolist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

@Configuration
public class LoginUserAuditorAware implements AuditorAware<Long> {

    @Autowired
    private UserService userService;


    @Override
    public Optional<Long> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        User user = userService.findOneUser(userDetails.getUsername());
        if (user == null) {
            return null;
        } else {
            return Optional.of(user.getId());
        }
    }
}
