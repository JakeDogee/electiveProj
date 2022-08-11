package servlets.servlet;

import database.LoginDAO;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "EditStatusServlet", urlPatterns = "/edit-status")
public class ChangeUserStatusServlet extends HttpServlet {
    private LoginDAO userDao;

    @Override
    public void init() {
        userDao = new LoginDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try(PrintWriter out = response.getWriter()){
            String id = request.getParameter("id");
            String status = request.getParameter("status");
            if(id!=null){
                ArrayList<User> users = (ArrayList<User>) request.getSession().getAttribute("user-list");
                if(users != null){
                    for(User u:users){
                        if(u.getId()== Integer.parseInt(id)){
                            users.get(users.indexOf(u)).setStatus(status);
                            userDao.changeStatusById(Integer.parseInt(id),status);
                            break;
                        }
                    }
                    response.sendRedirect("userlist.jsp");
                }
            }else{
                response.sendRedirect("userlist.jsp");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
