package com.example.todolist.repository;

import com.example.todolist.model.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface TodoListRepository extends JpaRepository<TodoList,Long> {
}
