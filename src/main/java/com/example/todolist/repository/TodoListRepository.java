package com.example.todolist.repository;

import com.example.todolist.model.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TodoListRepository extends JpaRepository<TodoList, Long> {
    boolean existsByDescription(String description);
    @Query("SELECT t FROM TodoList t WHERE t.description LIKE %:word%")
    List<TodoList> searchByWords(@Param("word") String word);
}
