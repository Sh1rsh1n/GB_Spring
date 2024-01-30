package github.sh1rsh1n.seminar_6.service;

import java.util.List;

import github.sh1rsh1n.seminar_6.entity.Note;

public interface NoteService {

    List<Note> getAll();

    Note getById(Long id);

    Note addNote(Note note);

    void updateNote(Long id, Note note);

    void deleteNote(Long id);
}
