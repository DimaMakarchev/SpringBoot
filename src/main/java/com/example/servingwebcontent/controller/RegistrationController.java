package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.dao.User;
import com.example.servingwebcontent.dao.Role;
import com.example.servingwebcontent.dao.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class RegistrationController {
    @Autowired
    UserRepo userRepo;
    @GetMapping(value = "/registration")
    public String registr(){
        return "registration";
    }
    @PostMapping("/registration")
    public String methodAddMarvel(User marvel, Model model){
        User byUsername = userRepo.findByUsername(marvel.getUsername());
        if (byUsername!=null){
        model.addAttribute("broCool","User exists");
        return "registration";
        }
        marvel.setActive(true);
        marvel.setRoles(Collections.singleton(Role.USER));
        userRepo.save(marvel);
        return "redirect:/login";
    }

}
