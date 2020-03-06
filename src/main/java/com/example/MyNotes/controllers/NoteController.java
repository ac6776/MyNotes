package com.example.MyNotes.controllers;

import com.example.MyNotes.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/note")
public class NoteController {
    private NoteService noteService;

    @Autowired
    public void setNoteService(NoteService noteService) {
        this.noteService = noteService;
    }


    @GetMapping("/delete/{id}")
    public String delNote(@PathVariable("id") Long id){
        noteService.deleteNoteById(id);
        return "redirect:/";
    }

    @GetMapping("/add")
    public String addNote(Model model) {
        return "add";
    }

    @PostMapping("/add")
    public String saveNote(@RequestParam(value = "title") String title, @RequestParam(value = "body") String body, Authentication auth) {
        noteService.save(title, body, auth.getName());
        return "redirect:/";
    }

}
