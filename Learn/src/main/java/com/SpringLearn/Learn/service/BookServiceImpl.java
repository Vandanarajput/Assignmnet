package com.SpringLearn.Learn.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.SpringLearn.Learn.model.Book;
import com.SpringLearn.Learn.model.BookDto;
import com.SpringLearn.Learn.repository.BookRepository;

import jakarta.validation.ValidationException;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    // Constructor injection
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    private BookDto toDto(Book book) {
        BookDto dto = new BookDto();
        BeanUtils.copyProperties(book, dto);
        return dto;
    }

    private Book toEntity(BookDto dto) {
        Book book = new Book();
        BeanUtils.copyProperties(dto, book);
        return book;
    }

    @Override
    public BookDto createBook(BookDto bookDto) {
        // Check if ISBN already exists
        if (bookRepository.existsByIsbn(bookDto.getIsbn())) {
            throw new ValidationException("ISBN must be unique");
        }

        Book book = toEntity(bookDto);
        Book saved = bookRepository.save(book);
        return toDto(saved);
    }

    @Override
    public List<BookDto> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<BookDto> getBookById(Long id) {
        return bookRepository.findById(id)
                .map(this::toDto);
    }

    @Override
    public Optional<BookDto> updateBook(Long id, BookDto bookDto) {
        Optional<Book> existing = bookRepository.findById(id);

        if (existing.isEmpty()) {
            return Optional.empty();
        }

        // If ISBN is changed, check uniqueness
        if (!existing.get().getIsbn().equals(bookDto.getIsbn()) &&
                bookRepository.existsByIsbn(bookDto.getIsbn())) {
            throw new ValidationException("ISBN must be unique");
        }

        Book bookToUpdate = existing.get();
        // Update fields
        bookToUpdate.setTitle(bookDto.getTitle());
        bookToUpdate.setAuthor(bookDto.getAuthor());
        bookToUpdate.setIsbn(bookDto.getIsbn());
        bookToUpdate.setPrice(bookDto.getPrice());

        Book updated = bookRepository.save(bookToUpdate);
        return Optional.of(toDto(updated));
    }

    @Override
    public boolean deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            return false;
        }
        bookRepository.deleteById(id);
        return true;
    }
}

