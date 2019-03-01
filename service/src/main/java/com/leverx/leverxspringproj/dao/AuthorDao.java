package com.leverx.leverxspringproj.dao;

import com.leverx.leverxspringproj.domain.Author;
import com.leverx.leverxspringproj.domain.Book;
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
	private static final String TABLE_NAME = "javaCFMTA::Author";
	private static final String AUTHOR_ID = "author_id";
	private static final String AUTHOR_NAME = "name";
	private static final String SEQUENCE = "javaCFMTA::author_id";


	@Autowired
	public AuthorDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override  
	public Optional<Author> getById(String id) {
		Optional<Author> entity;
		entity = Optional.<Author>empty();
		try (Connection conn = dataSource.getConnection();
				PreparedStatement stmnt = conn.prepareStatement(
				String.format("SELECT TOP 1 * FROM \"%s\" WHERE \"%s\" = ?",TABLE_NAME,AUTHOR_NAME )))
		{
			stmnt.setString(1, id);
			ResultSet result = stmnt.executeQuery();

			if (result.next()) {
				Author author = new Author();
				author.setAuthorId(id);
				author.setName(result.getString(AUTHOR_NAME));
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

		 try (Connection connection = dataSource.getConnection();
				 PreparedStatement statement = connection.prepareStatement(
				 		String.format("SELECT \"%s\", \"%s\" FROM \"%s\"",AUTHOR_ID,AUTHOR_NAME,TABLE_NAME)))
		 {
			 ResultSet result = statement.executeQuery();
			 while (result.next()) {
				 Author author = new Author();
				 author.setAuthorId(result.getString(AUTHOR_ID));
				 author.setName(result.getString(AUTHOR_NAME));
				 authorsList.add(author);
			 }
		 } catch (SQLException e) {
			 logger.error("Error while trying to get list of entities: " + e.getMessage());
		 }
		 return authorsList;
	} 
	 
	@Override
	public Author save(Author entity) {
		entity.setAuthorId(Sequence.getNextValue(dataSource, SEQUENCE, logger));
		 try (Connection conn = dataSource.getConnection();
				 PreparedStatement statement = conn.prepareStatement(
                         String.format("INSERT INTO \"%s\"(\"%s\", \"%s\") VALUES (?, ?)", TABLE_NAME,AUTHOR_ID,AUTHOR_NAME)))
		 {
			 statement.setString(1, entity.getName());
			 statement.execute();
		 } catch (SQLException e) {
			 logger.error("Error while trying to add entity: " + e.getMessage());
		 }
		return entity;
	}
	 
	@Override
	public Author delete(String id) {
		 try (Connection conn = dataSource.getConnection();
			 	PreparedStatement statement = conn.prepareStatement(
			 			String.format("DELETE FROM \"%s\" WHERE \"%s\" = ?",TABLE_NAME,AUTHOR_ID)))
		 {
			 statement.setString(1, id);
			 statement.execute();
		 } catch (SQLException e) {
			 logger.error("Error while trying to delete entity: " + e.getMessage());
		 }
		return null;
	}



	@Override
	public Author update(Author entity) {
		 try(Connection conn = dataSource.getConnection();
				 PreparedStatement statement = conn.prepareStatement(
						 String.format("UPDATE \"%s\" SET \"%s\" = ? WHERE \"%s\") = ?",TABLE_NAME,AUTHOR_NAME,AUTHOR_ID)))
		 {
			 statement.setString(1, entity.getName());
			 statement.setString(2, entity.getAuthorId());
			 statement.executeUpdate();
		 } catch (SQLException e) {
			 logger.error("Error while trying to update entity: " + e.getMessage());
		 }
		return entity;
	}
	
}
