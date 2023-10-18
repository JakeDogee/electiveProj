<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<%@ page import="database.LoginDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="database.CourseDAO" %>
<%@ page import="model.Course" %>
<%@ page import="database.JournalDAO" %>
<%@ page import="model.Journal" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    LoginDAO userDao = new LoginDAO();
    JournalDAO journalDao = new JournalDAO();
    int id = userDao.getIdByLogin((String) session.getAttribute("login"));
    List<Journal> journals = journalDao.getJournalListByUser(id);
    session.setAttribute("journal-list", journals);
    CourseDAO courseDao = new CourseDAO();
    List<Course> courses = courseDao.getCourseListByJournalList(journals);
    session.setAttribute("course-list", courses);
%>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="languages"/>

<html lang="${sessionScope.lang}">
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <!-- Bootstrap CSS -->
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
            crossorigin="anonymous"
    />
    <meta charset="ISO-8859-1" />
    <title>Courses</title>
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
        <div class="d-flex py-3"><h3><fmt:message key="label.totalCourses"/> <%=courses.toArray().length%></h3></div>
        <table class="table table-light table-striped">
            <thead>
            <tr>
                <th scope="col">ID</th>
                <th scope="col"><fmt:message key="label.name"/></th>
                <th scope="col"><fmt:message key="label.theme"/></th>
                <th scope="col"><fmt:message key="label.status"/></th>
                <th scope="col"><fmt:message key="label.grade"/></th>

            </tr>
            </thead>
            <tbody>
            <tr>
                <%
                    int i = 0;
                    if(!courses.isEmpty()){
                        for(Course p:courses){


                %>
                <td><%=p.getId()%></td>
                <td><%=p.getName()%></td>
                <td><%=p.getTheme()%></td>
                <td><%=journals.get(i).getStatus()%></td>
                <td><%=journals.get(i).getGrade()%></td>
            </tr>
            <%i++;}
            }%>
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


