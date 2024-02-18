package github.sh1rsh1n.seminar_5.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import github.sh1rsh1n.seminar_5.entity.Status;
import github.sh1rsh1n.seminar_5.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {    

    // для теста производительности
    @Query(value = "SELECT * FROM tasks WHERE status=:status", nativeQuery = true)
    List<Task> getAllTaskByStatus(Status status);
}
