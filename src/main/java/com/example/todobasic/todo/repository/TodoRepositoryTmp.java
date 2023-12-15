// package com.example.todobasic.todo.repository;

// import java.time.LocalDate;
// import java.time.LocalDateTime;
// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;
// import java.util.Optional;

// import org.springframework.stereotype.Repository;

// import com.example.todobasic.todo.Todo;

// import lombok.RequiredArgsConstructor;
// import lombok.extern.slf4j.Slf4j;

// @Repository
// @Slf4j
// @RequiredArgsConstructor
// public class TodoRepository {
//     private static Map<Long, Todo> todos = new HashMap<>();


//     static {
//         todos.put(1L, new Todo(1L, "learn java", "complete the java course", LocalDate.now().plusDays(1), false));
//         todos.put(2L, new Todo(2L, "learn spring", "Make project", LocalDate.now().minusDays(1), true));
//         todos.put(3L, new Todo(3L, "learn DSA", "solve 100 problems", LocalDate.now().plusDays(10), false));
//     }


//     public Todo save(Todo todo) {
//         log.info("todo update after merge: {}", todo);
//         if (todos.containsKey(todo.getId())) {
//             todos.put(todo.getId(), todo);
//         } else {
//             Long id = (long) todos.size()+1;
//             todo.setId(id);
//             todos.put(id, todo);
//         }
//         return todo;
//     }
    
//     public List<Todo> findAll() {
//         return todos.values().stream().toList();
//     }

//     public void deleteById(Long id) {
//         todos.remove(id);
//         log.info("todos: {}", todos);
//         log.info(id.toString());
//     }

//     public Optional<Todo> findById(Long id) {
//         if (todos.containsKey(id)) return Optional.of(todos.get(id));
//         return Optional.empty();
//     }
// }
