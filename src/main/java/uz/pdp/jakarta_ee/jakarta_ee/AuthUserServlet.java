package uz.pdp.jakarta_ee.jakarta_ee;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "AuthUserServlet", value = "/AuthUserServlet")
public class AuthUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String about = request.getParameter("about");
        String role = request.getParameter("role");
        Integer age = Integer.parseInt(request.getParameter("age"));
        AuthUser authUser = AuthUser.builder()
                .username(username)
                .email(email)
                .about(about)
                .role(role)
                .age(age)
                .build();

        em.persist(authUser);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
