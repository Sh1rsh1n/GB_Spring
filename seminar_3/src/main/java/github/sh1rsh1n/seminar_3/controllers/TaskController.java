package github.sh1rsh1n.seminar_3.controllers;

import github.sh1rsh1n.seminar_3.domain.User;
import github.sh1rsh1n.seminar_3.services.DataProcessingService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер обрабатывает запросы по адресу http://localhost:8080/task
 */
@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private DataProcessingService data;

    /**
     * метод, обрабатывает запросы по адресу http://localhost:8080/task/{age}
     * @param age параметр запроса(минимальный возраст пользователя)
     * @return ResponseEntity: ответ сервера, список пользователей в JSON формате
     */
    @GetMapping("/{age}")
    public ResponseEntity<?> filterUsersByAge(@PathVariable("age") int age) {

        List<User> filteringUsers = data.filterUsersByAge(age);
        if (filteringUsers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(filteringUsers, HttpStatus.OK);
    }

    /**
     * метод, обрабатывает запросы по адресу http://localhost:8080/task/calc
     * @return ResponseEntity, результат вычислений среднего возраста всех пользователей
     */
    @GetMapping("/calc")
    public ResponseEntity<?> calculateAverageAge() {
        double avgAge = data.calculateAverageAge();
        if (avgAge < 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(avgAge, HttpStatus.OK);
    }

    /**
     * метод, обрабатывает запросы по адресу http://localhost:8080/task/sort
     * @return ResponseEntity, отсортированный список пользователей в формате JSON
     */
    @GetMapping("/sort")
    public ResponseEntity<?> sortByUserAge(){
        List<User> sortList = data.sortUsersByAge();

        if (sortList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(sortList, HttpStatus.OK);
    }
}
