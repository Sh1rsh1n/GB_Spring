package github.sh1rsh1n.seminar_10.service;

import github.sh1rsh1n.seminar_10.entity.Status;
import github.sh1rsh1n.seminar_10.entity.Task;
import github.sh1rsh1n.seminar_10.exception.TaskNotFoundException;
import github.sh1rsh1n.seminar_10.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.when;

/**
 * Тесты сервис слоя приложения
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
     * Тест метода создания заметки, успешный результат добавления
     */
    @Test
    void createTaskTest_WillReturn_True() {
        var newTask = Task.builder().id(1L).description("Task #001").status(Status.TODO).build();

        given(taskRepository.save(any(Task.class))).willReturn(newTask);

        boolean checkIsSaveTask = taskService.createTask(newTask);

        assertThat(newTask).isNotNull();
        assertTrue(checkIsSaveTask);
    }

    @Test
    void createTaskTest_WillReturn_False() {
        var newTask = new Task();

        boolean checkIsSaveTask = taskService.createTask(newTask);

        assertFalse(checkIsSaveTask);
    }

    @Test
    void deleteTaskTest_isGood() {

        given(taskRepository.findById(anyLong())).willReturn(Optional.of(tasks.get(0)));

        taskService.deleteTask(anyLong());

        verify(taskRepository).deleteById(anyLong());
    }

    @Test
    void deleteTaskTest_ThrowException() {
        given(taskRepository.findById(Mockito.anyLong())).willReturn(Optional.empty());

        assertThrows(TaskNotFoundException.class,
                () -> taskService.deleteTask(anyLong()));

        verify(taskRepository, never()).deleteById(anyLong());
    }

    @Test
    void getAllTaskTest_ReturnCollectionTasks() {
        given(taskRepository.findAll()).willReturn(tasks);

        var tasksList = taskService.getAllTask();

        assertEquals(tasks.size(), tasksList.size());
    }

    @Test
    void getAllTaskTest_ReturnEmptyCollection() {
        given(taskRepository.findAll()).willReturn(Collections.emptyList());

        var tasksList = taskService.getAllTask();

        assertEquals(0, tasksList.size());
    }

    @Test
    void getTaskByIdTest_WillReturnTask() {
        Task task = Task.builder()
                .id(1L).description("Task #001").build();

        when(taskRepository.findById(anyLong())).thenReturn(Optional.of(task));

        Task expectedTask = taskService.getTaskById(anyLong());

        assertEquals(expectedTask, task);
    }

    @Test
    void getTaskByIdTest_ThrowException() {

        when(taskRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(TaskNotFoundException.class,
                () -> taskService.getTaskById(anyLong()));
    }

    @Test
    void getTaskByStatusTest() {
        given(taskRepository.findAll()).willReturn(tasks);

        given(taskService.getTaskByStatus(Status.TODO)).willReturn(
                List.of(Task.builder().status(Status.TODO).build()));

        var tasksList = taskService.getTaskByStatus(Status.TODO);

        assertEquals(1,  tasksList.size());
    }

    @Test
    void updateTaskStatusTest_WillReturnUpdatedTask() {

        Task task = Task.builder().id(1L).description("Task #001").status(Status.TODO).build();

        given(taskRepository.findById(anyLong())).willReturn(Optional.of(task));

        boolean expected = taskService.updateTaskStatus(anyLong(), Status.DONE);

        assertThat(task.getStatus()).isEqualTo(Status.DONE);
        assertTrue(expected);
    }

    @Test
    void updateTaskStatusTest_ThrowException() {

        given(taskRepository.findById(anyLong())).willReturn(Optional.empty());

        assertThrows(TaskNotFoundException.class,
                () -> taskService.updateTaskStatus(anyLong(), Status.TODO));
    }
}