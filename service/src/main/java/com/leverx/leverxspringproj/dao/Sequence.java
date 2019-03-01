package com.leverx.leverxspringproj.dao;

import org.slf4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class Sequence {
    static public String getNextValue(DataSource dataSource, String sequenceName, Logger logger) {
        String resultID = "";

        try(Connection conn = dataSource.getConnection();
            PreparedStatement statement = conn.prepareStatement(String.format("SELECT \"%s\".NEXTVAL FROM DUMMY", sequenceName)))
        {
            ResultSet resultSet = statement.executeQuery();
            logger.info("Next value of author_id: " + resultSet);

            if (resultSet.next()){
                resultID = resultSet.getString(1);
            }

            throw new SQLException("ID wasn't generated");
        } catch (SQLException e) {
            logger.error("Can't get id from DUMMY" + e.getMessage());
        }

        return resultID;
    }
}