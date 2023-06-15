package com.example.todolist.counter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class RequestCountController {
    private RequestCounter requestCounter;

    @GetMapping("/requests/count")
    public int getRequestCount() {
        return requestCounter.getCount();
    }
}
