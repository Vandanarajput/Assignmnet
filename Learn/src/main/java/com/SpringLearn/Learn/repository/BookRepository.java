package com.SpringLearn.Learn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SpringLearn.Learn.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	boolean existsByIsbn(String isbn);
}
