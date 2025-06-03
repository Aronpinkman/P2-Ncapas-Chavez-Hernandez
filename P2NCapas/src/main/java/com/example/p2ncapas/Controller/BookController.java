package com.example.p2ncapas.Controller;

import com.example.p2ncapas.Domain.DTO.CreateBookRequest;
import com.example.p2ncapas.Domain.DTO.UpdateBookRequest;
import com.example.p2ncapas.Domain.Entity.Book;
import com.example.p2ncapas.Service.iBookService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/books")
public class BookController {

    private final iBookService service;

    public BookController(iBookService service) {
        this.service = service;
    }

    @PostMapping
    public Book create(@Valid @RequestBody CreateBookRequest request) {
        return service.create(request);
    }

    @GetMapping
    public List<Book> filter(
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String language,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) Integer minPages,
            @RequestParam(required = false) Integer maxPages
    ) {
        return service.filter(author, language, genre, minPages, maxPages);
    }

    @GetMapping("/{isbn}")
    public Book getByIsbn(@PathVariable String isbn) {
        return service.findByIsbn(isbn);
    }

    @PutMapping("/{id}")
    public Book update(@PathVariable String id, @RequestBody UpdateBookRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
