package com.leverx.leverxspringdemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leverx.leverxspringdemo.dao.BookDao;
import com.leverx.leverxspringdemo.domain.Book;

@Service
public class BookService {
	
	@Autowired
	private BookDao bookDao;
	
	public List<Book> getBookAll() {
		return bookDao.getAll();
	}
	
	public Book getBook(Long id) {
		Optional<Book> bookOptional = this.bookDao.getById(id);
		Book book = null;
		if (bookOptional.isPresent()) {
			book = bookOptional.get();
		}
		return book;
	}
	
	public void createBook(Book book) {
		this.bookDao.save(book);
	}
	
	public void updateBook(Book book) {
		this.bookDao.update(book);
	}
	
	public void deleteBook(Long id) {
		this.bookDao.delete(id);
	}
	
}
