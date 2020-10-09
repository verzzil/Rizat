package servlet;

import models.User;
import repository.UsersRepositoryJdbcImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/users")
public class UsersServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/task4";
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "123456";

    List<User> users;

    @Override
    public void init() throws ServletException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }

        try {
            Connection connection = DriverManager.getConnection(DB_URL,DB_USERNAME, DB_PASSWORD);
            UsersRepositoryJdbcImpl usersRepository = new UsersRepositoryJdbcImpl(connection);
            users = usersRepository.findAll();
        } catch (SQLException throwables) {
            throw new IllegalStateException(throwables);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("usersList", users);
        req.getRequestDispatcher("/jsp/users.jsp").forward(req,resp);
    }
}
