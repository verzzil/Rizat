package filters;

import dto.UserDto;
import services.UsersService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // преобразуем запросы к нужному виду
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);

        Boolean isAuthenticated = false;
        Boolean sessionExists = session != null;
        Boolean isLoginPage = request.getRequestURI().equals("/signIn") || request.getRequestURI().equals("/signUp");

        if (sessionExists) {
            isAuthenticated = session.getAttribute("auth") != null;
        }

        // если авторизован и запрашивает не логин или если не авторизован и запрашивает логин
        if (isAuthenticated && !isLoginPage || !isAuthenticated && isLoginPage) {
            // отдаем ему то, что он хочет
            filterChain.doFilter(request, response);
        } else if (isAuthenticated && isLoginPage) {
            // пользователь аутенцифицирован и запрашивает страницу входа
            response.sendRedirect("/profile");
        } else {
            // если пользователь не аутенцицицирован и запрашивает другие страницы
            response.sendRedirect("/signIn");
        }

    }

    @Override
    public void destroy() {

    }
}
