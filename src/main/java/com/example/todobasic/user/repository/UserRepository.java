package com.example.todobasic.user.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.todobasic.user.User;

@Repository
public class UserRepository {
    private static Map<String, User> users = new HashMap<>();

    static {
        users.put("Khaled", new User("Khaled", "123"));
        users.put("Ali", new User("Ali", "1433"));
        users.put("Elsayed", new User("Elsayed", "393"));
    }

    public User save(User user) {
        users.put(user.getUsername(), user);
        return user;
    }

    public Optional<User> findByName(String name) {
        if (users.containsKey(name))
            return Optional.of(users.get(name));
        return Optional.empty();
    }
}
