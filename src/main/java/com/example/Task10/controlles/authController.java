package com.example.Task10.controlles;

import com.example.Task10.models.User;
import com.example.Task10.services.RegistrationService;
import com.example.Task10.util.UserValidator;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class authController {
    private final RegistrationService registationService;
    private final UserValidator userValidator;

    public authController(RegistrationService registationService, UserValidator userValidator) {
        this.registationService = registationService;
        this.userValidator = userValidator;
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
        userValidator.validate(user,bindingResult);
        if(bindingResult.hasErrors()) return "authPlate/registry";
        registationService.registration(user);
        return "redirect:/";
    }


}
