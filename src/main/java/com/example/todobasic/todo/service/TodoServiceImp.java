package com.example.todobasic.todo.service;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.todobasic.todo.Todo;
import com.example.todobasic.todo.repository.TodoRepository;
import com.example.todobasic.util.Merger;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class TodoServiceImp implements TodoService {
    private final TodoRepository todoRepository;
    private final Merger merger;

    @Override
    public Todo create(Todo todo) {
        todo.setDone(false);
        // todo.setEnd(LocalDate.now().plusDays(1)); 
        return todoRepository.save(todo);
    }

    @Override
    public List<Todo> getAll() {
        return todoRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        todoRepository.deleteById(id);
    }

    @Override
    public Todo update(Todo todo) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        Todo orginal = todoRepository.findById(todo.getId()).orElse(null);
        log.info("updated: {}", orginal);
        if (orginal == null) return null;
        return todoRepository.save((Todo) merger.merge(orginal, todo));
    }

    @Override
    public Optional<Todo> get(Long id) {
        return todoRepository.findById(id);
    }

    @Override
    public List<Todo> findByUsername(String username) {
        return todoRepository.findByUsername(username);
    }
    
    
}
