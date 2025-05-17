package com.SpringLearn.Learn.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "books")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 100)
	private String title;

	@Column(nullable = false, length = 50)
	private String author;

	@Column(nullable = false, unique = true, length = 13)
	private String isbn;

	@Column(nullable = false, precision = 10, scale = 2)
	private BigDecimal price;

	// ===== Constructors =====

	public Book() {
	}

	public Book(String title, String author, String isbn, BigDecimal price) {
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.price = price;
	}

	// ===== Getters & Setters =====

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}
