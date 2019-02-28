package com.leverx.leverxspringproj.controller;

import com.leverx.leverxspringproj.domain.Book;
import com.leverx.leverxspringproj.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//good
@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping(value="/Book")
    public List<Book> getBookAll() {
        return bookService.getBookAll();
    }

    @GetMapping(value="/Book/{id}")
    public Book getBook(@PathVariable String id) {
        return bookService.getBook(id);
    }

    @PostMapping(value="/Book")
    public void createBook(@RequestBody Book book) {
        bookService.createBook(book);
    }

    @DeleteMapping(value="/Book/{id}")
    public void deleteBook(@PathVariable String id) {
        bookService.deleteBook(id);
    }

    @PutMapping(value="/Book")
    public void updateBook(@RequestBody Book book) {
        bookService.updateBook(book);
    }

}
