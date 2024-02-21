package github.sh1rsh1n.seminar_12.controller;

import github.sh1rsh1n.seminar_12.entity.Status;
import github.sh1rsh1n.seminar_12.entity.Task;
import github.sh1rsh1n.seminar_12.service.data_handlers.DataServiceFactory;
import github.sh1rsh1n.seminar_12.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST-контроллер обработка запросов по URI http://localhost:8080/tasks
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    private final DataServiceFactory dataService;

    private final static String LOG_FILE = "log.txt";

    /**
     * Обработка GET-запосов для получения списка всех задач.
     * Пример запроса: http://localhost:8080/tasks
     *
     * @return ответ сервера, статус запроса и список задач
     */
    @GetMapping
    public ResponseEntity<?> getAllTask() {
        dataService.getService("txtWriter").writeData(LOG_FILE, "Call GET method: getAllTask");
        List<Task> tasks = taskService.getAllTask();
        if (tasks.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    /**
     * Обработка GET-запросов для получения списка задач отфильтрованных по статусу
     * пример запроса: http://localhost:8080/tasks/DONE
     *
     * @param status статус задачи
     * @return ответ сервера и список задач отфильтрованных по статусу
     */
    @GetMapping("/{status}")
    public ResponseEntity<?> getAllTaskByStatus(@PathVariable("status") Status status) {
        dataService.getService("txtWriter").writeData(LOG_FILE, "Call GET method: getAllTaskByStatus");
        List<Task> tasks = taskService.getTaskByStatus(status);
        if (tasks.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    /**
     * Обработка POST-запроса. Создание новой задачи.
     * добавление данных происходит через параметры запроса
     * пример запроса: http://localhost:8080/tasks/new?description=Buy bear
     *
     * @param description - описание задачи
     * @return статус запроса и задача в формате JSON
     */
    @PostMapping("/new")
    public ResponseEntity<?> addTask(@RequestParam String description) {
        dataService.getService("txtWriter").writeData(LOG_FILE, "Call POST method: addTask with parameter's " + description);
        Task task = new Task();
        task.setDescription(description);
        if (taskService.createTask(task)) {
            return new ResponseEntity<>(task, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
