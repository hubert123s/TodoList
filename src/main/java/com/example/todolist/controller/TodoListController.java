package com.example.todolist.controller;
import com.example.todolist.handler.exception.NotFoundException;
import com.example.todolist.model.TodoList;
import com.example.todolist.service.TodoListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todo")
public class TodoListController {
    private final TodoListService todoListService;
    @GetMapping
    List<TodoList> findAll(){
        return todoListService.findAll();
    }
    @PostMapping
    TodoList add(@RequestBody TodoList todoList){
       return todoListService.add(todoList);
    }
    @DeleteMapping("/{id}")
    void remove(@PathVariable Long id) throws NotFoundException {
        todoListService.remove(id);
    }
    @PutMapping("/{id}")
    void update(@PathVariable Long id,@RequestBody TodoList todoList) throws NotFoundException {
        todoListService.edit(id,todoList);
    }
}
