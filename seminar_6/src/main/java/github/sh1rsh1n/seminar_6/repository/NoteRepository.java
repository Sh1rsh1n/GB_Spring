package github.sh1rsh1n.seminar_6.repository;

import github.sh1rsh1n.seminar_6.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
}
