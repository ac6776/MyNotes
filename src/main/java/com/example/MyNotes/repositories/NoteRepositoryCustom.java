package com.example.MyNotes.repositories;

import com.example.MyNotes.entities.Note;
import com.example.MyNotes.entities.User;

import java.util.List;

public interface NoteRepositoryCustom {
    List<Note> findNotesByUserAndKeyword(User user, String keyword);
}
