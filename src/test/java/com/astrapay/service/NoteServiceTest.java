package com.astrapay.service;

import com.astrapay.dto.NoteDto;
import com.astrapay.dto.mapper.INoteMapper;
import com.astrapay.entity.Note;
import com.astrapay.repository.INoteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NoteServiceTest {

    @InjectMocks
    private NoteService noteService;

    @Mock
    private INoteRepository noteRepository;

    @Mock
    private INoteMapper noteMapper;

    private Note note;
    private NoteDto noteDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Setup DTO and Entity for testing
        noteDto = new NoteDto();
        noteDto.setTitle("Test Title");
        noteDto.setContent("Test Content");

        note = new Note();
        note.setTitle("Test Title");
        note.setContent("Test Content");
    }

    @Test
    void shouldReturnNoteList() {
        // Given
        when(noteRepository.findAll()).thenReturn(List.of(note));
        when(noteMapper.toDto(List.of(note))).thenReturn(List.of(noteDto));

        // When
        var response = noteService.getAllNotes();

        // Then
        assertEquals("OK", response.getStatus());
        assertNotNull(response.getData());
        assertEquals(1, response.getData().size());
        assertEquals("Test Title", response.getData().get(0).getTitle());
    }

    @Test
    void shouldReturnEmptyNoteList() {
        // Given
        when(noteRepository.findAll()).thenReturn(List.of());
        when(noteMapper.toDto(List.of(note))).thenReturn(List.of());

        // When
        var response = noteService.getAllNotes();

        // Then
        assertEquals("OK", response.getStatus());
        assertNotNull(response.getData());
        assertEquals(0, response.getData().size());
        assertEquals("No Notes Added", response.getMessage().get(0));
    }

    @Test
    void shouldCreateNote() {
        // Given
        when(noteMapper.toEntity(noteDto)).thenReturn(note);
        when(noteRepository.save(any(Note.class))).thenReturn(note);
        when(noteMapper.toDto(any(Note.class))).thenReturn(noteDto);

        // When
        var response = noteService.createOrUpdateNote(noteDto);

        // Then
        assertNotNull(response.getMessage());
        assertEquals(1, response.getMessage().size());
        assertEquals("Note Successfully Added", response.getMessage().get(0));
    }

    @Test
    void shouldNotUpdateNote() {
        // Given
        noteDto.setId(1);
        when(noteRepository.findById(noteDto.getId())).thenReturn(Optional.ofNullable(note));
        when(noteMapper.toEntity(noteDto)).thenReturn(note);
        when(noteRepository.save(any(Note.class))).thenReturn(note);
        when(noteMapper.toDto(any(Note.class))).thenReturn(noteDto);

        // When
        var response = noteService.createOrUpdateNote(noteDto);

        // Then
        assertNotNull(response.getMessage());
        assertEquals(1, response.getMessage().size());
        assertEquals("Note Successfully Updated", response.getMessage().get(0));
    }

    @Test
    void shouldNotUpdateNoteWhenNoteNotFound() {
        // Given
        noteDto.setId(1);

        // When
        var exception = assertThrows(ResponseStatusException.class, () -> {
            noteService.createOrUpdateNote(noteDto);
        });

        // Then
        assertEquals("Note Not Found", exception.getReason());
    }

    @Test
    void shouldDeleteNote() {
        // Given
        when(noteRepository.findById(1)).thenReturn(Optional.ofNullable(note));
        doNothing().when(noteRepository).deleteById(1);

        // When
        noteService.deleteNote(1);

        // Then
    }

    @Test
    void shouldNotDeleteNoteWhenNoteNotFound() {
        // Given
        doNothing().when(noteRepository).deleteById(1);

        // When
        var exception = assertThrows(ResponseStatusException.class, () -> {
            noteService.deleteNote(1);
        });

        // Then
        assertEquals("Note Not Found", exception.getReason());
    }

    @Test
    void shouldGetNoteById() {
        // Given
        when(noteRepository.findById(1)).thenReturn(Optional.of(note));
        when(noteMapper.toDto(note)).thenReturn(noteDto);

        // When
        var response = noteService.getNoteById(1);

        // Then
        assertEquals("OK", response.getStatus());
        assertNotNull(response.getData());
        assertEquals("Test Title", response.getData().get().getTitle());
    }

    @Test
    void shouldNotGetNoteByIdWhenNoteNotFound() {
        // Given

        // When
        var exception = assertThrows(ResponseStatusException.class, () -> {
            noteService.getNoteById(1);
        });

        // Then
        assertEquals("Note Not Found", exception.getReason());
    }
}