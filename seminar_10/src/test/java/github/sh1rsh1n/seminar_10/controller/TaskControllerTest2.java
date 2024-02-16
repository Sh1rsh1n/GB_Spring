package github.sh1rsh1n.seminar_10.controller;

import github.sh1rsh1n.seminar_10.entity.Status;
import github.sh1rsh1n.seminar_10.entity.Task;
import github.sh1rsh1n.seminar_10.repository.TaskRepository;
import github.sh1rsh1n.seminar_10.service.TaskServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class TaskControllerTest2 {

    @Mock
    TaskServiceImpl taskService;

    @InjectMocks
    TaskController taskController;

    List<Task> tasks;

    /**
     * Добавляем "задачи" в список который будем возвращать за место БД
     */
    @BeforeEach
    void setup() {
        tasks = List.of(
            Task.builder().description("Task1").status(Status.TODO).build(),
            Task.builder().description("Task2").status(Status.DONE).build()
        );
    }

    /**
     * Проверка получения списка "задач"
     * проверка статуса ответа (200 Ok) и содержимого тела-ответа
     */
    @Test
    void getAllTasksTest_ReturnOkStatus() {

        doReturn(tasks).when(this.taskService).getAllTask();

        var responseEntity = this.taskController.getAllTask();

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode()); 
        assertEquals(tasks, responseEntity.getBody());
    }

    /**
     * Проверка получения пустого списка
     * проверка статуса ответа сервера (204 NO_CONTENT)
     */
    @Test
    void getAllTasksTest_ReturnNoContentStatus() {
        doReturn(Collections.emptyList()).when(this.taskService).getAllTask();

        var responseEntity = this.taskController.getAllTask();

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    /**
     * Проверка метода добавления задачи
     * проверка статуса ответа, ожидается CREATED
     * если в теле ответа приходит Task, то выполняем доп проверки
     * на null и содержимое из тела ответа сервера поля description
     */
    @Test
    void addTaskTest_WhenReturnCreatedStatus() {
        String description = "New Task";

        doReturn(true).when(taskService).createTask(any(Task.class));

        var responseEntity = this.taskController.addTask(description);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        if (responseEntity.getBody() instanceof Task task) {
            assertNotNull(task);
            assertEquals(description, task.getDescription());
        }
    }

    /**
     * Проверка метода добавления задачи, когда метод сервиса возвращает false
     * проверка статуса ответа, ожидается BAD_REQUEST
     */
    @Test
    void addTaskTest_WhenReturnBadRequestStatus() {
        doReturn(false).when(taskService).createTask(any(Task.class));

        var responseEntity = this.taskController.addTask(null);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }
}
