package com.example.todobasic.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;


@Controller
public class HomeController {
    @GetMapping("/home")
    public String getHome(Model model, HttpServletRequest request) {
        String name = (String) request.getSession().getAttribute("username");
        model.addAttribute("name", name);
        return "home";
    }
}
