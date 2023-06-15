package com.example.todolist;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoListService {
    private final TodoListRepository todoListRepository;

    List<TodoList> findAll() {
        return todoListRepository.findAll();
    }

    public void add(TodoList todoList) {
        todoListRepository.save(todoList);
    }

    public void remove(Long id) throws NotFoundException {
        if(!todoListRepository.existsById(id)){
            throw new NotFoundException(id);
        }
        todoListRepository.deleteById(id);
    }

    public void edit(Long id, TodoList todoList) throws NotFoundException {
        if(!todoListRepository.existsById(id)){
            throw new NotFoundException(id);
        }
        todoListRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
        todoListRepository.save(todoList);
    }

}
