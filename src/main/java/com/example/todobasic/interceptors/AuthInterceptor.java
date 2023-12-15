package com.example.todobasic.interceptors;


import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.example.todobasic.todo.Todo;
import com.example.todobasic.todo.repository.TodoRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Autowired
    private TodoRepository todoRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // Your authentication logic goes here
        // You can access request headers, session, etc., to check authentication

        // For demonstration purposes, let's assume a simple check
        String authToken = (String) request.getSession().getAttribute("username");
        if (authToken == null) {
            response.sendRedirect("/auth/login");
            return false;
        }

        if (request.getRequestURI().startsWith("/todos/") && request.getRequestURI().length() > "/todos/".length()) {
            String[] comps = request.getRequestURI().split("/");
            Long id = -1l;
            for (String part : comps) {
                if (isLong(part)) {
                    id = Long.valueOf(part);
                    break;
                }
            }
            if (id != -1) {
                Optional<Todo> todo = todoRepository.findById(id);
                if (todo.isPresent()) {
                    if (!todo.get().getUsername().equals((String)request.getSession().getAttribute("username"))) {
                        response.sendRedirect("/todos");
                        return false;
                    }
                }
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        // This method is called after the controller method is executed
        // You can perform post-processing tasks here if needed
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception ex) throws Exception {
        // This method is called after the complete request has been handled
        // You can perform cleanup tasks here if needed
    }


    public static boolean isLong(String str) {
        try {
            Long.parseLong(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
