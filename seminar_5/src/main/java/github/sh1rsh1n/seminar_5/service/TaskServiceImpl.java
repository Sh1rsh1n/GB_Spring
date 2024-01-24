package github.sh1rsh1n.seminar_5.service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import github.sh1rsh1n.seminar_5.entity.Status;
import github.sh1rsh1n.seminar_5.entity.Task;
import github.sh1rsh1n.seminar_5.repository.TaskRepository;
import github.sh1rsh1n.seminar_5.exception.TaskNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository repository;

    @Override
    public boolean createTask(Task task) {
        if (task != null) {
            LocalDateTime date = LocalDateTime.now();

            task.setCreateAt(date);
            task.setChangesAt(date);
            task.setStatus(Status.TODO);

            repository.save(task);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteTask(Long id) {
        Optional<Task> task = repository.findById(id);
        if (task.isPresent()) {
            repository.deleteById(id);
            return true;
        }
        throw new TaskNotFoundException();
    }

    @Override
    public List<Task> getAllTask() {
        List<Task> tasks = repository.findAll();
        if (!tasks.isEmpty()) {
            return tasks;
        }
        return Collections.emptyList();
    }

    @Override
    public Task getTaskById(Long id) {
        Optional<Task> task = repository.findById(id);
        if (task.isPresent()) {
            return task.get();
        }
        return null;
    }

    @Override
    public List<Task> getTaskByStatus(Status status) {
        return repository.findAll().stream().filter(s -> s.getStatus().equals(status)).toList();
    }

    @Override
    public void updateTask(Long id, Task task) {
        Optional<Task> optionalTask = repository.findById(id);
        if (optionalTask.isPresent()) {
            Task updatedTask = optionalTask.get();
            updatedTask.setDescription(task.getDescription());
            updatedTask.setStatus(task.getStatus());
            updatedTask.setChangesAt(LocalDateTime.now());
            repository.save(updatedTask);
        }
        throw new TaskNotFoundException();
    }

}
