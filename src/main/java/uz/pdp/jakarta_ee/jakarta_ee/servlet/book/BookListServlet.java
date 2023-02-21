package uz.pdp.jakarta_ee.jakarta_ee.servlet.book;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.pdp.jakarta_ee.jakarta_ee.dao.BookDAO;

import java.io.IOException;

@WebServlet(name = "BookServlet", value = "/book/list")
public class BookListServlet extends HttpServlet {

    private final BookDAO bookDAO = new BookDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("books", bookDAO.findAll());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/book/book_list.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(405);
    }
}
