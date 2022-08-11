package servlets.servlet;
import database.CourseDAO;
import model.Course;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "EditCourseServlet", urlPatterns = "/edit-course")
public class EditCourseServlet extends HttpServlet {
    private CourseDAO courseDAO;
    String id;

    @Override
    public void init() {
        courseDAO = new CourseDAO();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        id = request.getParameter("id");
        response.getWriter().append("Served at: ").append(request.getContextPath());


        RequestDispatcher dispatcher = request.getRequestDispatcher("editcourse.jsp");
        dispatcher.forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String duration = request.getParameter("duration");
        String theme = request.getParameter("theme");
        try(PrintWriter out = response.getWriter()){
            if(id!=null){
                ArrayList<Course> courses = (ArrayList<Course>) request.getSession().getAttribute("course-list");
                if(courses != null){
                    for(Course c:courses){
                        if(c.getId()== Integer.parseInt(id)){
                            courses.get(courses.indexOf(c)).setName(name);
                            courses.get(courses.indexOf(c)).setDuration(Integer.parseInt(duration));
                            courses.get(courses.indexOf(c)).setTheme(theme);
                            courseDAO.editCourse(Integer.parseInt(id),name,duration,theme);
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
}



