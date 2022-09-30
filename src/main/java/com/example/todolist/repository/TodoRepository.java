package com.example.todolist.repository;

import com.example.todolist.entity.Todo;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    @Query(value = "select t from Todo t where t.user.id = :id order by t.id desc")
    List<Todo> findByUserId(Long id);

    @Query(value = "select t from Todo t where t.user.id = :id and t.id = (select max(tt.id) from Todo tt)")
    List<Todo> findByLastOne(Long id);
}
