package com.example.todolist.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;

@Configuration
public class LoginUserAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }

        User user = (User) authentication.getPrincipal();
        return Optional.of(user.getUsername());
        /**
         * TODO 현재 이 기능이 user 를 계속 호출해서 stackoverflowerror 가 나는 문제가 있음.
         * 이 부분에서 auditor가 user의 memberId가 아닌 userId만 받아올 수 있는 방법이 있는지 봐야함.
         *
         * 해결 > createdBy 를 user 테이블의 userId(고유식별자) 대신 memberId(로그인아이디) 로 넣도록 함.
         * 실무에서도 이렇게 처리하는지 궁금함
         */
       /* User user = userService.findOneUser(userDetails.getUsername());
        if (user == null) {
            return null;
        } else {
            return Optional.of(user.getId());
        }*/
    }
}
