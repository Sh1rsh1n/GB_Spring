package github.sh1rsh1n.seminar_12.service;

import github.sh1rsh1n.seminar_12.entity.Status;
import github.sh1rsh1n.seminar_12.entity.Task;
import github.sh1rsh1n.seminar_12.exception.TaskNotFoundException;
import github.sh1rsh1n.seminar_12.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository repository;

    /**
     * метод добавление новой задачи
     * если task не равен null устанавливаем даты и статус,
     * затем передаем задачу в репозиторий
     * @param task - задачу
     * @return boolean
     */
    @Override
    public boolean createTask(Task task) {
        if (task != null || task.getDescription() != null) {
            LocalDateTime date = LocalDateTime.now();
            task.setCreateAt(date);
            task.setChangesAt(date);
            task.setStatus(Status.TODO);

            repository.save(task);
            return true;
        }
        return false;
    }

    /**
     * Удаление задачи по ID
     * Выполняем проверку, есть ли задача по данному ID в репозитории
     * @param id - идентификатор задачи
     * @return boolean
     */
    @Override
    public boolean deleteTask(Long id) {
        Optional<Task> task = repository.findById(id);
        if (task.isPresent()) {
            repository.deleteById(id);
            return true;
        }
        throw new TaskNotFoundException();
    }

    /**
     * Получение списка всех задач
     * @return List<Task>
     */
    @Override
    public List<Task> getAllTask() {
        List<Task> tasks = repository.findAll();
        if (!tasks.isEmpty()) {
            return tasks;
        }
        return Collections.emptyList();
    }

    /**
     * Получение задачи по ID
     * @param id - идетификатор задачи
     * @return Task
     */
    @Override
    public Task getTaskById(Long id) {
        Optional<Task> task = repository.findById(id);
        if (task.isPresent()) {
            return task.get();
        }
        throw new TaskNotFoundException();
    }

    /**
     * Получаем список задач, отфильтрованный по статусу
     * @param status - статус задачи
     * @return List<Task>
     */
    @Override
    public List<Task> getTaskByStatus(Status status) {
        //return repository.getAllTaskByStatus(status); 
        return repository.findAll().stream().filter(s -> s.getStatus().equals(status)).toList();
    }

    /**
     * Обновление данных задачи по ID
     * Если задача по данному ID есть в репозитории, то обновляем ее данные/статус
     * @param id   - идентификатор задачи которую нужно изменить
     * @param status - новые данные для изменяемой задачи
     */
    @Override
    public boolean updateTaskStatus(Long id, Status status) {
        Optional<Task> optionalTask = repository.findById(id);
        if (optionalTask.isPresent()) {
            Task updatedTask = optionalTask.get();
            updatedTask.setStatus(status);
            updatedTask.setChangesAt(LocalDateTime.now());
            repository.save(updatedTask);
            return true;
        }
        throw new TaskNotFoundException();
    }

}
