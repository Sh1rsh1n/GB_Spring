package github.sh1rsh1n.seminar_4.controller;

import github.sh1rsh1n.seminar_4.entity.User;
import github.sh1rsh1n.seminar_4.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public String getAll(Model model) {
        log.info("Get all users from DB");
        List<User> users = service.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/create")
    public String createUser(Model model) {
        log.info("Create user form is started");
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("pageTitle", "Создание нового пользователя.");
        return "user_form";
    }

    @PostMapping("/save")
    public String saveUser(User user, RedirectAttributes redirectAttributes) {
        if (service.saveUser(user)) {
            log.info(String.format("User %s is created.", user.getName()));
            redirectAttributes.addFlashAttribute("message", "Пользователь был успешно создан.");
        } else {
            redirectAttributes.addFlashAttribute("message", "Ошибка!");
        }
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String updateUser(@PathVariable("id") Integer id, Model model) {

        User user = service.getUserById(id);
        if (user != null) {
            model.addAttribute("user", user);
            model.addAttribute("pageTitle", "Редактор пользователя: " + user.getName());
            log.info(String.format("User %s is success update.", user.getName()));
            return "user_form";
        }
        log.info(String.format("User with ID: %d not found", id));
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        if (service.deleteUser(id)) {
            log.info(String.format("User with ID: %d is been success delete.", id));
            redirectAttributes.addFlashAttribute("message", "Пользователь был успешно удален!");
        } else {
            log.info(String.format("User with ID: %d not found", id));
            redirectAttributes.addFlashAttribute("message", "Ошибка!");
        }
        return "redirect:/";
    }
}
