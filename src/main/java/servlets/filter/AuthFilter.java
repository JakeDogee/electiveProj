package servlets.filter;


import database.LoginDAO;
import model.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import static java.util.Objects.nonNull;

public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(final ServletRequest request,
                         final ServletResponse response,
                         final FilterChain filterChain)

            throws IOException, ServletException {

        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;

        final String login = req.getParameter("login");
        final String password = req.getParameter("password");

        @SuppressWarnings("unchecked")
        final AtomicReference<LoginDAO> dao = (AtomicReference<LoginDAO>) req.getServletContext().getAttribute("dao");

        final HttpSession session = req.getSession();

        StringBuffer requestURL = ((HttpServletRequest) request).getRequestURL();
        if (((HttpServletRequest) request).getQueryString() != null) {
            requestURL.append("?").append(((HttpServletRequest) request).getQueryString());
        }
        String completeURL = requestURL.toString();

        User user = new User(992,"null3","newuser3@mail","password", User.ROLE.USER,"user3name","user3lastname","UNBANNED");
        user.setLogin(login);
        user.setPassword(password);

        //Registration form
        if (completeURL.equals("http://localhost:8080/register")){
            filterChain.doFilter(request, response);
        }

        if (completeURL.equals("http://localhost:8080/newcourse")){
            filterChain.doFilter(request, response);
        }


        //Logged user.
        if (nonNull(session) &&
                nonNull(session.getAttribute("login")) &&
                nonNull(session.getAttribute("password"))) {


            final User.ROLE role = (User.ROLE) session.getAttribute("role");

            filterChain.doFilter(request, response);

        //Not logged in user.
        } else {
            try {
                if (dao.get().validate(user)) {

                    final User.ROLE role = dao.get().getRoleByUser(user);
                    final String status = dao.get().getStatusByUser(user);


                    req.getSession().setAttribute("password", password);
                    req.getSession().setAttribute("login", login);
                    req.getSession().setAttribute("role", role);

                    moveToMenu(req, res, role, status);

                } else {

                    moveToMenu(req, res, User.ROLE.UNKNOWN, "Unbanned");
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    //Move user to menu.

    private void moveToMenu(final HttpServletRequest req,
                            final HttpServletResponse res,
                            final User.ROLE role,
                            final String status)
            throws ServletException, IOException {


        if(status.equals("Banned")){
            req.getRequestDispatcher("banned.jsp").forward(req, res);
        }
        if (role.equals(User.ROLE.ADMIN)) {

            req.getRequestDispatcher("admin_menu.jsp").forward(req, res);

        } else if (role.equals(User.ROLE.USER)) {

            req.getRequestDispatcher("user_menu.jsp").forward(req, res);

        }
        else if (role.equals(User.ROLE.TEACHER)) {
            req.getRequestDispatcher("teacher_menu.jsp").forward(req, res);
        }
        else {
            req.getRequestDispatcher("login.jsp").forward(req, res);
        }
    }


    @Override
    public void destroy() {
    }

}
