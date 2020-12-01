package servlets;

import dto.PostDto;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import models.User;
import services.PostService;
import services.UsersService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@WebServlet("/posts/new")
public class NewPostServlet extends HttpServlet {

    private PostService postService;
    private UsersService usersService;
    private Validator validator;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.postService = (PostService) config.getServletContext().getAttribute("postService");
        this.validator = (Validator) config.getServletContext().getAttribute("validator");
        this.usersService = (UsersService) config.getServletContext().getAttribute("usersService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/newpost.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PostDto post = new PostDto();
        post.setName(req.getParameter("name"));
        post.setText(req.getParameter("text"));

        Set<ConstraintViolation<PostDto>> constraintViolations = validator.validate(post);

        if (!constraintViolations.isEmpty()) {
            req.setAttribute("violations", constraintViolations);
            req.getRequestDispatcher("/jsp/newpost.jsp").forward(req,resp);
        } else {
            String email = (String) req.getSession().getAttribute("currentEmail");
            User user = usersService.getUserByEmail(email);
            postService.savePost(post, user.getId());
            resp.sendRedirect("/posts");
        }
    }
}


