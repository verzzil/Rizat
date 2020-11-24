package servlets;

import models.User;
import services.UsersServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    private UsersServiceImpl usersService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        usersService = (UsersServiceImpl) config.getServletContext().getAttribute("usersService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = (String) req.getSession().getAttribute("currentEmail");
        User user = usersService.getUserByEmail(email);
        req.setAttribute("email", email);
        req.setAttribute("firstName", user.getFirstName());
        req.setAttribute("lastName", user.getLastName());
        req.getRequestDispatcher("/jsp/profile.jsp").forward(req, resp);
    }
}

