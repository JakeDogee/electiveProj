<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<html><fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="languages"/>

<html lang="${sessionScope.lang}">
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <meta charset="ISO-8859-1">
    <title>Login</title>
</head>
<style>
    * {
        padding: 0;
        margin: 0;
        border: 0;
    }
    *,
    *:before,
    :after {
        -moz-box-sizing: border-box;
        -webkit-box-sizing: border-box;
        box-sizing: border-box;
    }
    :focus,
    :active {
        outline: none;
    }
    a:focus,
    a:active {
        outline: none;
    }
    nav,
    footer,
    header,
    aside {
        display: block;
    }
    html,
    body {
        height: 100%;
        width: 100%;
        font-size: 100%;
        line-height: 1;
        font-size: 14px;
        -ms-text-size-adjust: 100%;
        -moz-text-size-adjust: 100%;
        -webkit-text-size-adjust: 100%;
    }
    input,
    button,
    textarea {
        font-family: inherit;
    }
    input::-ms-clear {
        display: none;
    }
    button {
        cursor: pointer;
    }
    button::-moz-focus-inner {
        padding: 0;
        border: 0;
    }
    a,
    a:visited {
        text-decoration: none;
    }
    a:hover {
        text-decoration: none;
    }
    ul li {
        list-style: none;
    }
    img {
        vertical-align: top;
    }
    h1,
    h2,
    h3,
    h4,
    h5,
    h6 {
        font-size: inherit;
        font-weight: inherit;
    }
    .login .form-control{
        width: 300px;
    }
    .password .form-control{
        width: 300px;
    }
    .ui-button{
        color: blue;
        width: 100px;
        height: 20px;
        border: 1px solid black;
        margin: 10px;
    }
</style>
<body>
<div align="center">
    <a href="?sessionLocale=ua">UA</a>
    <a href="?sessionLocale=en">EN</a>
    <form>
        <div class="login">
            <input type="text" required placeholder="<fmt:message key="label.login"/>" name="login" id="form2Example1" class="form-control" />
            <label class="form-label" for="form2Example1"><fmt:message key="label.login"/></label>
        </div>

        <!-- Password input -->
        <div class="password">
            <input type="password" required placeholder="<fmt:message key="label.password"/>" name="password" id="form2Example2" class="form-control" />
            <label class="form-label" for="form2Example2"><fmt:message key="label.password"/></label>
        </div>


        <!-- Submit button -->
        <input class="ui-button" type="submit" value="<fmt:message key="label.logIn"/>">

        <!-- Register buttons -->
        <div class="text-center">
            <p><fmt:message key="label.notAMember"/> <a href="/register"><fmt:message key="label.register"/></a></p>
        </div>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>
