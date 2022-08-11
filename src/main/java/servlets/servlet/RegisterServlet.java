package servlets.servlet;

import database.RegisterDAO;
import model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "RegServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {

    private RegisterDAO userDAO;

    @Override
    public void init() {
        userDAO = new RegisterDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.getWriter().append("Served at: ").append(request.getContextPath());

        RequestDispatcher dispatcher = request.getRequestDispatcher("userregister.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        User user = new User(992,"null3","newuser3@mail","password", User.ROLE.USER,"user3name","user3lastname","UNBANNED");
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setLogin(login);
        user.setPassword(password);
        user.setEmail(email);
        user.setRole(User.ROLE.USER);
        user.setStatus("Unbanned");

        try {
            if (!userDAO.validate(user)){
                userDAO.registerUser(user);
                return;
            }
            else
                response.sendRedirect("/register");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
