package database;

import model.Course;
import model.Journal;
import model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


class DAOTest {

    private RegisterDAO regDao = new RegisterDAO();
    private LoginDAO logDao = new LoginDAO();
    private JournalDAO journalDao = new JournalDAO();
    private CourseDAO courseDao = new CourseDAO();
    private List<User> userList = new ArrayList<>();

    {
        userList.add(new User(990,"user1","user1@mail","password", User.ROLE.USER,"user1name","user1lastname","Unbanned"));
        userList.add(new User(991,"user2","user2@mail","password", User.ROLE.USER,"user2name","user2lastname","Unbanned"));
        userList.add(new User(992,"user3","user3@mail","password", User.ROLE.USER,"user3name","user3lastname","Unbanned"));
        userList.add(new User(993,"user4","user4@mail","password", User.ROLE.USER,"user4name","user4lastname","Unbanned"));
    }

    private List<Journal> journalList = new ArrayList<>();

    {
        journalList.add(new Journal(990,1,990,990,"Started"));
        journalList.add(new Journal(991,2,990,990, "Started"));
        journalList.add(new Journal(992,3,990,990, "Started"));
        journalList.add(new Journal(993,5,990,990, "Started"));
    }

    private List<Course> courseList = new ArrayList<>();

    {
        courseList.add(new Course(990,"course1",990,990,990, "Basic"));
        courseList.add(new Course(991,"course2",990,990, 991, "Basic"));
        courseList.add(new Course(992,"course3",990,990, 992, "Basic"));
        courseList.add(new Course(993,"course4",990,990, 993, "Basic"));
    }


    @Test
    public void testRegDAO() throws Exception{
        userList.stream().forEach(x->{
            try {
                Assertions.assertEquals(false,regDao.validate(x));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        userList.stream().forEach(x->{
            try {
                Assertions.assertEquals(1,regDao.registerUser(x));
                Assertions.assertEquals(1,regDao.delete(x));
                regDao.delete(x);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void testLoginDAO() throws Exception {
        userList.stream().forEach(x -> {
            try {
                Assertions.assertEquals(false, regDao.validate(x));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        userList.stream().forEach(x -> {
            try {
                Assertions.assertEquals(1, regDao.registerUser(x));
                Assertions.assertEquals(User.ROLE.USER, logDao.getRoleByUser(x));
                Assertions.assertEquals(1, regDao.delete(x));
                regDao.delete(x);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        userList.stream().forEach(x -> {
            try {
                Assertions.assertEquals(1, regDao.registerUser(x));
                Assertions.assertEquals("Unbanned", logDao.getStatusByUser(x));
                Assertions.assertEquals(1, regDao.delete(x));
                regDao.delete(x);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        userList.stream().forEach(x -> {
            try {
                Assertions.assertEquals(1, regDao.registerUser(x));
                Assertions.assertTrue(logDao.getUserListByLogin(x.getLogin()) instanceof User);
                Assertions.assertEquals(1, regDao.delete(x));
                regDao.delete(x);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        userList.stream().forEach(x -> {
            try {
                Assertions.assertEquals(1, regDao.registerUser(x));
                Assertions.assertTrue((Integer) (logDao.getIdByLogin(x.getLogin())) instanceof Integer);
                Assertions.assertEquals(1, regDao.delete(x));
                regDao.delete(x);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        userList.stream().forEach(x -> {
            try {
                Assertions.assertEquals(1, regDao.registerUser(x));
                Assertions.assertTrue(logDao.getAllUsers() != null);
                Assertions.assertTrue(logDao.getAllUsers() instanceof List);
                Assertions.assertEquals(1, regDao.delete(x));
                regDao.delete(x);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        userList.stream().forEach(x -> {
            try {
                Assertions.assertEquals(1, regDao.registerUser(x));
                Assertions.assertTrue(logDao.getAllTeachers() != null);
                Assertions.assertTrue(logDao.getAllTeachers() instanceof List);
                Assertions.assertEquals(1, regDao.delete(x));
                regDao.delete(x);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        userList.stream().forEach(x -> {
            try {
                Assertions.assertTrue(logDao.getUsersListByJournalList(journalList) instanceof List);
                regDao.delete(x);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void testCourseDAO() throws Exception {
        courseList.stream().forEach(x -> {
            try {
                Assertions.assertEquals(false, courseDao.validate(x));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        courseList.stream().forEach(x->{
            try {
                Assertions.assertEquals(1,courseDao.registerCourse(x));
                Assertions.assertEquals(1,courseDao.delete(x));
                courseDao.delete(x);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        courseList.stream().forEach(x->{
            try {
                Assertions.assertEquals(1,courseDao.registerCourse(x));
                Assertions.assertTrue(courseDao.getAllCourses() != null);
                Assertions.assertTrue(courseDao.getAllCourses() instanceof List);
                Assertions.assertEquals(1,courseDao.delete(x));
                courseDao.delete(x);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        courseList.stream().forEach(x->{
            try {
                Assertions.assertEquals(1,courseDao.registerCourse(x));
                Assertions.assertTrue(courseDao.getAllCoursesByTeacherId(x.getTeacherId()) != null);
                Assertions.assertTrue(courseDao.getAllCoursesByTeacherId(x.getTeacherId()) instanceof List);
                Assertions.assertEquals(1,courseDao.delete(x));
                courseDao.delete(x);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        courseList.stream().forEach(x->{
            try {
                Assertions.assertEquals(1,courseDao.registerCourse(x));
                Assertions.assertTrue(courseDao.getCourseByTeacherId(x.getTeacherId()) != null);
                Assertions.assertTrue(courseDao.getCourseByTeacherId(x.getTeacherId()) instanceof String);
                Assertions.assertEquals(1,courseDao.delete(x));
                courseDao.delete(x);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        courseList.stream().forEach(x->{
            try {
                Assertions.assertEquals(1,courseDao.registerCourse(x));
                Assertions.assertNotNull(courseDao.getCourseListByJournalList(journalList));
                Assertions.assertTrue(courseDao.getCourseListByJournalList(journalList) instanceof List);
                Assertions.assertEquals(1,courseDao.delete(x));
                courseDao.delete(x);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void testJournalDAO() throws Exception {
        journalList.stream().forEach(x -> {
            try {
                Assertions.assertEquals(false, journalDao.validate(x.getIdUser(),x.getIdCourse()));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });


    }

}