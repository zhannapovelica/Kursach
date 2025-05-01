package com.example.kurs.controllers;

import com.example.kurs.models.Role;
import com.example.kurs.models.User;
import com.example.kurs.repositories.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    UserRepository userRepository;

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, HttpSession session) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            if (user.get().getPassword().equals(password)) {
                session.setAttribute("user", user.get());
                return "redirect:/enter";
            }
        }
        return "redirect:/profile";
    }
    @PostMapping("/registration")
    public String registration(@RequestParam String name, @RequestParam String surname, @RequestParam String lastName,
                               @RequestParam int age, @RequestParam String email,
                               @RequestParam String password, @RequestParam String role,
                               HttpSession session) {
        User user = new User(surname, name, lastName, age, email, password);
        if(role.equals("1")) {
            user.setRole(Role.USER);
        }
        else if(role.equals("2")) {
            user.setRole(Role.FAMILY_MEMBER);
        }
        userRepository.save(user);
        session.setAttribute("user", user);
        return "redirect:/enter";
    }
}
