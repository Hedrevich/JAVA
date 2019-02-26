package com.leverx.leverxspringproj.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.leverx.leverxspringproj.domain.Shop;
import com.leverx.leverxspringproj.intfce.IShopDao;

@Repository
public class ShopDao implements IShopDao{

    private static final Logger logger = LoggerFactory.getLogger(ShopDao.class);

    @Autowired
    private DataSource dataSource;

    @Override
    public Optional<Shop> getById(String id) {
        Optional<Shop> entity = null;
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmnt = conn.prepareStatement(
                     "SELECT TOP 1 \"id\", \"shid\", \"flid\", \"name\", \"description\" FROM \"javaCFMTA::ExtraInfo.Shops\" WHERE \"id\" = ?")) {
            stmnt.setString(1, id);
            ResultSet result = stmnt.executeQuery();
            if (result.next()) {
                Shop shop = new Shop();
                shop.setId(id);
                shop.setShid(result.getString("shid"));
                shop.setFlid(result.getString("flid"));
                shop.setName(result.getString("name"));
                shop.setDescription(result.getString("description"));
                entity = Optional.of(shop);
            } else {
                entity = Optional.empty();
            }
        } catch (SQLException e) {
            logger.error("Error while trying to get entity by Id: " + e.getMessage());
        }
        return entity;
    }

    @Override
    public List<Shop> getAll() {
        List<Shop> personList = new ArrayList<Shop>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmnt = conn
                     .prepareStatement("SELECT \"id\", \"shid\", \"flid\", \"name\", \"description\" FROM \"javaCFMTA::ExtraInfo.Shops\"")) {
            ResultSet result = stmnt.executeQuery();
            while (result.next()) {
                Shop shop = new Shop();
                shop.setId(result.getString("id"));
                shop.setShid(result.getString("shid"));
                shop.setFlid(result.getString("flid"));
                shop.setName(result.getString("name"));
                shop.setDescription(result.getString("description"));
                personList.add(shop);
            }
        } catch (SQLException e) {
            logger.error("Error while trying to get list of entities: " + e.getMessage());
        }
        return personList;
    }

    @Override
    public void save(Shop entity) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmnt = conn.prepareStatement(
                     "INSERT INTO \"javaCFMTA::ExtraInfo.Shops\"(\"id\", \"shid\", \"flid\", \"name\", \"description\") VALUES (?,?,?,?)")) {
        	stmnt.setString(1, entity.getId());
        	stmnt.setString(1, entity.getShid());
            stmnt.setString(2, entity.getFlid());
            stmnt.setString(3, entity.getName());
            stmnt.setString(4, entity.getDescription());
            stmnt.execute();
        } catch (SQLException e) {
            logger.error("Error while trying to add entity: " + e.getMessage());
        }
    }

    @Override
    public void delete(String id) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmnt = conn.prepareStatement("DELETE FROM \"javaCFMTA::ExtraInfo.Shops\" WHERE \"id\" = ?")) {
            stmnt.setString(1, id);
            stmnt.execute();
        } catch (SQLException e) {
            logger.error("Error while trying to delete entity: " + e.getMessage());
        }
    }

    @Override
    public void update(Shop entity) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmnt = conn.prepareStatement(
                     "UPDATE \"javaCFMTA::ExtraInfo.Shops\" SET \"description\" = ? WHERE \"id\" = ?")) {
            stmnt.setString(1, entity.getDescription());
            stmnt.setString(2, entity.getId());
            stmnt.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error while trying to update entity: " + e.getMessage());
        }
    }
}