package com.example.p2ncapas.Repository;

import com.example.p2ncapas.Domain.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface iBookRepository extends JpaRepository<Book, String> {
    Optional<Book> findByIsbn(String isbn);
    boolean existsByIsbn(String isbn);

    List<Book> findByAuthor(String author);
    List<Book> findByLanguage(String language);
    List<Book> findByGenre(String genre);
    List<Book> findByPagesBetween(Integer min, Integer max);
}
