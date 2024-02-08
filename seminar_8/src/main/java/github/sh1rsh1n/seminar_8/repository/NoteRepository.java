package github.sh1rsh1n.seminar_8.repository;

import github.sh1rsh1n.seminar_8.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Обращение к БД
 */
public interface NoteRepository extends JpaRepository<Note, Long> {
}
