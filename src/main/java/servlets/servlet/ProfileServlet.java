package servlets.servlet;

import database.LoginDAO;
import model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ProfileServle", urlPatterns = "/profile")
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;

        final HttpSession session = req.getSession();
        String login = (String) session.getAttribute("login");

        LoginDAO dao = new LoginDAO();

        User user = new User(992,"null3","newuser3@mail","password", User.ROLE.USER,"user3name","user3lastname","UNBANNED");
        user = dao.getUserListByLogin(login);

        request.setAttribute("ID", user.getId());
        request.setAttribute("FirstName", user.getFirstName());
        request.setAttribute("LastName", user.getLastName());
        request.setAttribute("Login", login);
        request.setAttribute("Password", user.getPassword());
        request.setAttribute("Email", user.getEmail());
        request.setAttribute("Role", user.getRole().name());

        request.getRequestDispatcher("profile.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Posted");

    }
}
