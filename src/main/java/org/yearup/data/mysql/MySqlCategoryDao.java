package org.yearup.data.mysql;

import org.springframework.stereotype.Component;
import org.yearup.data.CategoryDao;
import org.yearup.models.Category;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class MySqlCategoryDao extends MySqlDaoBase implements CategoryDao
{
    public MySqlCategoryDao(DataSource dataSource)
    {
        super(dataSource);
    }

    //Method to get all the categories.
    @Override
    public List<Category> getAllCategories()
    {
        //Initialize the list to hold the categories
        List<Category> categories = new ArrayList<>();

        //Select everything from the Categories table query
        String sql = "SELECT * FROM Categories ";

        //Initilize connection
        try (Connection connection = getConnection();
             //Initialize the prepared statement with the method of the connection
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            //Set a resultset with the preparedStatement's execute query method which will execute the preset query
             ResultSet resultSet = preparedStatement.executeQuery();
             {
                 //Iterate through the resultset
                while (resultSet.next()) {
                    //Call the helper method to create a category object according to the resultSet
                    Category category = mapRow(resultSet);
                    //Add it to the list
                    categories.add(category);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        //return the list
        return categories;
    }

    //Method to get a category by id
    @Override
    public Category getById(int categoryId)
    {
        //Create the query needed to filter the categories by id
        String query = "SELECT * FROM Categories WHERE category_id = ?";

        //Initialize connection
        try (Connection connection = getConnection();
             //Initialize prepared statement with the query
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            //Set the parameter id for the question mark of the query
            preparedStatement.setInt(1, categoryId);

            //Set a resultset with the preparedStatement's execute query method which will execute the preset query
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    //Call the helper method to create a category object according to the resultSet
                    Category category = mapRow(resultSet);
                    //Return found category
                    return category;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Return null if no category matching the category found
        return null;

    }

    //Method to create a new category
    @Override
    public Category create(Category category)
    {
        //Create the query needed to add a new category to the categories table
        String query = "INSERT INTO Categories (name, description) VALUES (?, ?)";

        //Initialize connection
        try (Connection connection = getConnection();
             //Initialize prepared statement with the query. Return the id of the category id
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            //Set the questionmarks to the according variable values of the argument
            preparedStatement.setString(1, category.getName());
            preparedStatement.setString(2, category.getDescription());

            //execute the query and save how many rows affected
            int rows = preparedStatement.executeUpdate();

            //If no rows were affected throw and exception because inserting failed
            if (rows == 0) {
                throw new SQLException("Insert failed, no rows affected!");
            }

            //Get the id of the category inserted
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    //Save the first int value of generated key
                    int generatedId = generatedKeys.getInt(1);
                    //Set that int/id to the category passed as an argument
                    category.setCategoryId(generatedId);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Return the updated category with a new id
        return category;
    }

    //Method to update a category.
    @Override
    public void update(int categoryId, Category category)
    {
        //initilize the query that would update a category with a given id
        String query = "UPDATE Categories SET name = ?, description = ? WHERE category_id = ?";

        //Initialize the connection
        try (Connection connection = getConnection();

             //Initialize the prepared stattement
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            //Give the category variables for updating
            preparedStatement.setString(1, category.getName());
            preparedStatement.setString(2, category.getDescription());
            //Give the category id that would be updated
            preparedStatement.setInt(3, categoryId);


            //Save how many rows affected
            int rows = preparedStatement.executeUpdate();

            //Throw an exception if no rows affected
            if (rows == 0) {
                throw new SQLException("Update failed, no rows affected!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Method to delete a category with a given id
    @Override
    public void delete(int categoryId)
    {
        //Initialize the query that would delete a category
        String query = "DELETE FROM Categories WHERE category_id = ?";


        //Initialize the connection
        try (Connection connection = getConnection();
             //Initialize the prepared statement
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            //Set the question mark to the id of the category you want to delete
            preparedStatement.setInt(1, categoryId);

            //Save the rows affected
            int rows = preparedStatement.executeUpdate();

            //If no rows are affected throw an exception
            if (rows == 0) {
                throw new SQLException("Delete failed, no rows affected!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //Helper method to create a category object if needed by the above methods
    private Category mapRow(ResultSet row) throws SQLException
    {
        int categoryId = row.getInt("category_id");
        String name = row.getString("name");
        String description = row.getString("description");

        Category category = new Category()
        {{
            setCategoryId(categoryId);
            setName(name);
            setDescription(description);
        }};

        return category;
    }

}
