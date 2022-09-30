package com.example.todolist.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class TodoWriteForm {

    @NotEmpty(message = "내용값은 필수입니다.")
    @Size(max = 250, message = "내용은 250자를 넘어갈 수 없습니다.")
    private String content;

    public TodoWriteForm() {
    }

    public TodoWriteForm(String content) {
        this.content = content;
    }
}
