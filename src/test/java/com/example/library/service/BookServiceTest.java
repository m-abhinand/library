package com.example.library.service;

import com.example.library.service.BookEntity;
import com.example.library.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository repository;

    @InjectMocks
    private BookService service;

    @Test
    void shouldReturnAllBooks() {
        BookEntity book = new BookEntity();
        book.setTitle("Clean Code");
        book.setAuthor("Robert C. Martin");

        when(repository.findAll()).thenReturn(List.of(book));

        List<BookEntity> result = service.getAll();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getTitle()).isEqualTo("Clean Code");
    }
}
