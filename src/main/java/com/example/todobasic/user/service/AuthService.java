package com.example.todobasic.user.service;

import com.example.todobasic.user.User;

public interface AuthService {
    User register(User user);
    boolean isAuthenticated(User user);
}