package com.example.todobasic.todo.controller;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.todobasic.todo.Todo;
import com.example.todobasic.todo.dto.TodoCreateDto;
import com.example.todobasic.todo.dto.TodoUpdateDto;
import com.example.todobasic.todo.service.TodoService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/todos")
@RequiredArgsConstructor
@Slf4j
public class TodoController {
    private final TodoService todoService;
    private final ModelMapper modelMapper;

    @GetMapping
    public String list(Model model, HttpServletRequest request) {
        String username = (String) request.getSession().getAttribute("username");
        model.addAttribute("todos", todoService.findByUsername(username));
        return "todo/list";
    }

    @GetMapping("/create")
    public String getCreateTodoPage(Model model) {
        model.addAttribute("todoCreateDto", new TodoCreateDto());
        return "todo/create";
    }

    @PostMapping("/create")
    public String createTodo(@Valid @ModelAttribute("todoCreateDto") TodoCreateDto todoCreateDto,
            BindingResult bindingResult, Model model, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "todo/create";
        }
        Todo todo = modelMapper.map(todoCreateDto, Todo.class);
        todo.setUsername((String) request.getSession().getAttribute("username"));
        todoService.create(todo);
        return "redirect:/todos";
    }

    @GetMapping("/delete/{id}")
    public String deleteTodo(@PathVariable Long id) {
        todoService.delete(id);
        return "redirect:/todos";
    }

    @GetMapping("/update/{id}")
    public String getUpdatePage(@PathVariable Long id, Model model) {
        
        return update(id, model);
    }

    @PostMapping("/update/{id}")
    public String updateTodo(@Valid @ModelAttribute("todoUpdateDto") TodoUpdateDto todoUpdateDto,
            BindingResult bindingResult, @PathVariable Long id, Model model) {
        log.info("todo update: {}", todoUpdateDto);
        log.info("has errors: {}", bindingResult.getAllErrors());
        if (bindingResult.hasErrors()) {
            return "/todo/update";
        }
        Todo todo = modelMapper.map(todoUpdateDto, Todo.class);
        todo.setId(id);
        try {
            todoService.update(todo);
        } catch (Exception e) {
            return update(id, model);
        }
        return "redirect:/todos";
    }

    public String update(Long id, Model model) {
        Optional<Todo> todoLook = todoService.get(id);
        if (todoLook.isEmpty())
            return "redirect:/todos";
        Todo todo = todoLook.get();
        TodoUpdateDto todoUpdateDto = new TodoUpdateDto(todo.getTitle(), todo.getDescription(), todo.getDone(), todo.getEnd());
        model.addAttribute("todoUpdateDto", todoUpdateDto);
        return "/todo/update";
    }

}
