package github.sh1rsh1n.seminar_10.service;

import github.sh1rsh1n.seminar_10.entity.Status;
import github.sh1rsh1n.seminar_10.entity.Task;
import github.sh1rsh1n.seminar_10.exception.TaskNotFoundException;
import github.sh1rsh1n.seminar_10.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.when;

/**
 * Тестирование для сервис-слоя приложения
 */
@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {

    @Mock
    TaskRepository taskRepository;

    @InjectMocks
    TaskServiceImpl taskService;

    List<Task> tasks;

    @BeforeEach
    void setUp() {
        var task1 = Task.builder().id(1L).description("Task #001").status(Status.TODO).build();
        var task2 = Task.builder().id(2L).description("Task #002").status(Status.IN_PROGRESS).build();

        tasks = List.of(task1, task2);
    }

    /**
     * Тест метода создания новой задачи
     * Успешно выполнение
     */
    @Test
    void createTaskTest_WillReturn_True() {
        var newTask = Task.builder().id(1L).description("Task #001").status(Status.TODO).build();

        given(taskRepository.save(any(Task.class))).willReturn(newTask);

        boolean checkIsSaveTask = taskService.createTask(newTask);

        assertThat(newTask).isNotNull();
        assertTrue(checkIsSaveTask);
    }

    /**
     * Тест метода создания новой задачи
     * Неуспешное выполнение
     */
    @Test
    void createTaskTest_WillReturn_False() {
        var newTask = new Task();

        boolean checkIsSaveTask = taskService.createTask(newTask);

        assertFalse(checkIsSaveTask);
    }

    /**
     * Успешный тест метода удаления задачи
     */
    @Test
    void deleteTaskTest_isGood() {

        given(taskRepository.findById(anyLong())).willReturn(Optional.of(tasks.get(0)));

        taskService.deleteTask(anyLong());

        verify(taskRepository).deleteById(anyLong());
    }

    /**
     * Тест метода удаления задачи, проверка на исключение TaskNotFoundException
     */
    @Test
    void deleteTaskTest_ThrowException() {
        given(taskRepository.findById(anyLong())).willReturn(Optional.empty());

        assertThrows(TaskNotFoundException.class,
                () -> taskService.deleteTask(anyLong()));

        verify(taskRepository, never()).deleteById(anyLong());
    }

    /**
     * Тест метода получения списка задач
     * Успешный результат, должен вернутся список задач
     */
    @Test
    void getAllTaskTest_ReturnCollectionTasks() {
        given(taskRepository.findAll()).willReturn(tasks);

        var tasksList = taskService.getAllTask();

        assertEquals(tasks.size(), tasksList.size());
    }

    /**
     * Тест метода получения списка задач
     * Успешный результат, должен вернутся пустой список
     */
    @Test
    void getAllTaskTest_ReturnEmptyCollection() {
        given(taskRepository.findAll()).willReturn(Collections.emptyList());

        var tasksList = taskService.getAllTask();

        assertEquals(0, tasksList.size());
    }

    /**
     * Тест метода получение задачи по ID
     * Успешный результат, ожидается возврат объекта Task
     */
    @Test
    void getTaskByIdTest_WillReturnTask() {
        Task task = Task.builder()
                .id(1L).description("Task #001").build();

        when(taskRepository.findById(anyLong())).thenReturn(Optional.of(task));

        Task expectedTask = taskService.getTaskById(anyLong());

        assertEquals(expectedTask, task);
    }

    /**
     * Тест метода получение задачи по ID
     * Проверка на исключение
     */
    @Test
    void getTaskByIdTest_ThrowException() {

        when(taskRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(TaskNotFoundException.class,
                () -> taskService.getTaskById(anyLong()));
    }

    /**
     * Тест метода получения списка задач по статусу
     * Успешный результат, должен вернутся список задач отфильтрованный по статусу
     */
    @Test
    void getTaskByStatusTest() {
        given(taskRepository.findAll()).willReturn(tasks);

        given(taskService.getTaskByStatus(Status.TODO)).willReturn(
                List.of(Task.builder().status(Status.TODO).build()));

        var tasksList = taskService.getTaskByStatus(Status.TODO);

        assertEquals(1, tasksList.size());
    }

    /**
     * Тест метода изменение статусу задачи
     * Успешный результат, должен вернутся значение true
     * Выполняется проверка текущего статуса с ожидаемым
     */
    @Test
    void updateTaskStatusTest_WillReturnUpdatedTask() {

        Task task = Task.builder().id(1L).description("Task #001").status(Status.TODO).build();

        given(taskRepository.findById(anyLong())).willReturn(Optional.of(task));

        boolean expected = taskService.updateTaskStatus(anyLong(), Status.DONE);

        assertThat(task.getStatus()).isEqualTo(Status.DONE);
        assertTrue(expected);
    }

    /**
     * Тест метода изменение статусу задачи
     * Проверка на исключение
     */
    @Test
    void updateTaskStatusTest_ThrowException() {

        given(taskRepository.findById(anyLong())).willReturn(Optional.empty());

        assertThrows(TaskNotFoundException.class,
                () -> taskService.updateTaskStatus(anyLong(), Status.TODO));
    }
}