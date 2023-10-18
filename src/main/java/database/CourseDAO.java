package database;

import model.Course;
import model.Journal;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {

    public boolean validate(Course course) throws ClassNotFoundException {
        boolean status = false;

        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/facultativ?useTimezone=true&serverTimezone=UTC", "root", "1234");

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection
                     .prepareStatement("select * from courses where name = ? and theme = ? ")) {
            preparedStatement.setString(1, course.getName());
            preparedStatement.setString(2, course.getTheme());

            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            status = rs.next();

        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }
        return status;
    }

    public int registerCourse(Course course) throws ClassNotFoundException {
        String INSERT_COURSES_SQL = "INSERT INTO courses" +
                "  (name, duration, stud_numb, id_teacher, theme) VALUES " +
                " (?, ?, ?, ?, ?);";

        int result = 0;

        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/facultativ?useTimezone=true&serverTimezone=UTC", "root", "1234");

             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_COURSES_SQL)) {
            preparedStatement.setString(1, course.getName());
            preparedStatement.setString(2, String.valueOf(course.getDuration()));
            preparedStatement.setString(3, String.valueOf(0));
            preparedStatement.setString(4, String.valueOf(course.getTeacherId()));
            preparedStatement.setString(5, course.getTheme());


            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }
        return result;
    }

    public List<Course> getAllCourses(){
        List<Course> courses = new ArrayList<Course>();

        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/facultativ?useTimezone=true&serverTimezone=UTC", "root", "1234");

             PreparedStatement preparedStatement = connection
                     .prepareStatement("select * from courses")) {


            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Course row = new Course(0,null,0,0,0,null);
                row.setId(rs.getInt("id_course"));
                row.setName(rs.getString("name"));
                row.setTheme(rs.getString("theme"));
                row.setDuration(rs.getInt("duration"));
                row.setStudNumb(rs.getInt("stud_numb"));
                row.setTeacherId(rs.getInt("id_teacher"));

                courses.add(row);
            }

        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }

        return courses;
    }

    public List<Course> getAllCoursesByTeacherId(int id){
        List<Course> courses = new ArrayList<Course>();

        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/facultativ?useTimezone=true&serverTimezone=UTC", "root", "1234");

             PreparedStatement preparedStatement = connection
                     .prepareStatement("SELECT * FROM facultativ.courses where id_teacher = ?;")) {
            preparedStatement.setString(1, String.valueOf(id));


            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Course row = new Course(0,null,0,0,0,null);
                row.setId(rs.getInt("id_course"));
                row.setName(rs.getString("name"));
                row.setTheme(rs.getString("theme"));
                row.setDuration(rs.getInt("duration"));
                row.setStudNumb(rs.getInt("stud_numb"));
                row.setTeacherId(rs.getInt("id_teacher"));

                courses.add(row);
            }

        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }

        return courses;
    }

    public void removeCourse(int id) throws ClassNotFoundException {
        String DELETE_COURSES_SQL = "DELETE FROM courses WHERE id_course" +
                " = ?;";

        int result = 0;

        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/facultativ?useTimezone=true&serverTimezone=UTC", "root", "1234");

             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_COURSES_SQL)) {
            preparedStatement.setString(1, String.valueOf(id));


            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }
    }

    public void editCourse(int id, String name, String duration, String theme) throws ClassNotFoundException {
        String EDIT_COURSES_SQL = "UPDATE courses SET name = ?, duration = ?, theme = ?" +
                " WHERE id_course" +
                " = ?;";

        int result = 0;

        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/facultativ?useTimezone=true&serverTimezone=UTC", "root", "1234");

             PreparedStatement preparedStatement = connection.prepareStatement(EDIT_COURSES_SQL)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, duration);
            preparedStatement.setString(3, theme);
            preparedStatement.setString(4, String.valueOf(id));

            System.out.println(preparedStatement);
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void changeTeacherById(int courseId, int teacherId) throws ClassNotFoundException {
            String CHANGE_COURSE_SQL = "UPDATE courses SET id_teacher = ?" +
                " WHERE id_course = ?";

        int result = 0;


        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/facultativ?useTimezone=true&serverTimezone=UTC", "root", "1234");

             PreparedStatement preparedStatement = connection.prepareStatement(CHANGE_COURSE_SQL)) {
            preparedStatement.setString(1, String.valueOf(teacherId));
            preparedStatement.setString(2, String.valueOf(courseId));


            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }
    }

    public String getCourseByTeacherId(int id){
        String name = null;
        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/facultativ?useTimezone=true&serverTimezone=UTC", "root", "1234");

             PreparedStatement preparedStatement = connection
                     .prepareStatement("SELECT * FROM facultativ.courses where id_teacher = ?;")) {
            preparedStatement.setString(1, String.valueOf(id));

            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                name = rs.getString("name");
            }


        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }

        return name;
    }

    public int getTeacherIdByCourseId(int id){
        int resultId = 0;
        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/facultativ?useTimezone=true&serverTimezone=UTC", "root", "1234");

             PreparedStatement preparedStatement = connection
                     .prepareStatement("SELECT * FROM facultativ.courses where id_course = ?;")) {
            preparedStatement.setString(1, String.valueOf(id));

            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                resultId = Integer.parseInt(rs.getString("id_teacher"));
            }

        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }

        return resultId;
    }

    public void addUser(int id) throws ClassNotFoundException {
        String ADD_USER_SQL = "UPDATE courses SET stud_numb =" +
                " stud_numb + 1 WHERE id_course = ?;";

        int result = 0;

        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/facultativ?useTimezone=true&serverTimezone=UTC", "root", "1234");

             PreparedStatement preparedStatement = connection.prepareStatement(ADD_USER_SQL)) {
            preparedStatement.setString(1, String.valueOf(id));


            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }
    }

    public List<Course> getCourseListByJournalList(List<Journal> journals) {
        List<Integer> ids = new ArrayList<Integer>();
        if (!journals.isEmpty()) {
            for (Journal j : journals) {
                try (Connection connection = DriverManager
                        .getConnection("jdbc:mysql://localhost:3306/facultativ?useTimezone=true&serverTimezone=UTC", "root", "1234");

                     PreparedStatement preparedStatement = connection
                             .prepareStatement("SELECT * FROM journal where id_journal = ?;")) {
                    preparedStatement.setString(1, String.valueOf(j.getId()));

                    System.out.println(preparedStatement);
                    ResultSet rs = preparedStatement.executeQuery();
                    while (rs.next()) {
                        int row = rs.getInt("id_course");

                        ids.add(row);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        List<Course> courses = new ArrayList<Course>();
        if(!ids.isEmpty()){
            for (int i : ids){
                try (Connection connection = DriverManager
                        .getConnection("jdbc:mysql://localhost:3306/facultativ?useTimezone=true&serverTimezone=UTC", "root", "1234");

                     PreparedStatement preparedStatement = connection
                             .prepareStatement("SELECT * FROM courses where id_course = ?;")) {
                    preparedStatement.setString(1, String.valueOf(i));

                    System.out.println(preparedStatement);
                    ResultSet rs = preparedStatement.executeQuery();
                    while (rs.next()) {
                        Course row = new Course(0,null,0,0,0,null);
                        row.setId(rs.getInt("id_course"));
                        row.setName(rs.getString("name"));
                        row.setDuration(rs.getInt("duration"));
                        row.setStudNumb(rs.getInt("stud_numb"));
                        row.setTeacherId(rs.getInt("id_teacher"));
                        row.setTheme(rs.getString("theme"));

                        courses.add(row);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return courses;
    }

    public int delete(Course course) throws ClassNotFoundException {
        int result = 0;
        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/facultativ?useTimezone=true&serverTimezone=UTC", "root", "1234");

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection
                     .prepareStatement("delete from courses where name = ? ")) {
            preparedStatement.setString(1, course.getName());

            System.out.println(preparedStatement);
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }
        return result;
    }

        private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}

