package com.example.p2ncapas.Service    ;

import com.example.p2ncapas.Domain.DTO.CreateBookRequest;
import com.example.p2ncapas.Domain.DTO.UpdateBookRequest;
import com.example.p2ncapas.Domain.Entity.Book;
import jakarta.validation.Valid;

import java.util.List;

public interface iBookService {
    Book create(com.example.p2ncapas.Domain.DTO.@Valid CreateBookRequest request);
    List<Book> findAll();
    Book findByIsbn(String isbn);
    Book update(String id, UpdateBookRequest request);
    void delete(String id);
    List<Book> filter(String author, String language, String genre, Integer minPages, Integer maxPages);
}
