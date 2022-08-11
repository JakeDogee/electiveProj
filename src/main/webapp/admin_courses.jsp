<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<%@ page import="database.CourseDAO" %>
<%@ page import="model.Course" %>
<%@ page import="java.util.List" %>
<%@ page import="database.LoginDAO" %>
<%@ page import="model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    CourseDAO courseDAO = new CourseDAO();
    List<Course> courses = courseDAO.getAllCourses();
    session.setAttribute("course-list", courses);
    LoginDAO userDao = new LoginDAO();
    List<User> users = userDao.getAllUsers();
    session.setAttribute("user-list", users);
    List<User> teachers = userDao.getAllTeachers();
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
    <title>AdminCourses</title>
</head>
<style>
    #NewCourse {
        background-color: green;
        position: relative;
        left: 50px;
    }

    #UserList {
        position: relative;
        left: 50px;
    }

</style>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="nav-link active home" href="/admin_menu.jsp"><fmt:message key="label.home"/></a>
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
                    <a class="nav-link active lang courses" href="admin_courses.jsp"><fmt:message key="label.courses"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active lang profile" href="/profile"><fmt:message key="label.profile"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link lang logout" href="/logout"><fmt:message key="label.logout"/></a>
                </li>
                <li><a href="?sessionLocale=ua">UA</a></li>
                <li><a href="?sessionLocale=en">EN</a></li>
            </ul>
        </div>
    </div>
</nav>

<main>
    <section class="py-5 text-center container">
        <div class="row py-lg-5">
            <div class="col-lg-6 col-md-8 mx-auto">
                <h1 class="fw-light"><fmt:message key="label.mainText"/></h1>
                <p class="lead text-muted">
                    <fmt:message key="label.agitation"/>
                </p>
            </div>
        </div>
    </section>

    <div class="album py-5 bg-light">
        <div class="container" align="center">
            <a
                    class="btn btn-dark"
                    data-bs-toggle="collapse"
                    href="#collapseExample"
                    role="button"
                    aria-expanded="false"
                    aria-controls="collapseExample"
                    id="Name"
            >
                <fmt:message key="label.sortByNameAZ"/>
            </a>
            <a
                    class="btn btn-dark"
                    data-bs-toggle="collapse"
                    href="#collapseExample"
                    role="button"
                    aria-expanded="false"
                    aria-controls="collapseExample"
                    id="Name2"
            >
                <fmt:message key="label.sortByNameZA"/>
            </a>
            <a
                    class="btn btn-dark"
                    data-bs-toggle="collapse"
                    href="#collapseExample"
                    role="button"
                    aria-expanded="false"
                    aria-controls="collapseExample"
                    id="Duration"
            >
                <fmt:message key="label.sortByDuration"/>
            </a>
            <a
                    class="btn btn-dark"
                    data-bs-toggle="collapse"
                    href="#collapseExample"
                    role="button"
                    aria-expanded="false"
                    aria-controls="collapseExample"
                    id="Stud"
            >
                <fmt:message key="label.sortByNumber"/>
            </a>
            <div class="btn-group">
                <button type="button" class="btn btn-secondary dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false" style="background-color: #212529; border-color: #212529;">
                    <fmt:message key="label.theme"/>
                </button>
                <ul class="dropdown-menu">
                    <li><a class="dropdown-item" href="#" id="Basic"><fmt:message key="label.basic"/></a></li>
                    <li><a class="dropdown-item" href="#" id="Middle"><fmt:message key="label.middle"/></a></li>
                    <li><a class="dropdown-item" href="#" id="Hard"><fmt:message key="label.hard"/></a></li>
                </ul>
            </div>
            <div class="btn-group">
                <button type="button" class="btn btn-secondary dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false" style="background-color: #212529; border-color: #212529;">
                    <fmt:message key="label.teacher"/>
                </button>
                <ul class="dropdown-menu">
                    <%
                        if (!teachers.isEmpty()) {
                            for (User t : teachers) {

                    %>
                    <li><a class="dropdown-item" href="#" id="Teacher" data-id="<%=t.getId()%>"><%=t.getFirstName()%> <%=t.getLastName()%></a></li>
                    <%
                            }
                        }
                    %>
                </ul>
            </div>
            <a
                    class="btn btn-dark "
                    role="button"
                    href="/newcourse"
                    aria-expanded="false"
                    aria-controls="collapseExample"
                    id="NewCourse"
            >
                <fmt:message key="label.newCourse"/>
            </a>
            <a
                    class="btn btn-dark"
                    role="button"
                    href="userlist.jsp"
                    aria-expanded="false"
                    aria-controls="collapseExample"
                    id="UserList"
            >
                <fmt:message key="label.userList"/>
            </a>

        </div>
        <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3" id="courses">
            <%
                if (!courses.isEmpty()) {
                    for (Course p : courses) {

            %>
            <div class="col" data-duration="<%=p.getDuration()%>" data-name="<%= p.getName() %>"
                 data-stud="<%=p.getStudNumb()%>" data-theme="<%= p.getTheme() %>" data-teacher="<%=p.getTeacherId()%>">
                <div class="card shadow-sm">
                    <svg
                            class="bd-placeholder-img card-img-top"
                            width="100%"
                            height="200"
                            xmlns="http://www.w3.org/2000/svg"
                            role="img"
                            aria-label="Placeholder: Эскиз"
                            preserveAspectRatio="xMidYMid slice"
                            focusable="false"
                    >
                        <title>Placeholder</title>
                        <rect width="100%" height="100%" fill="#4B0082"/>
                        <text x="46%" y="50%" fill="#eceeef" dy=".3em"><%= p.getName() %>
                        </text>
                    </svg>

                    <div class="card-body">
                        <%--                        <p class="card-text">Java IO</p>--%>
                        <div class="card-text category" style="color:blue"><%=p.getTheme()%>
                        </div>
                        <div class="card-text teacher"
                             style="color:blue"><%=(users.get((courseDAO.getTeacherIdByCourseId(p.getId())) - 1)).getFirstName()%>
                            <%=(users.get((courseDAO.getTeacherIdByCourseId(p.getId())) - 1)).getLastName()%>
                        </div>
                        <div
                                class="d-flex justify-content-between align-items-center"
                        >
                            <div class="btn-group">
                                <a
                                        type="button"
                                        class="btn btn-sm btn-outline-secondary"
                                        href="edit-course?id=<%=p.getId()%>"
                                >
                                    <fmt:message key="label.edit"/>
                                </a>
                                <a
                                        type="button"
                                        class="btn btn-sm btn-outline-secondary"
                                        href="remove-course?id=<%=p.getId()%>"
                                >

                                    <fmt:message key="label.remove"/>
                                </a>
                            </div>
                            <small class="text-muted"><%=p.getDuration()%> <fmt:message key="label.hrs"/></small>
                            <small class="text-muted"><%=p.getStudNumb()%> <fmt:message key="label.students"/></small>
                        </div>
                    </div>
                </div>
            </div>


            <%
                    }
                }
            %>


        </div>
    </div>
