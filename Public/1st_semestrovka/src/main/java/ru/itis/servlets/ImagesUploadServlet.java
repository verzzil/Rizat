package ru.itis.servlets;
import ru.itis.models.FileInfo;
import ru.itis.services.FilesService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/images")
public class ImagesUploadServlet extends HttpServlet {

    private FilesService fileService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.fileService = (FilesService) config.getServletContext().getAttribute("filesUploadService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        String fileId = req.getParameter("id");

        FileInfo fileInfo = fileService.getFileInfo(Long.parseLong(fileId));
            response.setContentType(fileInfo.getType());
            response.setContentLength(fileInfo.getSize().intValue());
            response.setHeader("Content-Disposition", "filename=\"" + fileInfo.getOriginalFileName() + "\"");

            fileService.writeFileFromStorageById(Long.parseLong(fileId), response.getOutputStream());
            response.flushBuffer();

    }
}