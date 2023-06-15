package com.example.todolist.handler.exception;

public class NotFoundException extends Exception {
    public NotFoundException(Long id){
        super("Not found Todo with id:".formatted(id));
    }
}