</main>

<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"
></script>
<script>

    document.querySelector('#Basic').onclick = filterBasic;
    document.querySelector('#Middle').onclick = filterMiddle;
    document.querySelector('#Hard').onclick = filterHard;
    document.querySelector('#Duration').onclick = durationSort;
    document.querySelector('#Name').onclick = nameSort;
    document.querySelector('#Name2').onclick = nameSort2;
    document.querySelector('#Stud').onclick = studSort;

    document.querySelectorAll("#Teacher").forEach(div =>
        div.onclick = filterTeacher
    )


    function durationSort() {
        let nav = document.querySelector('#courses');
        for (let i = 0; i < nav.children.length; i++) {
            for (let j = i; j < nav.children.length; j++) {
                if (+nav.children[i].getAttribute('data-duration') > +nav.children[j].getAttribute('data-duration')) {
                    replacedNode = nav.replaceChild(nav.children[j], nav.children[i]);
                    insertAfter(replacedNode, nav.children[i]);

                }
            }
        }
    }

    function nameSort() {
        let nav = document.querySelector('#courses');
        for (let i = 0; i < nav.children.length; i++) {
            for (let j = i; j < nav.children.length; j++) {
                if (nav.children[i].getAttribute('data-name') > nav.children[j].getAttribute('data-name')) {
                    replacedNode = nav.replaceChild(nav.children[j], nav.children[i]);
                    insertAfter(replacedNode, nav.children[i]);

                }
            }
        }

    }

    function nameSort2() {
        let nav = document.querySelector('#courses');
        for (let i = 0; i < nav.children.length; i++) {
            for (let j = i; j < nav.children.length; j++) {
                if (nav.children[i].getAttribute('data-name') < nav.children[j].getAttribute('data-name')) {
                    replacedNode = nav.replaceChild(nav.children[j], nav.children[i]);
                    insertAfter(replacedNode, nav.children[i]);

                }
            }
        }

    }

    function studSort() {
        let nav = document.querySelector('#courses');
        for (let i = 0; i < nav.children.length; i++) {
            for (let j = i; j < nav.children.length; j++) {
                if (+nav.children[i].getAttribute('data-stud') > +nav.children[j].getAttribute('data-stud')) {
                    replacedNode = nav.replaceChild(nav.children[j], nav.children[i]);
                    insertAfter(replacedNode, nav.children[i]);

                }
            }
        }
    }

    function filterBasic() {
        let nav = document.querySelector('#courses');
        for (let i = 0; i < nav.children.length; i++) {
            if (nav.children[i].getAttribute('data-theme') != "Basic") {
                nav.children[i].remove()
            }
        }
    }

    function filterMiddle() {
        let nav = document.querySelector('#courses');
        for (let i = 0; i < nav.children.length; i++) {
            if (nav.children[i].getAttribute('data-theme') != "Middle") {
                nav.children[i].remove()
            }
        }
    }

    function filterHard() {
        let nav = document.querySelector('#courses');
        for (let i = 0; i < nav.children.length; i++) {
            if (nav.children[i].getAttribute('data-theme') != "Hard") {
                nav.children[i].remove()
            }
        }
    }

    function filterTeacher() {
        let nav = document.querySelector('#courses');
        for (let i = 0; i < nav.children.length; i++) {
            let k = this.getAttribute('data-id')
            if (nav.children[i].getAttribute('data-teacher') != k) {
                nav.children[i].remove()
            }
        }
    }

    function insertAfter(elem, refElem) {
        return refElem.parentNode.insertBefore(elem, refElem.nextSibling);
    }
</script>
</body>
</html>


