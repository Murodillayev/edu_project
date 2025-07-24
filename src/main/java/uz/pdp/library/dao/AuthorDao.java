package uz.pdp.library.dao;

import uz.pdp.library.model.Author;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AuthorDao {
    private List<Author> authors = new ArrayList<>();

    private static AuthorDao instance;

    public static AuthorDao getInstance() {

        if (instance == null) {
            instance = new AuthorDao();
        }
        return instance;

    }

    public Optional<Author> findById(String authorId) {

        return authors.stream().filter(
                author -> author.getId().equals(authorId)).findFirst();
    }

    public void create(Author author) {
        authors.add(author);
    }

    public List<Author> findAll() {
        return authors;
    }
}
