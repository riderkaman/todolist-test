package com.example.todolist.entity;

import com.example.todolist.constants.TodoStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString(exclude = {"user"})
public class Todo extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_id")
    private Long id;

    private String content;

    @Column(name = "todo_status")
    @Enumerated(EnumType.STRING)
    private TodoStatus todoStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user = new User();

    @Builder
    public Todo(String content, TodoStatus todoStatus, User user) {
        this.content = content;
        this.todoStatus = todoStatus;
        this.user = user;
    }

    public void changeTodoStatusPlan() {
        this.todoStatus = todoStatus.PLAN;
    }

    public void changeTodoStatusProgress() {
        this.todoStatus = todoStatus.PROGRESS;
    }

    public void changeTodoStatusComplete() {
        this.todoStatus = todoStatus.COMPLETE;
    }

}
