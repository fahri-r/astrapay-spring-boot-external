package com.astrapay.repository;
import com.astrapay.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface INoteRepository extends JpaRepository<Note, Long> {
}