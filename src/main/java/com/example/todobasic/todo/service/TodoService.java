package com.example.todobasic.todo.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;

import com.example.todobasic.todo.Todo;

public interface TodoService {
    Todo create(Todo todo);

    List<Todo> getAll();

    List<Todo> findByUsername(String username);

    void delete(Long id);

    Todo update(Todo todo) throws InstantiationException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException, NoSuchMethodException, SecurityException;

    Optional<Todo> get(Long id);
}
