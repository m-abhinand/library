package com.example.library.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.library.service.BookEntity;
import com.example.library.service.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping
    public List<BookEntity> getAllBooks() {
        return service.getAll();
    }

    @PreAuthorize("hasRole('ADMIN')")

    @PostMapping
    public BookEntity addBook(@RequestBody BookEntity book) {
        return service.save(book);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        service.delete(id);
    }

}
