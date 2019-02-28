package com.leverx.leverxspringproj.service;

import com.leverx.leverxspringproj.dao.AuthorDao;
import com.leverx.leverxspringproj.domain.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

	private final AuthorDao authorDao;

	@Autowired
	public AuthorService(AuthorDao authorDao) {
		this.authorDao = authorDao;
	}

	public List<Author> getAuthorsAll() {
		return authorDao.getAll();
	}
	
	public Author getAuthor(String id) {
		Optional<Author> authorOptional = authorDao.getById(id);
		Author author = null;

		if(authorOptional.isPresent()) {
			author = authorOptional.get();
		}

		return author;
	}
	
	public void createAuthor(Author author) {
		authorDao.save(author);
	}
	
	public void updateAuthor(Author author) {authorDao.update(author);
	}
	
	public void deleteAuthor(String id) {
		authorDao.delete(id);
	}
	
}
