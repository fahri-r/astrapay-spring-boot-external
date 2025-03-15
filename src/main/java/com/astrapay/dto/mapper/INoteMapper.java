package com.astrapay.dto.mapper;

import com.astrapay.dto.NoteDto;
import com.astrapay.entity.Note;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface INoteMapper extends IEntityMapper<NoteDto, Note> {

    NoteDto toDto(final Note note);

    List<NoteDto> toDto(final List<Note> notes);

    Note toEntity(final NoteDto noteDto);

    List<Note> toEntity(final List <NoteDto> noteDtos);
}
