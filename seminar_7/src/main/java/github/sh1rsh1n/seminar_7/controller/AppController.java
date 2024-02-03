package github.sh1rsh1n.seminar_7.controller;

import github.sh1rsh1n.seminar_7.entity.User;
import github.sh1rsh1n.seminar_7.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

@Controller
@RequiredArgsConstructor
public class AppController {

    private final UserService userService;

    @GetMapping("/public")
    public String publicPage() {
        return "public";
    }

    @GetMapping("/private")
    public String privatePage() {
        return "private";
    }

    @RequestMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/")
    public String mainPage() {
        return "index";
    }
}
