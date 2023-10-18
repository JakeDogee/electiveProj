package database;

import model.Course;
import model.Journal;
import model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoginDAO {

    public boolean validate(User user) throws ClassNotFoundException {
        boolean status = false;

        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/facultativ?useTimezone=true&serverTimezone=UTC", "root", "1234");

             PreparedStatement preparedStatement = connection
                     .prepareStatement("select * from users where login = ? and password = ? ")) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());

            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            status = rs.next();

        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }
        return status;
    }

    public User.ROLE getRoleByUser(User user) {
        User.ROLE result = User.ROLE.UNKNOWN;

        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/facultativ?useTimezone=true&serverTimezone=UTC", "root", "1234");

             PreparedStatement preparedStatement = connection
                     .prepareStatement("select role from users where login = ? and password = ? ")) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());

            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            String role = "";
            while (rs.next()) {
                role = rs.getString("role");
            }
            switch (role) {
                case "USER":
                    result = User.ROLE.USER;
                    break;
                case "ADMIN":
                    result = User.ROLE.ADMIN;
                    break;
                case "TEACHER":
                    result = User.ROLE.TEACHER;
                    break;
            }

        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }
        return result;
    }

    public String getStatusByUser(User user) {
        String result = "";

        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/facultativ?useTimezone=true&serverTimezone=UTC", "root", "1234");

             PreparedStatement preparedStatement = connection
                     .prepareStatement("select status from users where login = ? and password = ? ")) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());

            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            String status = "";
            while (rs.next()) {
                status = rs.getString("status");
            }
            switch (status) {
                case "Unbanned":
                    result = "Unbanned";
                    break;
                case "Banned":
                    result = "Banned";
                    break;
            }

        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }
        return result;
    }

    public User getUserListByLogin(String login) {
        User user = (new User(0,null,"newuser1@mail","password", User.ROLE.USER,"user1name","user1lastname","UNBANNED"));
        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/facultativ?useTimezone=true&serverTimezone=UTC", "root", "1234");

             PreparedStatement preparedStatement = connection
                     .prepareStatement("SELECT * FROM facultativ.users where login = ?;")) {
            preparedStatement.setString(1, login);

            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            String id_user = null, email = null, password = null, role = null, first_name = null, last_name = null;
            while (rs.next()) {
                role = rs.getString("role");
                id_user = rs.getString("id_user");
                email = rs.getString("email");
                password = rs.getString("password");
                first_name = rs.getString("first_name");
                last_name = rs.getString("last_name");
            }
            user.setLogin(login);
            user.setRole(User.ROLE.valueOf(role));
            user.setEmail(email);
            user.setPassword(password);
            user.setFirstName(first_name);
            user.setLastName(last_name);
            user.setId(Integer.parseInt(id_user));


        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }

        return user;
    }

    public int getIdByLogin(String login) {

        int id_user = 0;
        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/facultativ?useTimezone=true&serverTimezone=UTC", "root", "1234");

             PreparedStatement preparedStatement = connection
                     .prepareStatement("SELECT * FROM facultativ.users where login = ?;")) {
            preparedStatement.setString(1, login);

            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                id_user = Integer.parseInt(rs.getString("id_user"));
            }


        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }

        return id_user;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();

        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/facultativ?useTimezone=true&serverTimezone=UTC", "root", "1234");

             PreparedStatement preparedStatement = connection
                     .prepareStatement("select * from users")) {


            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                User row = new User(0,null,"newuser1@mail","password", User.ROLE.USER,"user1name","user1lastname","UNBANNED");
                row.setId(rs.getInt("id_user"));
                row.setLogin(rs.getString("login"));
                row.setEmail(rs.getString("email"));
                row.setPassword(rs.getString("password"));
                row.setRole(User.ROLE.valueOf(rs.getString("role")));
                row.setFirstName(rs.getString("first_name"));
                row.setLastName(rs.getString("last_name"));
                row.setStatus(rs.getString("status"));


                users.add(row);
            }

        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }

        return users;
    }

    public List<User> getAllTeachers() {
        List<User> teachers = new ArrayList<User>();

        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/facultativ?useTimezone=true&serverTimezone=UTC", "root", "1234");

             PreparedStatement preparedStatement = connection
                     .prepareStatement("select * from users WHERE role = 'Teacher'")) {


            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                User row = new User(0,null,"newuser1@mail","password", User.ROLE.USER,"user1name","user1lastname","UNBANNED");
                row.setId(rs.getInt("id_user"));
                row.setLogin(rs.getString("login"));
                row.setEmail(rs.getString("email"));
                row.setPassword(rs.getString("password"));
                row.setRole(User.ROLE.valueOf(rs.getString("role")));
                row.setFirstName(rs.getString("first_name"));
                row.setLastName(rs.getString("last_name"));
                row.setStatus(rs.getString("status"));


                teachers.add(row);
            }

        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }

        return teachers;
    }

    public void changeRoleById(int id, String role) throws ClassNotFoundException {
        String CHANGE_USER_SQL = "UPDATE users SET role = ?" +
                " WHERE id_user = ?";

        int result = 0;

        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/facultativ?useTimezone=true&serverTimezone=UTC", "root", "1234");

             PreparedStatement preparedStatement = connection.prepareStatement(CHANGE_USER_SQL)) {
            preparedStatement.setString(1, role);
            preparedStatement.setString(2, String.valueOf(id));


            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }
    }

    public void changeStatusById(int id, String status) throws ClassNotFoundException {
        String CHANGE_USER_SQL = "UPDATE users SET status = ?" +
                " WHERE id_user = ?";

        int result = 0;

        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/facultativ?useTimezone=true&serverTimezone=UTC", "root", "1234");

             PreparedStatement preparedStatement = connection.prepareStatement(CHANGE_USER_SQL)) {
            preparedStatement.setString(1, status);
            preparedStatement.setString(2, String.valueOf(id));


            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }
    }

    public List<User> getUsersListByJournalList(List<Journal> journals) {
        List<User> users = new ArrayList<User>();

        if (!journals.isEmpty()) {
            List<Integer> userIdList = new ArrayList<Integer>();
            for (Journal j : journals) {
                try (Connection connection = DriverManager
                        .getConnection("jdbc:mysql://localhost:3306/facultativ?useTimezone=true&serverTimezone=UTC", "root", "1234");

                     PreparedStatement preparedStatement = connection
                             .prepareStatement("select id_user from journal WHERE id_journal =?;")) {
                    preparedStatement.setString(1, String.valueOf(j.getId()));


                    System.out.println(preparedStatement);
                    ResultSet rs = preparedStatement.executeQuery();
                    while (rs.next()) {
                        int row = rs.getInt("id_user");

                        userIdList.add(row);
                    }

                } catch (SQLException e) {
                    // process sql exception
                    printSQLException(e);
                }
            }

            for (Integer i : userIdList) {
                try (Connection connection = DriverManager
                        .getConnection("jdbc:mysql://localhost:3306/facultativ?useTimezone=true&serverTimezone=UTC", "root", "1234");

                     PreparedStatement preparedStatement = connection
                             .prepareStatement("select * from users WHERE id_user =?;")) {
                    preparedStatement.setString(1, String.valueOf(i));


                    System.out.println(preparedStatement);
                    ResultSet rs = preparedStatement.executeQuery();
                    while (rs.next()) {
                        User rowU = (new User(0,null,"newuser1@mail","password", User.ROLE.USER,"user1name","user1lastname","UNBANNED"));
                        rowU.setId(rs.getInt("id_user"));
                        rowU.setLogin(rs.getString("login"));
                        rowU.setEmail(rs.getString("email"));
                        rowU.setPassword(rs.getString("password"));
                        rowU.setRole(User.ROLE.valueOf(rs.getString("role")));
                        rowU.setFirstName(rs.getString("first_name"));
                        rowU.setLastName(rs.getString("last_name"));
                        rowU.setStatus(rs.getString("status"));

                        users.add(rowU);
                    }

                } catch (SQLException e) {
                    // process sql exception
                    printSQLException(e);
                }
            }
        }

        return users;
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
