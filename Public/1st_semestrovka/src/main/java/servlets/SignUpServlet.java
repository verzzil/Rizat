package servlets;
import dto.SignUpForm;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import services.signUp.SignUpService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet {

    private SignUpService signUpService;
    private Validator validator;

    @Override
    public void init(ServletConfig config) throws ServletException {
        signUpService = (SignUpService) config.getServletContext().getAttribute("signUpService");
        this.validator = (Validator) config.getServletContext().getAttribute("validator");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("violations", new ArrayList<Object>());
        req.getRequestDispatcher("/jsp/signUp.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SignUpForm form = new SignUpForm();

        form.setFirstName(req.getParameter("firstName"));
        form.setLastName(req.getParameter("lastName"));
        form.setEmail(req.getParameter("email"));
        form.setPassword(req.getParameter("password"));

        Set<ConstraintViolation<SignUpForm>> constraintViolations = validator.validate(form);

        if (!constraintViolations.isEmpty()) {
            for (ConstraintViolation<SignUpForm> constraintViolation: constraintViolations) {
                System.out.println(constraintViolation.getMessage());
            }
            req.setAttribute("violations", constraintViolations);
            req.getRequestDispatcher("/jsp/signUp.jsp").forward(req,resp);
        } else {
            signUpService.signUp(form);
            resp.sendRedirect("/signIn");
        }
    }
}