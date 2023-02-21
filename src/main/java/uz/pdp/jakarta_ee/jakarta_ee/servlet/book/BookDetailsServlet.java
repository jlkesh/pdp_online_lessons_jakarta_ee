package uz.pdp.jakarta_ee.jakarta_ee.servlet.book;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.pdp.jakarta_ee.jakarta_ee.dao.BookDAO;
import uz.pdp.jakarta_ee.jakarta_ee.entity.Book;

import java.io.IOException;

@WebServlet(name = "BookDetailsServlet", urlPatterns = "/book/detail/*")
public class BookDetailsServlet extends HttpServlet {
    private static final BookDAO bookDAO = new BookDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        String id = pathInfo.substring(1);
        Book book = bookDAO.findById(id);
        request.setAttribute("book", book);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/book/book_details.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
