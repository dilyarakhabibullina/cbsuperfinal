package ru.itpark.service;

import ru.itpark.domain.Recipe;
import ru.itpark.util.JdbcTemplate;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
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

    public int saveDataBase(Recipe recipe) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, SQLException {
        String url = "jdbc:sqlite:D:\\DANASHOP_test\\cbsuperfinal\\db1";
        Class.forName ("org.sqlite.JDBC").getDeclaredConstructor ( ).newInstance ( );
        Connection conn = DriverManager.getConnection (url);
        {
            String sql = "INSERT into RECIPES (id, name, ingredients, description) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement (sql);
            {
                preparedStatement.setString (1, recipe.generateId ( ));
                preparedStatement.setString (2, recipe.getName ( ));
                preparedStatement.setString (3, recipe.getIngredients ( ));
                preparedStatement.setString (4, recipe.getDescription ( ));

            }
            return preparedStatement.executeUpdate ( );
        }
    }

    public List<Recipe> searchByName(String name) throws SQLException {
        List<Recipe> foundByName = getAll ( );
        return foundByName.stream ( ).filter (o -> o.getName ( ).contains (name))
                          .collect (Collectors.toList ( ));
    }

    public int removeById(String id) throws SQLException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, InvocationTargetException {
        String url = "jdbc:sqlite:D:\\DANASHOP_test\\cbsuperfinal\\db1";
        Class.forName ("org.sqlite.JDBC").getDeclaredConstructor ( ).newInstance ( );
        Connection conn = DriverManager.getConnection (url);
        {
            String sql = "DELETE FROM recipes WHERE id=?";
            PreparedStatement preparedStatement = conn.prepareStatement (sql);
            {
                preparedStatement.setString (1, id);
                return preparedStatement.executeUpdate ( );
            }

        }

        // return JdbcTemplate.executeUpdate ("jdbc:sqlite:D:\\DANASHOP_test\\cbsuperfinal\\db1","DELETE FROM recipes WHERE id=?");

    }

    public Recipe getById(String id) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, SQLException {
        Recipe recipe = null;
        String url = "jdbc:sqlite:D:\\DANASHOP_test\\cbsuperfinal\\db1";
        Class.forName ("org.sqlite.JDBC").getDeclaredConstructor ( ).newInstance ( );
        Connection conn = DriverManager.getConnection (url);
        {
            String sql = "SELECT id, name, ingredients, description FROM recipes WHERE id=?";
            PreparedStatement preparedStatement = conn.prepareStatement (sql);
            preparedStatement.setString (1, id);
            ResultSet rs = preparedStatement.executeQuery ( );
            if (rs.next ( )) {
                recipe = new Recipe ( );
                recipe.setId (rs.getString (1));
                recipe.setName (rs.getString (2));
                recipe.setIngredients (rs.getString (3));
                recipe. setDescription (rs.getString (4));
                conn.close ( );
            }
            return recipe;
        }
    }

public int edit (String name, String ingredients, String description) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, SQLException {
        String url ="jdbc:sqlite:D:\\DANASHOP_test\\cbsuperfinal\\db1";
        Class.forName ("org.sqlite.JDBC").getDeclaredConstructor ().newInstance ();
        Connection conn = DriverManager.getConnection (url);
    {
        String sql = "UPDATE recipe SET name=?, ingredients=?, description=?";
        PreparedStatement preparedStatement = conn.prepareStatement (sql);
        {
            preparedStatement.setString (1, "name");
            preparedStatement.setString (2, "ingredients");
            preparedStatement.setString (3, "description");
            return preparedStatement.executeUpdate ();
        }




    }
}
//    public List<Recipe> deleteId(String id) throws NoSuchMethodException, IllegalAccessException, InstantiationException, SQLException, InvocationTargetException, ClassNotFoundException {
//        Recipe recipe = getById (id);
//        id=recipe.getId ();
//        removeById (id);
//        List<Recipe> foundToEdit = getAll();
//        return foundToEdit;
//    }

    public List<Recipe> deleteId(String id) throws SQLException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        Recipe recipe = getById (id);
        id = recipe.getId ( );
        removeById (id);
        List<Recipe> foundToEdit = getAll ( );
        return foundToEdit;

    }
}