package github.sh1rsh1n.seminar_5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import github.sh1rsh1n.seminar_5.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {    
}
