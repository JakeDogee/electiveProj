package servlets.servlet;

import database.CourseDAO;
import database.JournalDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "RegisterForCourseServlet", urlPatterns = "/reg-for-course")
public class RegisterForCourseServlet extends HttpServlet {
    private JournalDAO journalDao;
    private CourseDAO courseDao;

    @Override
    public void init() {

        journalDao = new JournalDAO();
        courseDao = new CourseDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try(PrintWriter out = response.getWriter()){
            String userId = request.getParameter("userId");
            String courseId = request.getParameter("courseId");
            if(!journalDao.validate(Integer.parseInt(userId), Integer.parseInt(courseId))){

                journalDao.registerJournal(Integer.parseInt(userId), Integer.parseInt(courseId));
                courseDao.addUser(Integer.parseInt(courseId));
                response.sendRedirect(request.getParameter("viewid"));
            }
            else
                response.sendRedirect(request.getParameter("viewid"));
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }





    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
