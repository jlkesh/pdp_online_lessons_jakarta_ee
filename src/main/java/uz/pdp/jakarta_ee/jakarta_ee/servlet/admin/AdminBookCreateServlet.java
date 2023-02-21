package uz.pdp.jakarta_ee.jakarta_ee.servlet.admin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import uz.pdp.jakarta_ee.jakarta_ee.dao.BookDAO;
import uz.pdp.jakarta_ee.jakarta_ee.dao.UploadDAO;
import uz.pdp.jakarta_ee.jakarta_ee.entity.Book;
import uz.pdp.jakarta_ee.jakarta_ee.entity.Upload;
import uz.pdp.jakarta_ee.jakarta_ee.utils.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@WebServlet(name = "AdminBookCreateServlet", value = "/admin/book/create")
@MultipartConfig
public class AdminBookCreateServlet extends HttpServlet {

    private static final Path rootPath = Path.of( "/home/jlkesh/Desktop/uploads/lib");
    private static final BookDAO bookDAO = new BookDAO();
    private static final UploadDAO uploadDAO = new UploadDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/create_book.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title"); // TODO: 21/02/23 bu yerga validation qo'yish kerak bo'ladi
        String description = request.getParameter("description"); // TODO: 21/02/23 bu yerga validation qo'yish kerak bo'ladi
        Part file = request.getPart("file"); // TODO: 21/02/23 bu yerga validation qo'yish kerak bo'ladi
        String originalName = file.getSubmittedFileName();
        String extension = StringUtils.fileExtension(originalName);
        Upload upload = Upload.childBuilder()
                .generatedName(UUID.randomUUID() + "." + extension)
                .originalName(originalName)
                .extension(extension)
                .size(file.getSize())
                .mimeType(file.getContentType())
                .build();
        uploadDAO.save(upload);
        Book book = Book.childBuilder()
                .title(title)
                .description(description)
                .file(upload)
                .build();
        bookDAO.save(book);
        Path pathToUpload = rootPath.resolve(upload.getGeneratedName());
        System.out.println(pathToUpload);
        Files.copy(file.getInputStream(), pathToUpload, StandardCopyOption.REPLACE_EXISTING);
        response.sendRedirect("/book/list");
    }
}
