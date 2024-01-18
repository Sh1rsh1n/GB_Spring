package github.sh1rsh1n.seminar_3.controllers;

import java.util.ArrayList;
import java.util.List;

import github.sh1rsh1n.seminar_3.services.RegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import github.sh1rsh1n.seminar_3.domain.User;
import github.sh1rsh1n.seminar_3.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService<User> userService;
    private final RegistrationService registrationService;

    public UserController(UserService<User> userService, RegistrationService registrationService) {
        this.userService = userService;
        this.registrationService = registrationService;
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        List<User> users = new ArrayList<>();
        userService.getAll().forEach(users::add);

        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/body")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        if (user != null) {
            registrationService.processRegistration(user.getName(), user.getAge(), user.getEmail());
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
