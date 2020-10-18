package servlet;

import models.User;
import repository.UsersRepositoryJdbcImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;

@WebServlet("/signIn")
public class SignInServlet extends HttpServlet {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/task4";
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "123456";

    private UsersRepositoryJdbcImpl usersRepository;

    @Override
    public void init() throws ServletException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }

        try {
            usersRepository = new UsersRepositoryJdbcImpl(
                    DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)
            );
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/html/signIn.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String pass = req.getParameter("password");

        Optional<User> currentUser = usersRepository.findUserByLogin(login);

        if (currentUser.isPresent()) {
            if (
                    currentUser.get().getFirstName().equals(login) &&
                            currentUser.get().getPassword().equals(pass)
            ) {
                Cookie cookie = new Cookie("Auth", currentUser.get().getUuid());
                resp.addCookie(cookie);
                resp.sendRedirect("/users");
            } else {
                resp.sendRedirect("/signIn");
            }
        } else {
            resp.sendRedirect("/signIn");
        }

    }
}
