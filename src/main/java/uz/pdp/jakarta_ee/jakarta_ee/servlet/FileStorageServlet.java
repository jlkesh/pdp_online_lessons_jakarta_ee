package uz.pdp.jakarta_ee.jakarta_ee.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@WebServlet(name = "FileStorageServlet", urlPatterns = "/storage/show")
public class FileStorageServlet extends HttpServlet {
    private static final Path rootPath = Path.of("/home/jlkesh/Desktop/uploads/lib");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fileName = request.getParameter("filename");
        Path path = rootPath.resolve(fileName);
        byte[] bytes = Files.readAllBytes(path);
        response.getOutputStream().write(bytes);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
