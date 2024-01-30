package github.sh1rsh1n.seminar_6.service;

import org.springframework.stereotype.Service;

import github.sh1rsh1n.seminar_6.entity.Note;
import github.sh1rsh1n.seminar_6.exception.NoteNotFountException;
import github.sh1rsh1n.seminar_6.repository.NoteRepository;
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
            return repository.save(note);
        }
        throw new NoteNotFountException();
    }

    @Override
    public void deleteNote(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
        throw new NoteNotFountException();
    }

    @Override
    public List<Note> getAll() {
        List<Note> notes = repository.findAll();
        if (notes.isEmpty()) {
            return Collections.emptyList();
        }
        return notes;
    }

    @Override
    public Note getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void updateNote(Long id, Note note) {
        Note updatedNote = getById(id);
        updatedNote.setTitle(note.getTitle());
        updatedNote.setDescription(note.getDescription());
        updatedNote.setChangesAt(LocalDateTime.now());
        repository.save(updatedNote);
    }
}
