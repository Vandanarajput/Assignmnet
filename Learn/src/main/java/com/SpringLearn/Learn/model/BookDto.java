package com.SpringLearn.Learn.model;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class BookDto {

	private Long id;

	@NotBlank(message = "Title is required")
	@Size(min = 3, max = 100, message = "Title must be 3–100 characters")
	private String title;

	@NotBlank(message = "Author is required")
	@Size(min = 3, max = 50, message = "Author must be 3–50 characters")
	private String author;

	@NotBlank(message = "ISBN is required")
	@Pattern(regexp = "\\d{13}", message = "ISBN must be exactly 13 digits")
	private String isbn;

	@NotNull(message = "Price is required")
	@DecimalMin(value = "0.0", inclusive = true, message = "Price must be ≥ 0.0")
	private BigDecimal price;

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
