package servlets.servlet;

import database.CourseDAO;
import model.Course;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CourseRegServlet", urlPatterns = "/newcourse")
public class CourseRegisterServlet extends HttpServlet {

    private CourseDAO courseDAO;

    @Override
    public void init() {
        courseDAO = new CourseDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.getWriter().append("Served at: ").append(request.getContextPath());

        RequestDispatcher dispatcher = request.getRequestDispatcher("courseregister.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String duration = request.getParameter("duration");
        String theme = request.getParameter("theme");

        Course course = new Course(0,null,0,0,0,null);
        course.setName(name);
        course.setDuration(Integer.parseInt(duration));
        course.setTeacherId(1);
        course.setTheme(theme);

        try {
            if (!courseDAO.validate(course)){
                courseDAO.registerCourse(course);
                return;
            }
            else
                response.sendRedirect("/newcourse");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
