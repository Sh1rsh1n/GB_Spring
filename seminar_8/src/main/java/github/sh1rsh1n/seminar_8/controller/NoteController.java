package github.sh1rsh1n.seminar_8.controller;

import github.sh1rsh1n.seminar_8.entity.Note;
import github.sh1rsh1n.seminar_8.service.NoteService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Обработка REST запросов по адресу "http://localhost:8080/api/notes"
 * Для разрешения взаимодействия с внешним интерфейсом(REACT) используется аннотация @CrossOrigin
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    /**
     * Получение списка всех заметок
     * Обработка запросов по адресу [GET]http://localhost:8080/api/notes
     * @return
     */
    @GetMapping
    public ResponseEntity<?> getAllNotes() {
        List<Note> notes = noteService.getAll();
        return new ResponseEntity<>(notes, HttpStatus.OK);
    }

    /**
     * Получение заметки по ID
     * Обработка запросов по адресу [GET]http://localhost:8080/api/notes/{id}
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getNote(@PathVariable Long id) {
        Note note = noteService.getById(id);
        if (note != null) {
            return new ResponseEntity<>(note, HttpStatus.OK);
        }
        return new ResponseEntity<>(note, HttpStatus.NOT_FOUND);
    }

    /**
     * Создание новой заметки
     * Обработка запроса по адресу [POST]http://localhost:8080/api/notes/new
     * @param note
     * @return
     */
    @PostMapping("/new")
    public ResponseEntity<?> addNote(@RequestBody Note note) {
        if (note == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        noteService.addNote(note);
        return new ResponseEntity<>(note, HttpStatus.CREATED);
    }

    /**
     * Изменение заметки
     * Обработка запроса по адресу [PUT]http://localhost:8080/api/notes/save
     * @param note
     * @return
     */
    @PutMapping("/save")
    public ResponseEntity<?> updateNote(@RequestBody Note note) {
        if (noteService.updateNote(note)) {
            return new ResponseEntity<>(note, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Удаление заметки
     * Обработка запроса по адресу [GET]http://localhost:8080/api/notes/delete/{id}
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable("id") Long id) {
        if (noteService.deleteNote(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
