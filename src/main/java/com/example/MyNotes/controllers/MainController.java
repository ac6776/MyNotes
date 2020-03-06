package com.example.MyNotes.controllers;

import com.example.MyNotes.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    private NoteService noteService;

    @Autowired
    public void setNoteService(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/")
    public String notes(Model model, Authentication auth, @RequestParam(value = "word", required = false) String word) {
        if(auth != null) {
            if (word != null){
                model.addAttribute("notes", noteService.findNotesByUsernameAndKeyword(auth.getName(), word));
            } else {
                model.addAttribute("notes", noteService.findNoteByUsername(auth.getName()));
            }
            model.addAttribute("word", word);
        }
        return "notes";
    }
}
