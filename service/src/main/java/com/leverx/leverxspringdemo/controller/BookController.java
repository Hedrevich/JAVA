package com.leverx.leverxspringdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.leverx.leverxspringdemo.domain.Book;
import com.leverx.leverxspringdemo.service.BookService;

@RestController
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@GetMapping(value="/book")
	public List<Book> getAllBook() {
		return bookService.getBookAll();
	}
	
	@GetMapping(value="/book/{id}")
	public Book getBook(@PathVariable Long id) {
		return bookService.getBook(id);
	}
	
	@PostMapping(value="/book")
	public void createBook(@RequestBody Book book) {
		bookService.createBook(book);
	}
	
	@DeleteMapping(value="/book/{id}")
	public void deleteBook(@PathVariable Long id) {
		bookService.deleteBook(id);
	}
	
	@PutMapping(value="/book")
	public void updateBook(@RequestBody Book book) {
		bookService.updateBook(book);
	}
	
}
