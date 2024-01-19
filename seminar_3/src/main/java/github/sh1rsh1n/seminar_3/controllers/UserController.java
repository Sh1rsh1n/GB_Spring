package github.sh1rsh1n.seminar_3.controllers;


import github.sh1rsh1n.seminar_3.domain.User;
import github.sh1rsh1n.seminar_3.services.RegistrationService;
import github.sh1rsh1n.seminar_3.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Контроллер, обрабатывает запросы по адресу http://localhost:8080/user
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService<User> userService;
    private final RegistrationService registrationService;

    public UserController(UserService<User> userService, RegistrationService registrationService) {
        this.userService = userService;
        this.registrationService = registrationService;
    }

    /**
     * метод, обрабатывает запросы по адресу http://localhost:8080/user
     *
     * @return ResponseEntity, список пользователей в формате JSON
     */
    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        List<User> users = new ArrayList<>();
        userService.getAll().forEach(users::add);

        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    /**
     * метод, обрабатывает запросы по адресу http://localhost:8080/user/body
     *
     * @param user данные которые передаем в теле запроса, для добавления в БД
     * @return ResponseEntity, данные о пользователи в формате JSON
     */
    @PostMapping("/body")
    public ResponseEntity<?> userAddFromParam(@RequestBody User user) {

        if (user != null) {
            String name = user.getName();
            int age = user.getAge();
            String email = user.getEmail();

            registrationService.processRegistration(name, age, email);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }

        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
