package org.example.library.dao.repositories;

import org.example.library.dao.entities.AuthorDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AuthorDetailsRepository extends JpaRepository<AuthorDetails,Long> {
    @Query("SELECT b FROM AuthorDetails b WHERE b.id = :id")
    AuthorDetails findByAuthorId(Long id);

//    @Query("SELECT b FROM AuthorDetails b WHERE b.fullName = :fullName")
    AuthorDetails findByFullName(String fullName);
}
