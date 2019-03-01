package com.leverx.leverxspringproj.dao;

import com.leverx.leverxspringproj.domain.Author;
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
    private static final String TABLE_NAME = "javaCFMTA::ExtraInfo.Book";
    private static final String BOOK_ID = "book_id";
    private static final String BOOK_NAME = "name";
    private static final String AUTHOR_TABLE_NAME = "javaCFMTA::Author";
    private static final String AUTHOR_ID = "author_id";
    private final DataSource dataSource;

    @Autowired
    public BookDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Book> getById(String id) {

        Optional<Book> entity = Optional.empty();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     String.format("SELECT TOP 1 * FROM \"%s\" WHERE \"%s\" = ?", TABLE_NAME, BOOK_ID))) {
            statement.setString(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                Book book = new Book();
                book.setBookId(id);
                book.setName(result.getString(BOOK_ID));
                entity = Optional.of(book);
            } else {
                entity = Optional.empty();
            }
        } catch (SQLException e) {
            logger.error("Error while trying to get entity by Id: " + e.getMessage());
        }

        return entity;
    }

    public List<String> getAuthorBook(String id) {
        List<String> list = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     String.format("SELECT * FROM \"%s\" INNER JOIN \"%s\" ON \"%s\" = \"%s\" WHERE \"%s\" = ?", AUTHOR_TABLE_NAME, TABLE_NAME, AUTHOR_ID, BOOK_ID, AUTHOR_ID))) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                list.add(result.getString("book"));
                list.add(result.getString("author"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Can't join tables: " + e.getMessage());
        }
        return list;
    }

    @Override
    public List<Book> getAll() {
        List<Book> bookList = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     String.format("SELECT * FROM \"$s\"", TABLE_NAME))) {
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Book book = new Book();
                book.setBookId(result.getString(BOOK_ID));
                book.setName(result.getString(BOOK_NAME));
                bookList.add(book);
            }
        } catch (SQLException e) {
            logger.error("Error while trying to get list of entities: " + e.getMessage());
        }

        return bookList;
    }

    @Override
    public Author save(Book entity) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     String.format("INSERT INTO \"%s\"(\"author_id\", \"%s\", \"%s\") VALUES (?,?,?)", TABLE_NAME, BOOK_ID, BOOK_NAME))) {
            statement.setString(1, entity.getAuthorId());
            statement.setString(1, entity.getBookId());
            statement.setString(1, entity.getName());
            statement.execute();
        } catch (SQLException e) {
            logger.error("Error while trying to add entity: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Author delete(String id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     String.format("DELETE FROM \"%s\" WHERE \"%s\" = ?", TABLE_NAME, BOOK_ID))) {
            statement.setString(1, id);
            statement.execute();
        } catch (SQLException e) {
            logger.error("Can't delete entity: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Author update(Book entity) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     String.format("UPDATE \"%s\" SET \"%s\" = ? WHERE \"%s\" = ?", TABLE_NAME, BOOK_NAME, BOOK_ID))) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getBookId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error while trying to update entity: " + e.getMessage());
        }
        return null;
    }
}