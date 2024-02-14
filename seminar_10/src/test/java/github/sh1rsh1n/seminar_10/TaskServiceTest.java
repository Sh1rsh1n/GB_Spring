package github.sh1rsh1n.seminar_10;

import github.sh1rsh1n.seminar_10.entity.Status;
import github.sh1rsh1n.seminar_10.entity.Task;
import github.sh1rsh1n.seminar_10.repository.TaskRepository;
import github.sh1rsh1n.seminar_10.service.TaskServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class TaskServiceTest {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskServiceImpl taskService;

    @BeforeEach
    void init() {
        Task task1 = new Task();
        task1.setId(1L);
        task1.setDescription("Task #001");
        task1.setStatus(Status.TODO);

        taskRepository.save(task1);
    }

    @Test
    void getTaskByIdTest() {
        assertThat(taskService.getTaskById(1L)).isNotNull();
    }
}
