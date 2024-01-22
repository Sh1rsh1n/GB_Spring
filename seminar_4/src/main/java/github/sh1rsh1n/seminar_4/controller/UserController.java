package github.sh1rsh1n.seminar_4.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import github.sh1rsh1n.seminar_4.entity.User;
import github.sh1rsh1n.seminar_4.service.UserService;

import github.sh1rsh1n.seminar_4.entity.User;

@Controller
@RequestMapping("/")
public class UserController {
    
    @Autowired
    private UserService service;

    @GetMapping
    public String getAll(Model model) {

        List<User> users = service.getAllUsers();
        
        model.addAttribute("users", users);

        return "users";
    }

    @GetMapping("/create")
    public String createUser(Model model) {
        
        return "user_form";
    }

    @PostMapping("/save")
    public String saveUser(User user, Model model) {
        
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String updateUser(@PathVariable("id") Integer id, Model model) {

        return "user_form";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {

        return "redirect:/";
    }
}
