package com.example.todobasic.user.controller;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.todobasic.user.User;
import com.example.todobasic.user.dto.LoginDto;
import com.example.todobasic.user.dto.RegisterDto;
import com.example.todobasic.user.service.AuthService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final AuthService authService;
    private final ModelMapper modelMapper;

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("registerDto", new RegisterDto());
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("registerDto") RegisterDto registerDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // There are validation errors, return to the form page
            return "auth/register";
        }
        User user = modelMapper.map(registerDto, User.class);
        authService.register(user);
        return "redirect:login";
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("loginDto", new LoginDto());
        return "auth/login";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("loginDto") LoginDto loginDto, BindingResult bindingResult,
            HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            // There are validation errors, return to the form page
            return "auth/login";
        }
        User user = modelMapper.map(loginDto, User.class);
        if (!authService.isAuthenticated(user)) {
            return "redirect:login";
        }
        request.getSession().setAttribute("username", user.getUsername());
        return "redirect:/home";
    }
}
