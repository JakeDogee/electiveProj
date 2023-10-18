package servlets.servlet;

import database.JournalDAO;
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

@WebServlet(name = "EditGrade", urlPatterns = "/edit-grade")
public class EditGradeServlet extends HttpServlet {
    private JournalDAO journalDao;

    @Override
    public void init() {
        journalDao = new JournalDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String courseId = request.getParameter("courseId");
            String userId = request.getParameter("userId");
            String grade = request.getParameter("grade");

            journalDao.editGradeByCourseIdAndUserID(Integer.parseInt(courseId), Integer.parseInt(userId), Integer.parseInt(grade));

            response.sendRedirect("teachinglist.jsp");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
