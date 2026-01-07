package com.example.library.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.library.service.BookEntity;
import com.example.library.repository.BookRepository;

@Service
public class BookService {

    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public List<BookEntity> getAll() {
        return repository.findAll();
    }

    public BookEntity save(BookEntity book) {
        return repository.save(book);
    }
    public void delete(Long id) {
        repository.deleteById(id);
    }

}
