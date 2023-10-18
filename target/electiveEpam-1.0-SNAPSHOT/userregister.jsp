<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="languages"/>

<html lang="${sessionScope.lang}">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <meta charset="ISO-8859-1">
    <title>Register</title>
</head>
<body>
<div align="center">
    <li><a href="?sessionLocale=ua">UA</a></li>
    <li><a href="?sessionLocale=en">EN</a></li>
    <h1><fmt:message key="label.userRegForm"/></h1>
    <form action=<%=request.getContextPath()%>"/register" method="post">
        <table style="with: 80%">
            <tr>
                <td><fmt:message key="label.firstName"/></td>
                <td><input type="text" id="form2Example3" required placeholder="<fmt:message key="label.firstName"/>" name="firstName" class="form-control" /></td>
            </tr>
            <tr>
                <td><fmt:message key="label.lastName"/></td>
                <td><input type="text" id="form2Example4" required placeholder="<fmt:message key="label.lastName"/>" name="lastName" class="form-control" /></td>
            </tr>
            <tr>
                <td><fmt:message key="label.login"/></td>
                <td><input type="text" id="form2Example6" required placeholder="<fmt:message key="label.login"/>" name="login" class="form-control" /></td>
            </tr>
            <tr>
                <td><fmt:message key="label.password"/></td>
                <td><input type="password" id="form2Example2" required placeholder="<fmt:message key="label.password"/>" name="password" class="form-control" /></td>
            </tr>
            <tr>
                <td><fmt:message key="label.email"/></td>
                <td><input type="email" id="form2Example1" required placeholder="<fmt:message key="label.email"/>" name="email" class="form-control" /></td>
            </tr>
        </table>
        <input type="submit" value="<fmt:message key="label.submit"/>"/>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>
