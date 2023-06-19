package com.example.todolist.counter;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class RequestCountController {
    private final RequestCounter requestCounter;

    @GetMapping("/requests/count")
    public int getRequestCount() {
        return requestCounter.getCount();
    }
}
