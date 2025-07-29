package uz.pdp.library.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.pdp.library.model.AuthUser;
import uz.pdp.library.repository.AuthUserRepository;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final AuthUserRepository authUserRepository = AuthUserRepository.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/auth/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        AuthUser authUser = authUserRepository.findByUsername(username).orElse(null);
        if (authUser == null || !authUser.getPassword().equals(password)) {
            req.setAttribute("error", "Invalid username or password");
            req.getRequestDispatcher("/auth/login.jsp").forward(req, resp);
            return;
        }

        req.getSession().setAttribute("userId", authUser.getId());
        req.getSession().setAttribute("fullName", authUser.getFullName());
        resp.sendRedirect("/home");

    }
}
