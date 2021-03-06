package com.example.MyNotes.services;

import com.example.MyNotes.entities.Note;
import com.example.MyNotes.entities.User;
import com.example.MyNotes.repositories.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.MyNotes.repositories.NoteSpecification.*;

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

    public Note save(String title, String body, String username) {
        User userFromDb = userService.findByUsername(username);
        if(title.length() == 0) {
            title = "Empty title";
            if(body.length() != 0) {
                title = body.substring(0, (Math.min(body.length(), 10)));
            }
        }
        Note note = new Note(title, body, userFromDb);
        return noteRepository.save(note);
    }

    public void deleteNoteById(Long id){
        Note noteFromDb = noteRepository.findById(id).get();
        noteRepository.delete(noteFromDb);
    }

    public List<Note> findNoteByUsername(String username){
        User userFromDb = userService.findByUsername(username);
        return noteRepository.findAll(hasUser(userFromDb));
    }

    public List<Note> findNotesByUsernameAndKeyword(String username, String keyword){
        User userFromDb = userService.findByUsername(username);
        return noteRepository.findAll(Specification.where(hasUser(userFromDb).and(titleContains(keyword))).or(hasUser(userFromDb).and(bodyContains(keyword))));
    }
}
