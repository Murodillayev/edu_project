package uz.pdp.library.dao;

import uz.pdp.library.model.Author;
import uz.pdp.library.model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookDao {

    private static final AuthorDao authorDao = AuthorDao.getInstance();
    private static BookDao instance;

//    static {
//        BookDao dao = getInstance();
//        Author author = new Author();
//        author.setFirstName("Pepe");
//        author.setLastName("Pepe");
//        authorDao.save(author);
//
//        Book book = new Book();
//        book.setAuthorId(author.getId());
//        book.setName("Java OOP");
//        book.setPublishYear(2025);
//        dao.save(book);
//
//    }

    public static BookDao getInstance() {
        if (instance == null) {
            instance = new BookDao();
        }
        return instance;
    }

    public List<Book> findAll() {

        try (Connection connection = DatabaseConnector.getConnection()) {
            String sql = "SELECT * FROM book";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            List<Book> books = new ArrayList<>();

            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getString("id"));
                book.setName(resultSet.getString("name"));
                book.setPublishYear(resultSet.getInt("publish_year"));
                book.setAuthorId(resultSet.getString("author_id"));
                books.add(book);

            }

            return books;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Optional<Book> findById(String id) {

        try (Connection connection = DatabaseConnector.getConnection();) {

            String sql = "SELECT * FROM book WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getString("id"));
                book.setName(resultSet.getString("name"));
                book.setPublishYear(resultSet.getInt("publish_year"));
                book.setAuthorId(resultSet.getString("author_id"));
                return Optional.of(book);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return Optional.empty();
    }

    public void save(Book book) {

        PreparedStatement preparedStatement = null;
        Optional<Book> byId = findById(book.getId());

        try (Connection connection = DatabaseConnector.getConnection();) {
            String sql = null;

            if (byId.isPresent()) {
                sql = "update book set name = ?, publish_year = ?, author_id = ? where id = ?";
            } else {
                sql = "insert into book ( name, publish_year,author_id, id) values (?, ?, ?, ?)";
            }

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, book.getName());
            preparedStatement.setInt(2, book.getPublishYear());
            preparedStatement.setString(3, book.getAuthorId());
            preparedStatement.setString(4, book.getId());
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
