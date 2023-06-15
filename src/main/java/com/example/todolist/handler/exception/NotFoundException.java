package com.example.todolist;

public class NotFoundException extends Exception {
    public NotFoundException(Long id){
        super("Not found Todo with id:".formatted(id));
    }
}
