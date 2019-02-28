package com.leverx.leverxspringproj.dao;

import com.leverx.leverxspringproj.domain.Book;
import com.leverx.leverxspringproj.intfce.IBookDao;
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
public class BookDao implements IBookDao {

    private static final Logger logger = LoggerFactory.getLogger(BookDao.class);

    private final DataSource dataSource;

    @Autowired
    public BookDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Book> getById(String id) {

        Optional<Book> entity = Optional.<Book>empty();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmnt = conn.prepareStatement(
                     "SELECT TOP 1 * FROM \"javaCFMTA::ExtraInfo.Book\" WHERE \"book_id\" = ?"))
        {
            stmnt.setString(1, id);
            ResultSet result = stmnt.executeQuery();
            if (result.next()) {
                Book book = new Book();
                book.setBookId(id);
                book.setName(result.getString("book_id"));
                entity = Optional.of(book);
            } else {
                entity = Optional.empty();
            }
        } catch (SQLException e) {
            logger.error("Error while trying to get entity by Id: " + e.getMessage());
        }

        return entity;
    }

    @Override
    public List<Book> getAll() {
        List<Book> bookList = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmnt = conn.prepareStatement("SELECT * FROM \"javaCFMTA::ExtraInfo.Book\""))
        {
            ResultSet result = stmnt.executeQuery();

            while (result.next()) {
                Book book = new Book();
                book.setBookId(result.getString("book_id"));
                book.setName(result.getString("name"));
                bookList.add(book);
            }
        } catch (SQLException e) {
            logger.error("Error while trying to get list of entities: " + e.getMessage());
        }

        return bookList;
    }

    @Override
    public void save(Book entity) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmnt = conn.prepareStatement(
             "INSERT INTO \"javaCFMTA::ExtraInfo.Book\"(\"author_id\", \"book_id\", \"name\") VALUES (?,?,?)"))
        {
        	stmnt.setString(1, entity.getAuthorId());
        	stmnt.setString(1, entity.getBookId());
        	stmnt.setString(1, entity.getName());
            stmnt.execute();
        } catch (SQLException e) {
            logger.error("Error while trying to add entity: " + e.getMessage());
        }
    }

    @Override
    public void delete(String id) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmnt = conn.prepareStatement("DELETE FROM \"javaCFMTA::ExtraInfo.Book\" WHERE \"author_id\" = ?"))
        {
            stmnt.setString(1, id);
            stmnt.execute();
        } catch (SQLException e) {
            logger.error("Error while trying to delete entity: " + e.getMessage());
        }
    }

    @Override
    public void update(Book entity) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmnt = conn.prepareStatement(
                     "UPDATE \"javaCFMTA::ExtraInfo.Book\" SET \"name\" = ? WHERE \"book_id\" = ?"))
        {
            stmnt.setString(1, entity.getName());
            stmnt.setString(2, entity.getBookId());
            stmnt.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error while trying to update entity: " + e.getMessage());
        }
    }
}