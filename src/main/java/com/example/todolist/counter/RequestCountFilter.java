package com.example.todolist;

import jakarta.servlet.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RequestCountFilter implements Filter {
    @Autowired
    private RequestCounter requestCounter;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        requestCounter.increment(); // Zwiększ licznik przy każdym żądaniu
        chain.doFilter(request, response);
    }
}
