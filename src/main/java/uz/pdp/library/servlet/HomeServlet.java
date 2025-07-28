package uz.pdp.library.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.pdp.library.dao.AuthorDao;
import uz.pdp.library.dao.BookDao;
import uz.pdp.library.dto.BookDto;
import uz.pdp.library.model.Author;
import uz.pdp.library.model.Book;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "home-servlet", value = {"/home", "/"})
public class HomeServlet extends HttpServlet {
    private final BookDao bookDao = BookDao.getInstance();
    private final AuthorDao authorDao = AuthorDao.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> books = bookDao.findAll();
        List<BookDto> dtos = toDto(books);
        req.setAttribute("books", dtos);
        req.getRequestDispatcher("index.jsp").forward(req, resp); // forward current http request


//        resp.sendRedirect("index.jsp"); // new http request
    }


    private List<BookDto> toDto(List<Book> books) {
        List<BookDto> dtos = new ArrayList<>();

        for (Book book : books) {
            BookDto dto = new BookDto();
            Author author = authorDao.findById(book.getAuthorId())
                    .orElseThrow(
                            () -> new RuntimeException("Author not found")
                    );
            dto.setId(book.getId());
            dto.setName(book.getName());
            dto.setPublishYear(book.getPublishYear());
            dto.setImageUrl(book.getImageUrl());
            dto.setAuthorName(author.getFirstName() + " " + author.getLastName());
            dtos.add(dto);

        }
        return dtos;
    }
}
