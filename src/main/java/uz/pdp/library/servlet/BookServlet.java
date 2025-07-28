package uz.pdp.library.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import uz.pdp.library.dao.AuthorDao;
import uz.pdp.library.dao.BookDao;
import uz.pdp.library.model.Author;
import uz.pdp.library.model.Book;
import uz.pdp.library.util.FileUtil;

import java.io.IOException;
import java.util.List;

@WebServlet("/book")
@MultipartConfig
public class BookServlet extends HttpServlet {
    private final BookDao dao = BookDao.getInstance();
    private final AuthorDao authorDao = AuthorDao.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getSession().getAttribute("userId") == null) {
            resp.sendRedirect("/login");
            return;
        }
        String action = req.getParameter("action");

        switch (action) {
            case "create" -> {
                createPage(req, resp);
            }
            case "update" -> {
                updatePage(req, resp);
            }
            default -> {

            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getSession().getAttribute("userId") == null) {
            resp.sendRedirect("/login");
            return;
        }
        String action = req.getParameter("action");

        switch (action) {
            case "delele" -> {

            }
            case "update" -> {
                update(req, resp);
            }
            default -> {
                create(req, resp);
            }
        }
    }

    private void create(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String name = req.getParameter("name");
        String authorId = req.getParameter("authorId");
        Integer publishYear = Integer.parseInt(req.getParameter("publishYear"));
        Part part = req.getPart("imgFile");
        String imgUrl = FileUtil.uploadFile(part);
        Book book = new Book();
        book.setName(name);
        book.setImageUrl(imgUrl);
        book.setAuthorId(authorId);
        book.setPublishYear(publishYear);
        dao.save(book);

        resp.sendRedirect("/home");
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String authorId = req.getParameter("authorId");
        String publishYear = req.getParameter("publishYear");

        Book book = dao.findById(id).orElseThrow(
                () -> new RuntimeException("Book not found")
        );

        book.setName(name);
        book.setAuthorId(authorId);
        book.setPublishYear(Integer.parseInt(publishYear));

        dao.save(book);

        resp.sendRedirect("/home");
    }

    private void createPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Author> authors = authorDao.findAll();
        req.setAttribute("authors", authors);
        req.getRequestDispatcher("/book/create.jsp").forward(req, resp);
    }

    private void updatePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("bookId");
        Book book = dao.findById(id).orElseThrow(
                () -> new RuntimeException("Book not found")
        );

        List<Author> authors = authorDao.findAll();

        req.setAttribute("book", book);
        req.setAttribute("authors", authors);
        req.getRequestDispatcher("/book/update.jsp").forward(req, resp);

    }
}
