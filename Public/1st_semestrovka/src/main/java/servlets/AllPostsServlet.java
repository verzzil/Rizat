package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import models.Post;
import services.PostService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/posts")
public class AllPostsServlet extends HttpServlet {

    private PostService postService;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void init(ServletConfig config) throws ServletException {
        postService = (PostService) config.getServletContext().getAttribute("postService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/html/posts.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonObject data = new Gson().fromJson(req.getReader(), JsonObject.class);
        String searchString = data.get("search").getAsString();

        List<Post> posts = postService.searchForPosts(searchString);

        resp.setContentType("application/json");
        String postsAsJson = objectMapper.writeValueAsString(posts);

        resp.getWriter().write(postsAsJson);
    }
}


