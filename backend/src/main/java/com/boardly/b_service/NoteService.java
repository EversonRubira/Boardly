package com.boardly.b_service;

import com.boardly.d_repository.NoteRepository;
import com.boardly.e_model.Note;
import com.boardly.f_dto.note.NoteDTO;
import com.boardly.g_mapper.NoteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class NoteService {

    private final NoteRepository noteRepository;

    public List<NoteDTO> getAllNotes() {
        return noteRepository.findAll().stream()
                .map(NoteMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<NoteDTO> getNoteById(String id) {
        return noteRepository.findById(id)
                .map(NoteMapper::toDTO);
    }

    public NoteDTO createNote(NoteDTO dto) {
        System.out.println("debug aqui");
        Note note = NoteMapper.toEntity(dto);
        Note saved = noteRepository.save(note);
        return NoteMapper.toDTO(saved);
    }

    public NoteDTO updateNote(String id, NoteDTO dto) {
        return noteRepository.findById(id).map(note -> {
            note.setTitle(dto.title());
            note.setContent(dto.content());
            Note updated = noteRepository.save(note);
            return NoteMapper.toDTO(updated);
        }).orElseThrow(() -> new RuntimeException("Note not found: " + id));
    }

    public void deleteNote(String id) {
        noteRepository.deleteById(id);
    }
}
