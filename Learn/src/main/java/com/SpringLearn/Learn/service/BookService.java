package com.SpringLearn.Learn.service;

import java.util.List;
import java.util.Optional;

import com.SpringLearn.Learn.model.BookDto;

public interface BookService {
    BookDto createBook(BookDto bookDto);
    List<BookDto> getAllBooks();
    Optional<BookDto> getBookById(Long id);
    Optional<BookDto> updateBook(Long id, BookDto bookDto);
    boolean deleteBook(Long id);
}

