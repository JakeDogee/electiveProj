<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page import="database.LoginDAO" %>
<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="database.CourseDAO" %>
<%@ page import="model.Course" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    LoginDAO userDao = new LoginDAO();
    List<User> users = userDao.getAllUsers();
    session.setAttribute("user-list", users);
    CourseDAO courseDAO = new CourseDAO();
    List<Course> courses = courseDAO.getAllCourses();
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
    <title>Users</title>
</head>
<style>

</style>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="nav-link active" href="/admin_menu.jsp"><fmt:message key="label.home"/></a>
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
                    <a class="nav-link active" href="admin_courses.jsp"><fmt:message key="label.courses"/></a>
                </li>
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
        <div class="d-flex py-3"><h3><fmt:message key="label.totalUsers"/>: <%=users.toArray().length%></h3></div>
        <table class="table table-light table-striped">
            <thead>
            <tr>
                <th scope="col">ID</th>
                <th scope="col"><fmt:message key="label.login"/></th>
                <th scope="col"><fmt:message key="label.firstName"/></th>
                <th scope="col"><fmt:message key="label.lastName"/></th>
                <th scope="col"><fmt:message key="label.role"/></th>
                <th scope="col"><fmt:message key="label.status"/></th>
                <th scope="col"><fmt:message key="label.courses"/></th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <%
                    if(!users.isEmpty()){
                        for(User p:users){

                %>
                <td><%=p.getId()%></td>
                <td><%=p.getLogin()%></td>
                <td><%=p.getFirstName()%></td>
                <td><%=p.getLastName()%></td>
                <td>
                    <div>
                        <div class="btn-group">
                            <button type="button" class="btn btn-secondary dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                                <%=p.getRole()%>
                            </button>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="/edit-role?id=<%=p.getId()%>&role=ADMIN"><fmt:message key="label.admin"/></a></li>
                                <li><a class="dropdown-item" href="/edit-role?id=<%=p.getId()%>&role=TEACHER"><fmt:message key="label.teacher"/></a></li>
                                <li><a class="dropdown-item" href="/edit-role?id=<%=p.getId()%>&role=USER"><fmt:message key="label.user"/></a></li>
                            </ul>
                        </div>
                    </div>
                </td>
                <td>
                    <form action="" method="post" class="form-inline" style="width: 150px;">
                        <input type="hidden" name="id" value="0" class="form-input">
                        <div class="form-group d-flex justify-content-between">
                            <a class="btn btn-sm btn-outline-secondary" href="/edit-status?id=<%=p.getId()%>&status=Banned" role="button" style="background-color: rgb(209, 24, 61); width: 70px;"><fmt:message key="label.ban"/></a>
                            <a class="btn btn-sm btn-outline-secondary" href="/edit-status?id=<%=p.getId()%>&status=Unbanned" role="button" style="background-color: rgb(22, 212, 22); width: 70px;"><fmt:message key="label.unban"/></a>
                            <a type="text" class="form-control" style="width: 150px;" readonly><%=p.getStatus()%></a>
                        </div>
                    </form>
                </td>
                <td>
                    <%
                        if(((p.getRole()).name()).equals("TEACHER")){

                    %>
                    <form action="" method="post">
                        <div class="btn-group">
                            <button type="button" class="btn btn-secondary dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                                <%=courseDAO.getCourseByTeacherId(p.getId())%>
                            </button>
                            <ul class="dropdown-menu">
                                <%
                                    if(!courses.isEmpty()){
                                        for(Course c:courses){

                                %>
                                <li><a class="dropdown-item" href="/edit-teacher?courseId=<%=c.getId()%>&userId=<%=p.getId()%>"><%=c.getName()%></a></li>
                                <%}
                                }%>
                            </ul>
                        </div>
                    </form>
                            <%}%>
                </td>
            </tr>
            <%}
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

