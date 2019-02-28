package com.leverx.leverxspringproj.controller;

import com.leverx.leverxspringproj.domain.Author;
import com.leverx.leverxspringproj.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthorController {

	@Autowired
	private AuthorService authorService;
	   
	@GetMapping(value="/Author")
	public List<Author> getAuthorAll() {
		return authorService.getAuthorsAll();
	}
	
	@GetMapping(value="/Author/{id}")
	public Author getAuthor(@PathVariable String id) {
		return authorService.getAuthor(id);
	}    
	
	@PostMapping(value="/Author")
	public void createAuthor(@RequestBody Author author) {
		authorService.createAuthor(author);
	}    
	
	@DeleteMapping(value="/Author/{id}")
	public void deleteAuthor(@PathVariable String id) {
		authorService.deleteAuthor(id);
	}    
	
	@PutMapping(value="/Author")
	public void updateAuthor(@RequestBody Author author) {
		authorService.updateAuthor(author);
	}
	
}
