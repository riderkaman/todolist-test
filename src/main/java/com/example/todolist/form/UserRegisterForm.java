package com.example.todolist.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserRegisterForm {

    @Size(min = 3, max = 100, message = "사용자 아이디의 길이는 3자 이상 100자 미만이어야 합니다.")
    @NotEmpty(message = "사용자 아이디는 필수입니다.")
    private String memberId;

    @NotEmpty(message = "비밀번호는 필수입니다.")
    private String password;

    @NotEmpty(message = "비밀번호 확인은 필수입니다.")
    private String passwordConfirm;

    @Size(min = 3, max = 100, message = "닉네임의 길이는 3자 이상 100자 미만이어야 합니다.")
    @NotEmpty(message = "닉네임은 필수입니다.")
    private String nickname;

    public UserRegisterForm() {
    }

    public UserRegisterForm(String memberId, String password, String passwordConfirm, String nickname) {
        this.memberId = memberId;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
        this.nickname = nickname;
    }
}
