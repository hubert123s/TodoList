package com.example.todolist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestCountController {
    @Autowired
    private RequestCounter requestCounter;

    @GetMapping("/requests/count")
    public int getRequestCount() {
        return requestCounter.getCount(); // Zwróć aktualną liczbę żądań
    }
}
