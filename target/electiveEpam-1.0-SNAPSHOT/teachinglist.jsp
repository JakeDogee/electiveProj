<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page import="database.LoginDAO" %>
<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="database.CourseDAO" %>
<%@ page import="model.Course" %>
<%@ page import="model.Journal" %>
<%@ page import="database.JournalDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    LoginDAO userDao = new LoginDAO();
    List<User> users = userDao.getAllUsers();
    session.setAttribute("user-list", users);
    CourseDAO courseDAO = new CourseDAO();
    String login = (String) session.getAttribute("login");
    int id = userDao.getIdByLogin(login);
    List<Course> courses = courseDAO.getAllCoursesByTeacherId(id);
    session.setAttribute("course-list", courses);
    JournalDAO journalDao = new JournalDAO();

%>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="languages"/>

<html lang="${sessionScope.lang}">
<head>
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <!-- Bootstrap CSS -->
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
            crossorigin="anonymous"
    />
    <meta charset="ISO-8859-1"/>
    <title>Teaching</title>
</head>
<style>

</style>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <button
                class="navbar-toggler"
                type="button"
                data-bs-toggle="collapse"
                data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent"
                aria-expanded="false"
                aria-label="Toggle navigation"
        >
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" href="/profile"><fmt:message key="label.profile"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/logout"><fmt:message key="label.logout"/></a>
                </li>
                <li><a href="?sessionLocale=ua">UA</a></li>
                <li><a href="?sessionLocale=en">EN</a></li>
            </ul>
        </div>
    </div>
</nav>


<main>
    <div class="container">
        <div class="d-flex py-3"><h3><fmt:message key="label.totalCourses"/>: <%=courses.toArray().length%>
        </h3></div>
        <table class="table table-light table-striped">
            <thead>
            <tr>
                <th scope="col">ID</th>
                <th scope="col"><fmt:message key="label.name"/></th>
                <th scope="col"><fmt:message key="label.theme"/></th>
                <th scope="col"><fmt:message key="label.action"/></th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <%
                    if (!courses.isEmpty()) {
                        for (Course c : courses) {
                            List<Journal> journals = journalDao.getJournalListByCourse(c.getId());
                            List<User> currentUsers = userDao.getUsersListByJournalList(journals);

                %>
                <td><%=c.getId()%>
                </td>
                <td><%=c.getName()%>
                </td>
                <td><%=c.getTheme()%>
                </td>
                <td>
                    <div class="dropdown">
                        <a class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1"
                           data-bs-toggle="dropdown" aria-expanded="false" data-bs-auto-close="outside">
                            <fmt:message key="label.showUsers"/>
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                            <table class="table table-light table-striped">
                                <thead>
                                <tr>
                                    <th scope="col">ID</th>
                                    <th scope="col"><fmt:message key="label.login"/></th>
                                    <th scope="col"><fmt:message key="label.firstName"/></th>
                                    <th scope="col"><fmt:message key="label.lastName"/></th>
                                    <th scope="col"><fmt:message key="label.grade"/></th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <%
                                        if (!currentUsers.isEmpty()) {
                                            for (User p : currentUsers) {

                                    %>
                                    <td><%=p.getId()%>
                                    </td>
                                    <td><%=p.getLogin()%>
                                    </td>
                                    <td><%=p.getFirstName()%>
                                    </td>
                                    <td><%=p.getLastName()%>
                                    </td>
                                    <td>
                                        <div>
                                            <div class="btn-group">
                                                <button type="button" class="btn btn-secondary dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                                                    <%=journalDao.getGradeByCourseIdAndUserID(c.getId(),p.getId())%>
                                                </button>
                                                <ul class="dropdown-menu">
                                                    <li><a class="dropdown-item" href="/edit-grade?courseId=<%=c.getId()%>&userId=<%=p.getId()%>&grade=1">1</a></li>
                                                    <li><a class="dropdown-item" href="/edit-grade?courseId=<%=c.getId()%>&userId=<%=p.getId()%>&grade=2">2</a></li>
                                                    <li><a class="dropdown-item" href="/edit-grade?courseId=<%=c.getId()%>&userId=<%=p.getId()%>&grade=3">3</a></li>
                                                    <li><a class="dropdown-item" href="/edit-grade?courseId=<%=c.getId()%>&userId=<%=p.getId()%>&grade=4">4</a></li>
                                                    <li><a class="dropdown-item" href="/edit-grade?courseId=<%=c.getId()%>&userId=<%=p.getId()%>&grade=5">5</a></li>
                                                </ul>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                                <%
                                        }
                                    }
                                %>
                                </tbody>
                            </table>
                        </ul>
                    </div>
                </td>

            </tr>
            <%
                    }
                }
            %>
            </tbody>
        </table>
    </div>


</main>


<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"
></script>
</body>
</html>


