package github.sh1rsh1n.seminar_10.controller;

import github.sh1rsh1n.seminar_10.entity.Status;
import github.sh1rsh1n.seminar_10.entity.Task;
import github.sh1rsh1n.seminar_10.repository.TaskRepository;
import github.sh1rsh1n.seminar_10.service.TaskServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class taskControllerTest2 {

    @Mock
    TaskServiceImpl taskService;

    @InjectMocks
    TaskController taskController;

    @Test
    void handlerGetAllTasks_ReturnIs200StatusResponseEntity() {
        var tasks = List.of(
                Task.builder().description("Task1").status(Status.TODO).build(),
                Task.builder().description("Task2").status(Status.DONE).build()
        );

        doReturn(tasks).when(this.taskService).getAllTask();

        var responseEntity = this.taskController.getAllTask();

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertEquals(MediaType.APPLICATION_JSON, responseEntity.getHeaders().getContentType());
        assertEquals(tasks, responseEntity.getBody());
    }
}
