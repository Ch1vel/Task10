package com.example.Task10.controlles;



import com.example.Task10.models.Role;
import com.example.Task10.models.User;
import com.example.Task10.services.RoleService;
import com.example.Task10.services.UserService;
import com.example.Task10.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;
    private final UserValidator userValidator;

    @Autowired
    public AdminController(UserService userService, RoleService roleService, UserValidator userValidator) {
        this.userService = userService;
        this.roleService = roleService;
        this.userValidator = userValidator;
    }

    @GetMapping
    public String welcomePage() {
        return "adminPlate/welcomePage";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("user") User user,Model model){
        model.addAttribute("role",roleService.showAllRoles());
        return "adminPlate/add";
    }

    @PostMapping
    public String addUserToBD(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,@RequestParam("role") List<Role> roles){
        userValidator.validate(user,bindingResult);
        if(bindingResult.hasErrors()) return "adminPlate/add";
        user.setRoles(roles);
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/users")
    public String showUserList(Model model) {
        model.addAttribute("list",userService.getAllUser());
        return "adminPlate/listOfUsers";
    }

    @DeleteMapping("/users/{id}/delete")
    public String deleteUser(@PathVariable("id") int id) {
        userService.removeUser(id);
        return "redirect:/admin/users";
    }

    @GetMapping("/users/{id}/edit")
    public String editUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user",userService.findUser(id));
        return "adminPlate/edit";
    }

    @PatchMapping("/users/{id}")
    public String editUser(@ModelAttribute("user")  @Valid User user,BindingResult bindingResult) {
        userValidator.validate(user,bindingResult);
        if(bindingResult.hasErrors()) return "adminPlate/edit";
        userService.editUser(user);
        return "redirect:/admin/users";
    }

}
