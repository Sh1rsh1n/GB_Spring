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

/**
 * REST-контроллер обработка запросов по URI http://localhost:8080/tasks
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskController {
    
    private final TaskService service;

    /**
     * Обработка GET-запосов для получения списка всех задач.
     * Пример запроса: http://localhost:8080/tasks
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

    /**
     * Обработка GET-запросов для получения списка задач отфильтрованных по статусу
     * пример запроса: http://localhost:8080/tasks/DONE
     * @param status статус задачи
     * @return ответ сервера и список задач отфильтрованных по статусу
     */
    @GetMapping("/{status}")
    public ResponseEntity<?> getAllTaskByStatus(@PathVariable("status") Status status) {
        List<Task> tasks = service.getTaskByStatus(status);
        if (tasks.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    /**
     * Обработка POST-запроса. Создание новой задачи.
     * добавление данных происходит через параметры запроса
     * пример запроса: http://localhost:8080/tasks/new?description=Buy bear
     * @param description - описание задачи
     * @return статус запроса и задача в формате JSON
     */
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

    /**
     * Обработка PUT-запроса. Обновление данных указанной задачи.
     * Пример запроса: http://localhost:8080/tasks/{id}
     * Тело запроса должно быть следующего виде(формат JSON): ["DONE"]
     * @param id - идентификатор задачи
     * @param status - обновляемый статус
     * @return статус запроса
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTaskStatus(@PathVariable("id") Long id, @RequestBody Status[] status) {
        if (service.updateTaskStatus(id, status[0])) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
