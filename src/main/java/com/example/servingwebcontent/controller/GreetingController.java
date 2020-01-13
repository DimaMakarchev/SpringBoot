package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.dao.Messages;
import com.example.servingwebcontent.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String methodOne(Model model) {
        return "greeting";
    }
    @GetMapping("/main")
    public String methodMain(Model model) {
        Iterable<Messages> all = userRepository.findAll();
        model.addAttribute("bro",all);
        return "main";
    }


    @PostMapping("/main")
    public String add(@RequestParam String name, @RequestParam String email, Model model) {
        Messages user = new Messages(name, email);
        userRepository.save(user);
        Iterable<Messages> all = userRepository.findAll();
        model.addAttribute("bro", all);
        return "main";
    }

    @PostMapping("/filter")
    public String methodFilter(@RequestParam String filter, Model model) {
        Iterable<Messages> byName;
        if (filter != null && !filter.isEmpty()) {
            byName = userRepository.findByName(filter);
        } else {
            byName = userRepository.findAll();
        }

        model.addAttribute("bro", byName);
        return "main";
    }


}