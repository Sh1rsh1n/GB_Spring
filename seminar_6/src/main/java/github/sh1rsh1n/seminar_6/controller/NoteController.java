package github.sh1rsh1n.seminar_6.controller;

import github.sh1rsh1n.seminar_6.entity.Note;
import github.sh1rsh1n.seminar_6.service.NoteService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.data.repository.core.support.RepositoryFragmentsFactoryBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @GetMapping
    public ResponseEntity<?> getAllNotes() {
        List<Note> notes = noteService.getAll();
        return new ResponseEntity<>(notes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getNote(@PathVariable Long id) {
        Note note = noteService.getById(id);
        if (note == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(note, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<?> addNote(@RequestBody Note note) {
        if (note == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(note, HttpStatus.CREATED);
    }

    @GetMapping("/save/{id}")
    
}
