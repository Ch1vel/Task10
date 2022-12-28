package com.example.Task10.controlles;

import com.example.Task10.models.User;
import com.example.Task10.services.RegistrationService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class authController {
    private final RegistrationService registationService;

    public authController(RegistrationService registationService) {
        this.registationService = registationService;
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
    public String registry(@ModelAttribute("user") User user) {
        return "authPlate/registry";
    }

    @PostMapping("/registry")
    public String registration(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) return "authPlate/registry";
        registationService.registration(user);
        return "redirect:/";
    }


}
