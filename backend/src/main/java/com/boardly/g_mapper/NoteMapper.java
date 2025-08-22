package com.boardly.g_mapper;

import com.boardly.e_model.Note;
import com.boardly.f_dto.note.NoteDTO;

public class NoteMapper {

    public static NoteDTO toDTO(Note note) {
        return new NoteDTO(
                note.getId(),
                note.getTitle(),
                note.getContent()
        );
    }

    public static Note toEntity(NoteDTO dto) {
        return Note.builder()
                .id(dto.id())
                .title(dto.title())
                .content(dto.content())
                .build();
    }
}
