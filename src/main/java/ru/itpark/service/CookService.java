package ru.itpark.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.itpark.domain.Recipe;
import ru.itpark.util.JdbcTemplate;


import javax.servlet.http.Part;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


@Data
@AllArgsConstructor


public class CookService {
    private final Collection<Recipe> items = new LinkedList<>();
    //public Path path;
    private Path uploadPath;
    private Path path;
    private Part file;

    public CookService() {

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Recipe> getAll() throws SQLException {
        return JdbcTemplate.executeQuery("jdbc:sqlite:D:\\DANASHOP_test\\cbsuperfinal\\db1", "SELECT id, name, ingredients, description FROM recipes", resultSet -> new Recipe(
                resultSet.getString("id"),
                resultSet.getString("name"),
                resultSet.getString("ingredients"),
                resultSet.getString("description")
        ));
    }


    private void writeFile(String id, Part file, Path uploadPath) throws IOException {
        if (file != null && file.getSize() != 0) {
            try {
                file.write(uploadPath.resolve(id).toString());
                file.delete();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public int saveDataBase(Recipe recipe, Part file, Path uploadPath) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, SQLException, IOException {
        String url = "jdbc:sqlite:D:\\DANASHOP_test\\cbsuperfinal\\db1";
        Class.forName("org.sqlite.JDBC").getDeclaredConstructor().newInstance();
        Connection conn = DriverManager.getConnection(url);
        {
            String sql = "INSERT into RECIPES (id, name, ingredients, description) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            String onlyId = recipe.generateId();

            {
                preparedStatement.setString(1, onlyId);
                preparedStatement.setString(2, recipe.getName());
                preparedStatement.setString(3, recipe.getIngredients());
                preparedStatement.setString(4, recipe.getDescription());

            }
            writeFile(onlyId, file, uploadPath);
            return preparedStatement.executeUpdate();
//
        }
    }

    public List<Recipe> pageOne() throws SQLException {
        return getAll().subList(0, 3);
    }
    public List<Recipe> pageTwo() throws SQLException {
        //int length = getAll().size();
        return getAll().subList(3, 6);
    }
    public List<Recipe> pageThree() throws SQLException {
        return getAll().subList(6, 9);
    }
    public List<Recipe> pageLast() throws SQLException {
        int length = getAll().size();
        return getAll().subList(9 , length);
    }

    public List<Recipe> searchByName(String name) throws SQLException {
        List<Recipe> foundByName = getAll();
        return foundByName.stream().filter(o -> o.getName().contains(name))
                .collect(Collectors.toList());
    }
//    public List<Recipe> pageSearchByName (String name) throws SQLException {
//        List<Recipe> foundByName = getAll();
//        return foundByName.subList(0,5);
//    }

    public List<Recipe> searchByIngredients(String ingredients) throws SQLException {
        List<Recipe> foundByName = getAll();
        return foundByName.stream().filter(o -> o.getIngredients().contains(ingredients))
                .collect(Collectors.toList());
    }


//    public void update(Recipe recipe) {
////        boolean removed = items.removeIf(o -> o.getId().equals(house.getId()));
////        if (!removed) {
////            throw new NotFoundException();
////        }
//        items.add(recipe);
//    }


//    public int removeById(String id, Path uploadPath)
//            throws SQLException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, InvocationTargetException {
//        String url = "jdbc:sqlite:D:\\DANASHOP_test\\cbsuperfinal\\db1";
//        Class.forName ("org.sqlite.JDBC").getDeclaredConstructor ( ).newInstance ( );
//        Connection conn = DriverManager.getConnection (url);
//        {
//            String sql = "DELETE FROM recipes WHERE id=?";
//            PreparedStatement preparedStatement = conn.prepareStatement (sql);
//            {
//                preparedStatement.setString (1, id);
//                return preparedStatement.executeUpdate ( );
//            }
//
//        }

    // return JdbcTemplate.executeUpdate ("jdbc:sqlite:D:\\DANASHOP_test\\cbsuperfinal\\db1","DELETE FROM recipes WHERE id=?");


    public Recipe getById(String id) throws
            ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, SQLException {
        Recipe recipe = null;
        String url = "jdbc:sqlite:D:\\DANASHOP_test\\cbsuperfinal\\db1";
        Class.forName("org.sqlite.JDBC").getDeclaredConstructor().newInstance();
        Connection conn = DriverManager.getConnection(url);
        {
            String sql = "SELECT id, name, ingredients, description FROM recipes WHERE id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                recipe = new Recipe();
                recipe.setId(rs.getString(1));
                recipe.setName(rs.getString(2));
                recipe.setIngredients(rs.getString(3));
                recipe.setDescription(rs.getString(4));
                conn.close();
            }
            return recipe;
        }
    }

//public int edit (String name, String ingredients, String description) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, SQLException {
//        String url ="jdbc:sqlite:D:\\DANASHOP_test\\cbsuperfinal\\db1";
//        Class.forName ("org.sqlite.JDBC").getDeclaredConstructor ().newInstance ();
//        Connection conn = DriverManager.getConnection (url);
//    {
//        String sql = "UPDATE recipe SET name=?, ingredients=?, description=?";
//        PreparedStatement preparedStatement = conn.prepareStatement (sql);
//        {
//            preparedStatement.setString (1, "name");
//            preparedStatement.setString (2, "ingredients");
//            preparedStatement.setString (3, "description");
//            return preparedStatement.executeUpdate ();
//        }
//
//
//
//
//    }
// }
//    public List<Recipe> deleteId(String id) throws NoSuchMethodException, IllegalAccessException, InstantiationException, SQLException, InvocationTargetException, ClassNotFoundException {
//        Recipe recipe = getById (id);
//        id=recipe.getId ();
//        removeById (id);
//        List<Recipe> foundToEdit = getAll();
//        return foundToEdit;
//    }

//

    public Object removeById(String id, Path path)
            throws
            SQLException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, InvocationTargetException, IOException {
        String url = "jdbc:sqlite:D:\\DANASHOP_test\\cbsuperfinal\\db1";
        Class.forName("org.sqlite.JDBC").getDeclaredConstructor().newInstance();
        Connection conn = DriverManager.getConnection(url);
        {
            String sql = "DELETE FROM recipes WHERE id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            {
                preparedStatement.setString(1, id);
//                Files.deleteIfExists(path.resolve(id));
                return preparedStatement.executeUpdate();
            }

        }

    }

    public List<Recipe> deleteId(String id) throws
            NoSuchMethodException, IllegalAccessException, InstantiationException, SQLException, InvocationTargetException, ClassNotFoundException, IOException {
        Recipe recipe = getById(id);
        id = recipe.getId();
        removeById(id, uploadPath);
        List<Recipe> foundToEdit = getAll();
        return foundToEdit;
    }
}