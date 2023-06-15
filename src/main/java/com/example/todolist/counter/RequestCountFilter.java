package com.example.todolist.counter;

import com.example.todolist.counter.RequestCounter;
import jakarta.servlet.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RequestCountFilter implements Filter {
    private RequestCounter requestCounter;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        requestCounter.increment();
        chain.doFilter(request, response);
    }
}
