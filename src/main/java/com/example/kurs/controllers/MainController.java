package com.example.kurs.controllers;

import com.example.kurs.data.LoginRequest;
import com.example.kurs.data.RegisterRequest;
import com.example.kurs.models.User;
import com.example.kurs.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class MainController {
    @Autowired
    UserRepository userRepository;
    @GetMapping("/")
    public String index() {
        return "index.html";
    }
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
        User user = new User(registerRequest.getName(), registerRequest.getSurname(),
                registerRequest.getLastName(),registerRequest.getAge(), registerRequest.getEmail(), registerRequest.getPassword());
        userRepository.save(user);
        return ResponseEntity.ok().body(registerRequest.getEmail());
    }
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginRequest loginRequest) {
        if (loginRequest.getEmail().equals("") || loginRequest.getPassword().equals("")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        Iterable<User> users = userRepository.findAll();
        for (User user : users) {
            if(user.getEmail().equals(loginRequest.getEmail())  && user.getPassword().equals(loginRequest.getPassword())) {
                System.out.println(user.toString());
                return ResponseEntity.ok(user);
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }
    @GetMapping("/cabinet")
    public String profile() {
        return "profile.html";
    }
}
