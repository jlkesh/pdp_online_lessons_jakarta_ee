package uz.pdp.jakarta_ee.jakarta_ee.servlet.auth;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.pdp.jakarta_ee.jakarta_ee.dao.AuthUserDAO;
import uz.pdp.jakarta_ee.jakarta_ee.dao.AuthUserOTPDAO;
import uz.pdp.jakarta_ee.jakarta_ee.entity.AuthUser;
import uz.pdp.jakarta_ee.jakarta_ee.entity.AuthUserOTP;
import uz.pdp.jakarta_ee.jakarta_ee.services.MailtrapService;
import uz.pdp.jakarta_ee.jakarta_ee.utils.PasswordUtils;
import uz.pdp.jakarta_ee.jakarta_ee.utils.StringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

@WebServlet(name = "AuthRegisterServlet", value = "/auth/register")
public class AuthRegisterServlet extends HttpServlet {

    private final AuthUserDAO authUserDAO = new AuthUserDAO();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/authuser/register.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirm_password = request.getParameter("confirm_password");

        Map<String, String> errors = new HashMap<>();

        if (!StringUtils.validEmail(email))
            errors.put("email_error", "Invalid email");
        else {
            authUserDAO.findByEmail(email).ifPresent(
                    (authUser -> errors.put("email_error", "Email Already Taken")));
        }

        if (password == null)
            errors.put("password_error", "Password is invalid");
        if (!Objects.equals(password, confirm_password))
            errors.put("password_error", "Password is invalid");

        if (!errors.isEmpty()) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/authuser/login.jsp");
            errors.forEach(request::setAttribute);
            dispatcher.forward(request, response);
        }

        AuthUser authUser = AuthUser
                .childBuilder()
                .email(email)
                .role("USER")
                .status(AuthUser.Status.IN_ACTIVE)
                .password(PasswordUtils.encode(password))
                .build();
        authUserDAO.save(authUser);

        CompletableFuture.runAsync(() -> {
            AuthUserOTPDAO authUserOTPDAO = AuthUserOTPDAO.getInstance();
            AuthUserOTP authUserOTP = AuthUserOTP
                    .childBuilder()
                    .userID(authUser.getId())
                    .build();
            authUserOTPDAO.save(authUserOTP);
            MailtrapService.sendActivationEmail(authUserOTP.getUserID());
        });

        response.sendRedirect("/auth/login");
    }
}
