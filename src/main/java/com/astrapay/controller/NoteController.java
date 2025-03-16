package com.astrapay.controller;

import com.astrapay.dto.NoteDto;
import com.astrapay.service.NoteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping
    public List<NoteDto> getAllNotes() {
        return noteService.getAllNotes();
    }

    @GetMapping("/{id}")
    public Optional<NoteDto> getNoteById(@PathVariable Integer id) {
        return noteService.getNoteById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NoteDto createNote(@RequestBody NoteDto note) {
        return noteService.createOrUpdateNote(note);
    }

    @PutMapping("/{id}")
    public NoteDto updateNote(@PathVariable Integer id, @RequestBody NoteDto note) {
        note.setId(id);
        return noteService.createOrUpdateNote(note);
    }

    @DeleteMapping("/{id}")
    public void deleteNote(@PathVariable Integer id) {
        noteService.deleteNote(id);
    }
}
