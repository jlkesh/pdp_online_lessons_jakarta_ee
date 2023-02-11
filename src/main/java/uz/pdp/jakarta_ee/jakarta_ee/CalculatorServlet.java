package uz.pdp.jakarta_ee.jakarta_ee;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet(
        name = "uz.pdp.jakarta_ee.jakarta_ee.CalculatorServlet",
        urlPatterns = "/calc"
)
public class CalculatorServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getMethod());
        PrintWriter writer = resp.getWriter();
        writer.println("""
                <form method="post">
                    <label>A : <input type="number" name="a"></label>
                    <br>
                    <label>B : <input type="number" name="b"></label>
                    <br>
                    <label>
                        Operator
                        <select name="o">
                            <option value="-">Sub</option>
                            <option value="+">Add</option>
                            <option value="/">Div</option>
                            <option value="*">Mul</option>
                        </select>
                    </label>
                    <br>
                    <input type="submit" value="Calculate">
                    <br>
                </form>
                """);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getMethod());
        int a = Integer.parseInt(req.getParameter("a"));
        int b = Integer.parseInt(req.getParameter("b"));
        String o = req.getParameter("o");
        PrintWriter writer = resp.getWriter();
        String result = a + " " + o + " " + b + " = ";
        if (o.equals("+"))
            result = result + (a + b);
        else if (o.equals("-"))
            result = result + (a - b);
        else if (o.equals("*"))
            result = result + (a * b);
        else
            result = result + (a / b);
        writer.println("<h1>" + result + "</h1>");
        writer.println("<a href=\"/calc\">Calculate Again</a>");
    }
}
