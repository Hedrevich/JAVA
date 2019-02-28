package com.leverx.leverxspringproj.intfce;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface IDao<T, K> {
	
	Optional<T> getById(K id);
	List<T> getAll();
	void save(T entity) throws SQLException;
	void delete(K id);
	void update(T entity);
	
}
