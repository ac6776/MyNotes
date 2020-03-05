package com.example.MyNotes.controllers;

import com.example.MyNotes.entities.Note;
import com.example.MyNotes.repositories.NoteRepository;
import com.example.MyNotes.services.NoteService;
import com.example.MyNotes.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/note")
public class NoteController {
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

    @GetMapping("/{id}")
    public String note(Model model, @PathVariable("id") Long id){
        //some staff
        return "note";
    }

    @GetMapping("/delete/{id}")
    public String delNote(@PathVariable("id") Long id){
//        noteRepository.deleteNoteById(id);
        noteService.deleteNoteById(id);
        return "redirect:/";
    }

    @GetMapping("/add")
    public String addNote(Model model) {
        return "add";
    }

    @PostMapping("/add")
    public String saveNote(@RequestParam(value = "title") String title, @RequestParam(value = "body") String body, Authentication auth) {
        Note note = new Note(title, body, userService.findByUsername(auth.getName()));
        noteService.save(note);
        return "redirect:/";
    }

}
