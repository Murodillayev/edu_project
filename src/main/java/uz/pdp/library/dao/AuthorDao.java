package uz.pdp.library.dao;

import uz.pdp.library.model.Author;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AuthorDao {

    private static AuthorDao instance;

    public static AuthorDao getInstance() {

        if (instance == null) {
            instance = new AuthorDao();
        }
        return instance;

    }

    public Optional<Author> findById(String authorId) {

        try (Connection connection = DatabaseConnector.getConnection();) {

            String sql = "SELECT * FROM author WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, authorId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Author author = new Author();
                author.setId(resultSet.getString("id"));
                author.setFirstName(resultSet.getString("firstname"));
                author.setLastName(resultSet.getString("lastname"));
                return Optional.of(author);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return Optional.empty();
    }

    public List<Author> findAll() {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String sql = "SELECT * FROM author";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            List<Author> authors = new ArrayList<>();

            while (resultSet.next()) {
                Author author = new Author();
                author.setId(resultSet.getString("id"));
                author.setLastName(resultSet.getString("lastname"));
                author.setFirstName(resultSet.getString("firstname"));
                authors.add(author);

            }

            return authors;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void save(Author author) {

        PreparedStatement preparedStatement;
        Optional<Author> byId = findById(author.getId());

        try (Connection connection = DatabaseConnector.getConnection();) {
            String sql = (byId.isPresent()) ?
                    "update author set firstname = ?, lastname = ? where id = ?"
                    : "insert into author( firstname, lastname,id) values (?, ?, ?)";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, author.getFirstName());
            preparedStatement.setString(2, author.getLastName());
            preparedStatement.setString(3, author.getId());
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
