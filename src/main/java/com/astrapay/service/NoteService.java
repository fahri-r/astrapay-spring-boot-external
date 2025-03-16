package com.astrapay.service;

import com.astrapay.dto.BaseApiDto;
import com.astrapay.dto.NoteDto;
import com.astrapay.dto.mapper.INoteMapper;
import com.astrapay.entity.Note;
import com.astrapay.repository.INoteRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NoteService {
    private final INoteRepository noteRepository;
    private final INoteMapper noteMapper;

    public NoteService(INoteRepository noteRepository, @Qualifier("INoteMapperImpl") INoteMapper noteMapper) {
        this.noteRepository = noteRepository;
        this.noteMapper = noteMapper;
    }

    public BaseApiDto<List<NoteDto>> getAllNotes() {
        var notes = noteRepository.findAll();

        if (notes.isEmpty()) {
            return new BaseApiDto<>(new ArrayList<>(), "No Notes Added");
        }
        var noteDtos = noteMapper.toDto(notes);

        return new BaseApiDto<>(noteDtos);
    }

    public BaseApiDto<Optional<NoteDto>> getNoteById(Integer id) {
        var findNote = noteRepository.findById(id);
        if (findNote.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Note Not Found");
        }

        var noteDto = findNote.map(noteMapper::toDto);
        return new BaseApiDto<>(noteDto);
    }

    public BaseApiDto<NoteDto> createOrUpdateNote(NoteDto noteDto) {
        Note entity;
        String message = null;
        if (noteDto.getId() == null) {
            // Create new note
            entity = noteMapper.toEntity(noteDto);
            message = "Note Successfully Added";

        } else {
            var findNote = noteRepository.findById(noteDto.getId());
            if (findNote.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Note Not Found");
            }
            // Update existing note
            entity = noteMapper.toEntity(noteDto);
            message = "Note Successfully Updated";
        }

        // Saving the entity
        var savedEntity = noteRepository.save(entity);
        var noteDtoSaved = noteMapper.toDto(savedEntity);

        return new BaseApiDto<>(noteDtoSaved, message);
    }

    public void deleteNote(Integer id) {
        var findNote = noteRepository.findById(id);
        if (findNote.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Note Not Found");
        }

        noteRepository.deleteById(id);
    }
}
