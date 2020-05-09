package ru.itpark.util;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class JdbcTemplate {


    public JdbcTemplate() {
    }


    public static <T> List<T> executeQuery(String url, String sql, RowMapper<T> mapper) throws SQLException {
        try (
                Connection connection = DriverManager.getConnection (url);
                Statement statement = connection.createStatement ( );
                ResultSet resultSet = statement.executeQuery (sql)) {
            List<T> result = new LinkedList<> ( );
            while (resultSet.next ( )) {
                result.add (mapper.map (resultSet));

            }

            return result;

        }
    }
    public static int executeUpdate(String url, String sql) throws SQLException {
        Connection connection = DriverManager.getConnection (url);
        PreparedStatement preparedStatement = connection.prepareStatement (sql);
        int s = preparedStatement.executeUpdate (sql);
        return s;

    }


}