package servlets.servlet;

import database.CourseDAO;
import model.Course;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "RemoveCourseServlet", urlPatterns = "/remove-course")
public class RemoveCourseServlet extends HttpServlet {
    private CourseDAO courseDAO;

    @Override
    public void init() {
        courseDAO = new CourseDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try(PrintWriter out = response.getWriter()){
            String id = request.getParameter("id");
            if(id!=null){
                ArrayList<Course> courses = (ArrayList<Course>) request.getSession().getAttribute("course-list");
                if(courses != null){
                    for(Course c:courses){
                        if(c.getId()== Integer.parseInt(id)){
                            courses.remove(c);
                            courseDAO.removeCourse(c.getId());
                            break;
                        }
                    }
                    response.sendRedirect("admin_courses.jsp");
                }
            }else{
                response.sendRedirect("admin_courses.jsp");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

