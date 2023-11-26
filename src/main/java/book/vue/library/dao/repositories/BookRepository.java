package book.vue.library.dao.repositories;

import book.vue.library.dao.entities.BookDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<BookDetails, Long> {
    List<BookDetails> findByTitle(String title);
    @Query("SELECT b FROM BookDetails b WHERE b.isbnNumber = :isbnNumber")
    BookDetails findByIsbnNumber(String isbnNumber);
}
