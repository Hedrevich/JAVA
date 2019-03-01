package com.leverx.leverxspringproj.intfce;

import com.leverx.leverxspringproj.domain.Author;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface IDao<T, K> {
	
	Optional<T> getById(K id);
	List<T> getAll();
	Author save(T entity) throws SQLException;
	Author delete(K id);
	Author update(T entity);
	
}
