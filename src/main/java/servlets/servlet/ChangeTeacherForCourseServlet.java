package servlets.servlet;

import database.CourseDAO;
import database.LoginDAO;
import model.Course;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "EditStatusServlet", urlPatterns = "/edit-teacher")
public class ChangeTeacherForCourseServlet extends HttpServlet {
    private CourseDAO courseDAO;

    @Override
    public void init() {
        courseDAO = new CourseDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try(PrintWriter out = response.getWriter()){
            String courseId = request.getParameter("courseId");
            String userId = request.getParameter("userId");
            if(courseId!=null){
                ArrayList<Course> courses = (ArrayList<Course>) request.getSession().getAttribute("course-list");
                if(courses != null ){
                    for(Course c:courses){
                        if(c.getId()== Integer.parseInt(courseId)){
                            courses.get(courses.indexOf(c)).setTeacherId(Integer.parseInt(userId));
                            courseDAO.changeTeacherById(Integer.parseInt(courseId), Integer.parseInt(userId));
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
