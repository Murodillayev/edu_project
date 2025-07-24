package uz.pdp.library.dao;

import uz.pdp.library.model.Author;
import uz.pdp.library.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookDao {

    private static final AuthorDao authorDao = AuthorDao.getInstance();
    private static final List<Book> books = new ArrayList<>();

    static {
        Author author = new Author();
        author.setFirstName("John");
        author.setLastName("Doe");
        authorDao.create(author);

        books.add(new Book("Otamdan qolgan dalalar", author.getId(), 2000));
        books.add(new Book("Dahshat", author.getId(), 2010));
        books.add(new Book("O'gri", author.getId(), 2011));
    }

    private static BookDao instance;

    public static BookDao getInstance() {
        if (instance == null) {
            instance = new BookDao();
        }
        return instance;
    }

    public List<Book> findAll() {
        return books;
    }

    public Optional<Book> findById(String id) {

        return books.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();
    }

    public void save(Book book) {

        Optional<Book> byId = findById(book.getId());

        if (byId.isPresent()) {
            books.removeIf(b -> b.getId().equals(book.getId()));
        }

        books.add(book);


    }
}
