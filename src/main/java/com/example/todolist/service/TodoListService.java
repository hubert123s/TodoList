package com.example.todolist.service;

import com.example.todolist.handler.exception.NotFoundException;
import com.example.todolist.model.TodoList;
import com.example.todolist.repository.TodoListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoListService {
    private final TodoListRepository todoListRepository;

    public List<TodoList> findAll() {
        return todoListRepository.findAll();
    }

    public TodoList add(TodoList todoList) {
         return todoListRepository.save(todoList);
    }

    public void remove(Long id) throws NotFoundException {
        if(!todoListRepository.existsById(id)){
            throw new NotFoundException(id);
        }
        todoListRepository.deleteById(id);
    }

    public TodoList edit(Long id, TodoList todoList) throws NotFoundException {
        if(!todoListRepository.existsById(id)){
            throw new NotFoundException(id);
        }
        return todoListRepository.save(todoList);
    }

}
