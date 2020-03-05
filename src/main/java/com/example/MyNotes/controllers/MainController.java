package com.example.MyNotes.controllers;

import com.example.MyNotes.entities.Note;
import com.example.MyNotes.entities.User;
import com.example.MyNotes.services.NoteService;
import com.example.MyNotes.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    private UserService userService;
    private NoteService noteService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setNoteService(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/")
    public String notes(Model model, Authentication auth, @RequestParam(value = "word", required = false) String word) {
//    public String notes(Model model, Authentication auth) {
        if(auth != null) {
            User userFromDB = userService.findByUsername(auth.getName());
            if (word != null){
                model.addAttribute("notes", noteService.findNotesByUserAndKeyword(userFromDB, word));
            } else {
                model.addAttribute("notes", noteService.findNotesByUsername(userFromDB.getUsername()));
            }

        }
        return "notes";
    }
}
