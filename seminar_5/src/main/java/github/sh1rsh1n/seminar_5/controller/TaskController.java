package github.sh1rsh1n.seminar_5.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import github.sh1rsh1n.seminar_5.service.TaskService;
import github.sh1rsh1n.seminar_5.entity.*;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {
    
    private final TaskService service;

    /**
     * обработка запосов по адресу http://localhost:8080/tasks
     * @return ответ сервера, статус запроса и список задач
     */
    @GetMapping
    public ResponseEntity<?> getAllTask() {
        List<Task> tasks = service.getAllTask();
        if (tasks.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/{status}")
    public ResponseEntity<?> getAllTaskByStatus(@PathVariable("status") Status status) {
        List<Task> tasks = service.getTaskByStatus(status);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<?> addTask(@RequestParam String description) {
        Task task = new Task();
        System.out.println(description);
        task.setDescription(description);
        if (service.createTask(task)){
            return new ResponseEntity<>(task, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTaskStatus(@PathVariable Long id, @RequestBody Status[] status) {
        if (service.updateTaskStatus(id, status[0])) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
