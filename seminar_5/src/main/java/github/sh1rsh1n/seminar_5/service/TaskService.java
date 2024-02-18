package github.sh1rsh1n.seminar_5.service;

import github.sh1rsh1n.seminar_5.entity.Task;
import github.sh1rsh1n.seminar_5.entity.Status;
import java.util.List;

public interface TaskService {

    List<Task> getAllTask();

    List<Task> getTaskByStatus(Status status);

    Task getTaskById(Long id);

    boolean createTask(Task task);

    boolean updateTaskStatus(Long id, Status status);

    boolean deleteTask(Long id);
}