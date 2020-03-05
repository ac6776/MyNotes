package com.example.MyNotes.services;

import com.example.MyNotes.entities.Note;
import com.example.MyNotes.entities.User;
import com.example.MyNotes.repositories.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    private NoteRepository noteRepository;
    private UserService userService;

    @Autowired
    public void setNoteRepository(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public Note save(Note note) {
        return noteRepository.save(note);
    }

    public void deleteNoteById(Long id){
        Note noteFromDb = noteRepository.findById(id).get();
        noteRepository.delete(noteFromDb);
    }

    public List<Note> findNotesByUsername(String name){
        User userFromDb = userService.findByUsername(name);
        return noteRepository.findNotesByUser(userFromDb);
    }

    public List<Note> findNotesByUserAndKeyword(User user, String keyword){
        return noteRepository.findNotesByUserAndKeyword(user, keyword);
    }

//    public Page<Note> getNotesWithPagingAndFiltering(int pageNumber, int pageSize, Specification<Note> noteSpecification) {
//        return noteRepository.findNotesByUser(noteSpecification, PageRequest.of(pageNumber, pageSize));
//    }

//    public Page<Note> getNotesPageable(Pageable pageable) {
//        return noteRepository.findAll(pageable);
//    }
}
