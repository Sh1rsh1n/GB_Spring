package github.sh1rsh1n.seminar_8.service;

import java.util.List;

import github.sh1rsh1n.seminar_8.entity.Note;

public interface NoteService {

    List<Note> getAll();

    Note getById(Long id);

    Note addNote(Note note);

    boolean updateNote(Note note);

    boolean deleteNote(Long id);
}
