package ru.itis.servlets;

import ru.itis.services.FilesService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/uploaded/files")
public class FilesServlet extends HttpServlet {

    private FilesService filesService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.filesService = (FilesService) config.getServletContext().getAttribute("filesUploadService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        req.setAttribute("files", filesService.writeFilesFromStorage());
        req.getRequestDispatcher("/jsp/uploadedFiles.jsp").forward(req, response);
    }
}
