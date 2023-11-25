package org.example.library.dao.repositories;

import org.example.library.dao.entities.BookDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<BookDetails, Long> {
    List<BookDetails> findByTitle(String title);
}
