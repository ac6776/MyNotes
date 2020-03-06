package com.example.MyNotes.repositories;

import com.example.MyNotes.entities.Note;
import com.example.MyNotes.entities.User;
import org.springframework.data.jpa.domain.Specification;

public class NoteSpecification {
    public static Specification<Note> hasUser(User user) {
        return (note, cq, cb) -> cb.equal(note.get("user"), user);
    }

    public static Specification<Note> titleContains(String keyword) {
        return (note, cq, cb) -> cb.like(note.get("title"), "%" + keyword + "%");
    }

    public static Specification<Note> bodyContains(String keyword) {
        return (note, cq, cb) -> cb.like(note.get("body"), "%" + keyword + "%");
    }
}
