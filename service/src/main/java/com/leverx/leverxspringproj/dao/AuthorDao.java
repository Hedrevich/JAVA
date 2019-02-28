package com.leverx.leverxspringproj.dao;

import com.leverx.leverxspringproj.domain.Author;
import com.leverx.leverxspringproj.intfce.IAuthorDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorDao implements IAuthorDao {
	private static final Logger logger = LoggerFactory.getLogger(AuthorDao.class);

	private final DataSource dataSource;


	@Autowired
	public AuthorDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override  
	public Optional<Author> getById(String id) {
		Optional<Author> entity = Optional.<Author>empty();
		try (Connection conn = dataSource.getConnection();
				PreparedStatement stmnt = conn.prepareStatement(
				"SELECT TOP 1 \"author_id\", \"name\" FROM \"javaCFMTA::Author\" WHERE \"author_id\" = ?"))
		{
			stmnt.setString(1, id);
			ResultSet result = stmnt.executeQuery();

			if (result.next()) {
				Author author = new Author();
				author.setAuthorId(id);
				author.setName(result.getString("name"));
				entity = Optional.of(author);
			} else {
				entity = Optional.empty();
			}
		} catch (SQLException e) {
			logger.error("Error while trying to get entity by Id: " + e.getMessage());
		}
		return entity;  
	} 
	 
	@Override
	public List<Author> getAll() {

		 List<Author> authorsList = new ArrayList<>();

		 try (Connection conn = dataSource.getConnection();
				 PreparedStatement stmnt = conn.prepareStatement(
				 		"SELECT \"author_id\", \"name\" FROM \"javaCFMTA::Author\""))
		 {
			 ResultSet result = stmnt.executeQuery();
			 while (result.next()) {
				 Author author = new Author();
				 author.setAuthorId(result.getString("author_id"));
				 author.setName(result.getString("name"));
				 authorsList.add(author);
			 }
		 } catch (SQLException e) {
			 logger.error("Error while trying to get list of entities: " + e.getMessage());
		 }
		 return authorsList;
	} 
	 
	@Override
	public void save(Author entity) {
		 try (Connection conn = dataSource.getConnection();
				 PreparedStatement stmnt = conn.prepareStatement(
				"INSERT INTO \"javaCFMTA::Author\"(\"name\") VALUES (?)"))
		 {
			 stmnt.setString(1, entity.getName());
			 stmnt.execute();
		 } catch (SQLException e) {
			 logger.error("Error while trying to add entity: " + e.getMessage());
		 }
	} 
	 
	@Override
	public void delete(String id) {
		 try (Connection conn = dataSource.getConnection();
			 	PreparedStatement stmnt = conn.prepareStatement(
			 			"DELETE FROM \"javaCFMTA::Author\" WHERE \"author_id\" = ?"))
		 {
			 stmnt.setString(1, id);
			 stmnt.execute();
		 } catch (SQLException e) {
			 logger.error("Error while trying to delete entity: " + e.getMessage());
		 }
	}
	 
	@Override
	public void update(Author entity) {
		 try(Connection conn = dataSource.getConnection();
				 PreparedStatement stmnt = conn.prepareStatement("UPDATE \"javaCFMTA::Author\" SET \"name\" = ? WHERE \"author_id\" = ?"))
		 {
			 stmnt.setString(1, entity.getName());
			 stmnt.setString(2, entity.getAuthorId());
			 stmnt.executeUpdate();
		 } catch (SQLException e) {
			 logger.error("Error while trying to update entity: " + e.getMessage());
		 }
	}
	
}
