package ru.itpark.service;

import ru.itpark.domain.Recipe;
import ru.itpark.util.JdbcTemplate;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class CookService {
    public CookService() {
        try {
            Class.forName ("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace ( );
        }
    }

    public List<Recipe> getAll() throws SQLException {
        return JdbcTemplate.executeQuery ("jdbc:sqlite:D:\\DANASHOP_test\\cbsuperfinal\\db1", "SELECT id, name, ingredients, description FROM recipes", resultSet -> new Recipe (
                resultSet.getString ("id"),
                resultSet.getString ("name"),
                resultSet.getString ("ingredients"),
                resultSet.getString ("description")


        ));
    }
    public List <Recipe> searchByName (String name) throws SQLException {
        List<Recipe> foundByName = getAll ();
        return foundByName.stream ().filter (o -> o.getName().contains (name))
                .collect(Collectors.toList());
    }
}
