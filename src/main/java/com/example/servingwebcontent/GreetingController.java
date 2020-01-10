package com.example.servingwebcontent;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class GreetingController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String methodOne(Model model) {
        Iterable<User> all = userRepository.findAll();
        model.addAttribute("bro", all);
        return "greeting";
    }

    @PostMapping
    public String add(@RequestParam String name, @RequestParam String email, Model model) {
        User user = new User(name, email);
        userRepository.save(user);
        Iterable<User> all = userRepository.findAll();
        model.addAttribute("bro", all);
        return "greeting";
    }

    @PostMapping("/coolbro")
    public String methodFilter(@RequestParam String filter, Model model) {
        Iterable<User> byName;
        if (filter != null && !filter.isEmpty()) {
            byName = userRepository.findByName(filter);
        } else {
            byName = userRepository.findAll();
        }

        model.addAttribute("bro", byName);
        return "greeting";
    }


}