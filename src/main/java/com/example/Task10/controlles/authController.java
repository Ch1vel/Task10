package com.example.Task10.controlles;

import com.example.Task10.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class authController {
    private final UserRepository userRepository;

    public authController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String startPage() {
        return "start";
    }
    @GetMapping("/login")
    public String login() {
        return "authPlate/login";
    }

    @GetMapping("/registry")
    public String registry() {
        return "authPlate/registry";
    }

    @PostMapping()
}
