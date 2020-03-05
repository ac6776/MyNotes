package com.example.MyNotes.repositories;

import com.example.MyNotes.entities.Note;
import com.example.MyNotes.entities.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class NoteRepositoryImpl implements NoteRepositoryCustom {
    EntityManager em;

    public NoteRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Note> findNotesByUserAndKeyword(User user, String keyword) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Note> cq = cb.createQuery(Note.class);

        Root<Note> note = cq.from(Note.class);
        List<Predicate> predicates = new ArrayList<>();

        if (user != null) {
            predicates.add(cb.equal(note.get("user"), user));
            if (keyword != null) {
                predicates.add(cb.like(note.get("title"), "%" + keyword + "%"));
                predicates.add(cb.like(note.get("body"), "%" + keyword + "%"));
            }
        }

        cq.where(predicates.toArray(new Predicate[0]));

        return em.createQuery(cq).getResultList();
    }
}
