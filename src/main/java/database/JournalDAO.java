package database;

import model.Journal;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class JournalDAO {

    public boolean validate(int userId, int courseId) throws ClassNotFoundException {
        boolean status = false;

        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/facultativ?useTimezone=true&serverTimezone=UTC", "root", "1234");

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection
                     .prepareStatement("select * from journal where id_course = ? and id_user = ? ")) {
            preparedStatement.setString(1, String.valueOf(courseId));
            preparedStatement.setString(2, String.valueOf(userId));

            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            status = rs.next();

        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }
        return status;
    }

    public void registerJournal(int idUser, int idCourse) throws ClassNotFoundException {
        String INSERT_USERS_SQL = "INSERT INTO journal" +
                " (grade, id_course, id_user, status) VALUES" +
                " (?, ?, ?, ?);";

        int result = 0;

        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/facultativ?useTimezone=true&serverTimezone=UTC", "root", "1234");

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setString(1, String.valueOf(0));
            preparedStatement.setString(2, String.valueOf(idCourse));
            preparedStatement.setString(3, String.valueOf(idUser));
            preparedStatement.setString(4, "Started");


            System.out.println(preparedStatement);
            result = preparedStatement.executeUpdate();
            // Step 3: Execute the query or update query

        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }
    }

    public List<Journal> getJournalListByCourse(int courseId) {
        List<Journal> journals = new ArrayList<Journal>();

        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/facultativ?useTimezone=true&serverTimezone=UTC", "root", "1234");

             PreparedStatement preparedStatement = connection
                     .prepareStatement("select * from journal WHERE id_course = ?;")) {
            preparedStatement.setString(1, String.valueOf(courseId));


            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Journal row = new Journal(0,0,0,0,null);
                row.setId(rs.getInt("id_journal"));
                row.setGrade(rs.getInt("grade"));
                row.setIdCourse(rs.getInt("id_course"));
                row.setIdUser(rs.getInt("id_user"));
                row.setStatus(rs.getString("status"));

                journals.add(row);
            }

        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }

        return journals;
    }

    public List<Journal> getJournalListByUser(int userId) {
        List<Journal> journals = new ArrayList<Journal>();

        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/facultativ?useTimezone=true&serverTimezone=UTC", "root", "1234");

             PreparedStatement preparedStatement = connection
                     .prepareStatement("select * from journal WHERE id_user = ?;")) {
            preparedStatement.setString(1, String.valueOf(userId));


            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Journal row = new Journal(0,0,0,0,null);
                row.setId(rs.getInt("id_journal"));
                row.setGrade(rs.getInt("grade"));
                row.setIdCourse(rs.getInt("id_course"));
                row.setIdUser(rs.getInt("id_user"));
                row.setStatus(rs.getString("status"));

                journals.add(row);
            }

        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }

        return journals;
    }

    public int getGradeByCourseIdAndUserID(int courseId, int userId) {

        int grade = 0;

        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/facultativ?useTimezone=true&serverTimezone=UTC", "root", "1234");

             PreparedStatement preparedStatement = connection
                     .prepareStatement("select * from journal WHERE id_course = ? AND id_user = ?;")) {
            preparedStatement.setString(1, String.valueOf(courseId));
            preparedStatement.setString(2, String.valueOf(userId));


            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                grade = rs.getInt("grade");
            }

        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }

        return grade;
    }

    public void editGradeByCourseIdAndUserID(int courseId, int userId, int grade) throws ClassNotFoundException {
        String CHANGE_GRADE_SQL = "UPDATE journal SET grade = ? WHERE id_course = ? AND id_user = ?;";

        int result = 0;

        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/facultativ?useTimezone=true&serverTimezone=UTC", "root", "1234");

             PreparedStatement preparedStatement = connection
                     .prepareStatement(CHANGE_GRADE_SQL)) {
            preparedStatement.setString(1, String.valueOf(grade));
            preparedStatement.setString(2, String.valueOf(courseId));
            preparedStatement.setString(3, String.valueOf(userId));


            System.out.println(preparedStatement);

            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }

    }

    public int delete(Journal journal) throws ClassNotFoundException {
        int result = 0;
        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/facultativ?useTimezone=true&serverTimezone=UTC", "root", "1234");

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection
                     .prepareStatement("delete from journal where id_user = ? and id_course = ?")) {
            preparedStatement.setString(1, String.valueOf(journal.getIdUser()));
            preparedStatement.setString(2, String.valueOf(journal.getIdCourse()));

            System.out.println(preparedStatement);
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }
        return result;
    }


    private static void printSQLException(SQLException ex) {
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
