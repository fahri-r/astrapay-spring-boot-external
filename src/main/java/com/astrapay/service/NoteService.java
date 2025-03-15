package com.astrapay.service;

import com.astrapay.dto.NoteDto;
import com.astrapay.dto.mapper.INoteMapper;
import com.astrapay.entity.Note;
import com.astrapay.repository.INoteRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {
    private final INoteRepository noteRepository;
    private final INoteMapper noteMapper;

    public NoteService(INoteRepository noteRepository, @Qualifier("INoteMapper") INoteMapper noteMapper) {
        this.noteRepository = noteRepository;
        this.noteMapper = noteMapper;
    }

    public List<NoteDto> getAllNotes() {
        var notes = noteRepository.findAll();
        return noteMapper.toDto(notes);
    }

    public Optional<NoteDto> getNoteById(Integer id) {
        return noteRepository.findById(id)
                .map(noteMapper::toDto);
    }

    public NoteDto createOrUpdateNote(NoteDto noteDto) {
        Note entity;
        if (noteDto.getId() == null) {
            // Create new note
            entity = noteMapper.toEntity(noteDto);
        } else {
            // Update existing note
            entity = noteRepository.findById(noteDto.getId())
                    .map(x -> {
                        x.setTitle(noteDto.getTitle());
                        x.setContent(noteDto.getContent());
                        return x;
                    })
                    .orElseThrow(() -> new RuntimeException("Note not found"));
        }

        // Saving the entity
        var savedEntity = noteRepository.save(entity);

        return noteMapper.toDto(savedEntity);
    }

    public void deleteNote(Integer id) {
        noteRepository.deleteById(id);
    }
}
