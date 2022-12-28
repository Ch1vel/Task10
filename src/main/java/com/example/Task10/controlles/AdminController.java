package com.example.Task10.controlles;



import com.example.Task10.models.User;
import com.example.Task10.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String welcomePage() {
        return "adminPlate/welcomePage";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("user") User user){
        return "adminPlate/add";
    }

    @PostMapping
    public String addUserToBD(@ModelAttribute("user") @Valid User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()) return "adminPlate/add";
        userService.save(user);
        return "redirect:";
    }

    @GetMapping("/users")
    public String showUserList(Model model) {
        model.addAttribute("list",userService.getAllUser());
        return "adminPlate/listOfUsers";
    }

    @DeleteMapping("/users/{id}/delete")
    public String deleteUser(@PathVariable("id") int id) {
        userService.removeUser(id);
        return "redirect:/users";
    }

    @GetMapping("/users/{id}/edit")
    public String editUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user",userService.findUser(id));
        return "adminPlate/edit";
    }

    @PatchMapping("/users/{id}")
    public String editUser(@ModelAttribute("user")  @Valid User user,BindingResult bindingResult) {
        if(bindingResult.hasErrors()) return "adminPlate/edit";
        userService.editUser(user);
        return "redirect:/users";
    }

}
