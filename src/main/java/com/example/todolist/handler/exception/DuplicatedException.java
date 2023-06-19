package com.example.todolist.handler.exception;

public class DuplicatedException extends Exception {
    public DuplicatedException(String message) {
        super("Description exist");
    }
}
