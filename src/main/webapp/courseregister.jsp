<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="languages"/>

<html lang="${sessionScope.lang}">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <meta charset="ISO-8859-1">
    <title>Course Reg</title>
</head>
<body>
<div align="center">
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
            <a class="nav-link active" href="/admin_menu.jsp"><fmt:message key="label.home" /></a>
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
    <h1>Course register form</h1>
    <form action=<%=request.getContextPath()%>"/newcourse" method="post">
        <table style="with: 80%">
            <tr>
                <td><fmt:message key="label.name"/></td>
                <td><input type="text" id="form2Example3" required placeholder="<fmt:message key="label.name"/>" name="name" class="form-control" /></td>
            </tr>
            <tr>
                <td><fmt:message key="label.duration"/></td>
                <td><input type="text" id="form2Example4" required placeholder="<fmt:message key="label.duration"/>" name="duration" class="form-control" /></td>
            </tr>
            <tr>
                <td><fmt:message key="label.theme"/></td>
                <td><input type="text" id="form2Example1" required placeholder="<fmt:message key="label.theme"/>" name="theme" class="form-control" /></td>
            </tr>
        </table>
        <input type="submit" value="<fmt:message key="label.submit"/>"/>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>
