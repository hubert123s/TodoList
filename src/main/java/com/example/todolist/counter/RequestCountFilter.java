package com.example.todolist.counter;

import jakarta.servlet.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class RequestCountFilter implements Filter {
    private final RequestCounter requestCounter;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        requestCounter.increment();
        chain.doFilter(request, response);
    }
}
