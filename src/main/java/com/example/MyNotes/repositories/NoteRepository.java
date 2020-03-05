package com.example.MyNotes.repositories;

import com.example.MyNotes.entities.Note;
import com.example.MyNotes.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long>, NoteRepositoryCustom {
//    void deleteNoteById(Long id);

    List<Note> findNotesByUser(User user);
//    Page<Note> findNotesByUser(Specification<Note> noteSpecification, Pageable pageable);

}
