package com.astrapay.controller;

import com.astrapay.dto.BaseApiDto;
import com.astrapay.dto.NoteDto;
import com.astrapay.service.NoteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping
    public BaseApiDto<List<NoteDto>> getAllNotes() {
        return noteService.getAllNotes();
    }

    @GetMapping("/{id}")
    public BaseApiDto<Optional<NoteDto>> getNoteById(@PathVariable Integer id) {
        return noteService.getNoteById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BaseApiDto<NoteDto> createNote(@RequestBody NoteDto note) {
            return noteService.createOrUpdateNote(note);
    }

    @PutMapping("/{id}")
    public BaseApiDto<NoteDto> updateNote(@PathVariable Integer id, @RequestBody NoteDto note) {
        note.setId(id);
        return noteService.createOrUpdateNote(note);
    }

    @DeleteMapping("/{id}")
    public void deleteNote(@PathVariable Integer id) {
        noteService.deleteNote(id);
    }
}
