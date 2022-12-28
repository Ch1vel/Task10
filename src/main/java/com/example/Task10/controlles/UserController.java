package com.example.Task10.controlles;

import com.example.Task10.models.User;
import com.example.Task10.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/user")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String show(Authentication authentication,Model model) {
        model.addAttribute("user",userRepository.findByNickname(authentication.getName()).get());
        return "userPlate/showUser";
    }
}
