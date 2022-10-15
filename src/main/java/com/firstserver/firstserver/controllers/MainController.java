package com.firstserver.firstserver.controllers;

import com.firstserver.firstserver.models.User;
import com.firstserver.firstserver.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/main")
    public String main(Model model) {
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        model.addAttribute("title", "first-server");
        return "index";
    }
}