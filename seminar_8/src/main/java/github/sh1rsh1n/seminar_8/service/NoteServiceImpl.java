package github.sh1rsh1n.seminar_8.service;

import github.sh1rsh1n.seminar_8.aspect.TrackUserAction;
import org.springframework.stereotype.Service;

import github.sh1rsh1n.seminar_8.entity.Note;
import github.sh1rsh1n.seminar_8.exception.NoteNotFountException;
import github.sh1rsh1n.seminar_8.repository.NoteRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NoteRepository repository;

    @Override
    public Note addNote(Note note) {
        if (note != null) {
            note.setChangesAt(LocalDateTime.now());
            return repository.save(note);
        }
        throw new NoteNotFountException();
    }

    @Override
    public boolean deleteNote(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @TrackUserAction
    @Override
    public List<Note> getAll() {
        List<Note> notes = repository.findAll();
        if (notes.isEmpty()) {
            return Collections.emptyList();
        }
        return notes;
    }

    @TrackUserAction
    @Override
    public Note getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public boolean updateNote(Note note) {
        Note updatedNote = getById(note.getId());
        if (updatedNote != null) {
            updatedNote.setTitle(note.getTitle());
            updatedNote.setDescription(note.getDescription());
            updatedNote.setChangesAt(LocalDateTime.now());
            repository.save(updatedNote);
            return true;
        }
        return false;
    }
}
