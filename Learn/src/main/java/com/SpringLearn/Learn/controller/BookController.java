package com.SpringLearn.Learn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.SpringLearn.Learn.model.BookDto;
import com.SpringLearn.Learn.service.BookService;

import jakarta.validation.Valid;
import jakarta.validation.ValidationException;

@Controller
@RequestMapping("/books")
public class BookController {

	private final BookService bookService;

	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	// List all books page
	@GetMapping
	public String listBooks(Model model) {
		model.addAttribute("books", bookService.getAllBooks());
		return "list"; // thymeleaf template name (list.html)
	}

	// Show form to create new book
	@GetMapping("/new")
	public String showCreateForm(Model model) {
		model.addAttribute("book", new BookDto());
		return "new"; // thymeleaf template name (new.html)
	}

	// Process the form to create new book
	@PostMapping
	public String createBook(@Valid @ModelAttribute("book") BookDto bookDto, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "new"; // return to form if validation fails
		}
		try {
			bookService.createBook(bookDto);
		} catch (ValidationException ex) {
			// Add error message for the ISBN field
			result.rejectValue("isbn", "error.book", ex.getMessage());
			return "new"; // go back to the form page with error
		}
		return "redirect:/books"; // after save redirect to list page
	}

	// Show form to edit book by id
	@GetMapping("/edit/{id}")
	public String showEditForm(@PathVariable Long id, Model model) {
		BookDto bookDto = bookService.getBookById(id).orElse(null);
		if (bookDto == null) {
			return "redirect:/books"; // if book not found, redirect to list
		}
		model.addAttribute("book", bookDto);
		return "edit"; // thymeleaf template for editing (edit.html)
	}

	// Process form to update book
	@PostMapping("/edit/{id}")
	public String updateBook(@PathVariable Long id, @Valid @ModelAttribute("book") BookDto bookDto,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "edit";
		}
		bookService.updateBook(id, bookDto);
		return "redirect:/books";
	}

	// Delete book by id
	@GetMapping("/delete/{id}")
	public String deleteBook(@PathVariable Long id) {
		bookService.deleteBook(id);
		return "redirect:/books";
	}
}
