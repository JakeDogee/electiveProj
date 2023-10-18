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
    <title>Profile</title>
</head>

<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
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
<div class="gall_bot" align="center">
    <div>
    <a class="nav-link active" href="#">ID ${ID}</a>
    <a class="nav-link active" href="#"><fmt:message key="label.login"/> ${Login}</a>
    <a class="nav-link active" href="#"><fmt:message key="label.firstName"/> ${FirstName}</a>
    <a class="nav-link active" href="#"><fmt:message key="label.lastName"/> ${LastName}</a>
    <a class="nav-link active" href="#"><fmt:message key="label.email"/> ${Email}</a>
    <a class="nav-link active" href="#"><fmt:message key="label.role"/> ${Role}</a>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>
