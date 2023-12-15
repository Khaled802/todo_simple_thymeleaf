package com.example.todobasic.user.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.todobasic.user.User;
import com.example.todobasic.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthSeviceImp implements AuthService {
    private final UserRepository userRepository;

    @Override
    public User register(User user) {
        return userRepository.save(user);
    }

    @Override
    public boolean isAuthenticated(User user) {
        Optional<User> userDB = userRepository.findByName(user.getUsername());
        if (userDB.isEmpty()) return false;
        if (!matchPassword(userDB.get(), user.getPassword())) return false;
        return true;
    }

    public boolean matchPassword(User user, String password) {
        return user.getPassword().equals(password);
    }

}
