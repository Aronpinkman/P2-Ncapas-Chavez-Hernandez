package com.example.p2ncapas.Service.ServiceImplementation;

import com.example.p2ncapas.Domain.DTO.CreateBookRequest;
import com.example.p2ncapas.Domain.DTO.UpdateBookRequest;
import com.example.p2ncapas.Domain.Entity.Book;
import com.example.p2ncapas.Repository.iBookRepository;
import com.example.p2ncapas.Service.iBookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements iBookService {

    private final iBookRepository repository;

    public BookServiceImpl(iBookRepository repository) {
        this.repository = repository;
    }

    @Override
    public Book create(CreateBookRequest request) {
        if (repository.existsByIsbn(request.isbn())) {
            throw new IllegalArgumentException("El ISBN ya existe");
        }

        Book book = Book.builder()
                .title(request.title())
                .author(request.author())
                .isbn(request.isbn())
                .publicationYear(request.publicationYear())
                .language(request.language())
                .pages(request.pages())
                .genre(request.genre())
                .build();

        return repository.save(book);
    }

    @Override
    public List<Book> findAll() {
        return repository.findAll();
    }

    @Override
    public Book findByIsbn(String isbn) {
        return repository.findByIsbn(isbn).orElseThrow(() -> new RuntimeException("Libro no encontrado"));
    }

    @Override
    public Book update(String id, UpdateBookRequest request) {
        Book book = repository.findById(id).orElseThrow(() -> new RuntimeException("Libro no encontrado"));

        if (request.title() != null) book.setTitle(request.title());
        if (request.language() != null) book.setLanguage(request.language());

        return repository.save(book);
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }

    @Override
    public List<Book> filter(String author, String language, String genre, Integer minPages, Integer maxPages) {
        if (author != null) return repository.findByAuthor(author);
        if (language != null) return repository.findByLanguage(language);
        if (genre != null) return repository.findByGenre(genre);
        if (minPages != null && maxPages != null) return repository.findByPagesBetween(minPages, maxPages);
        return repository.findAll();
    }
}
