package uz.pdp.jakarta_ee.jakarta_ee;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class CalculatorPageServlet extends HttpServlet {


    /*@Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getMethod());
        PrintWriter writer = resp.getWriter();
        writer.println("Hello From Servlet");
        writer.println("It is time : " + new Date());
        String username = req.getParameter("username");
        writer.println("Hello " + username);
    }*/

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getMethod());
        PrintWriter writer = resp.getWriter();
        writer.println("Hello From Servlet");
        writer.println("It is time : " + new Date());
        String username = req.getParameter("username");
        writer.println("Hello " + username);
    }
}
