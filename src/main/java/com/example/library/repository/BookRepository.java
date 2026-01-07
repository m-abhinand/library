package com.example.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.library.service.BookEntity;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
}
