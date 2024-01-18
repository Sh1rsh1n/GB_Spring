package github.sh1rsh1n.seminar_3.controllers;

import java.util.ArrayList;
import java.util.List;

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

    private final UserService<User> service;

    public UserController(UserService<User> service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        List<User> users = new ArrayList<>();
        service.getAll().forEach(users::add);

        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/body")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
