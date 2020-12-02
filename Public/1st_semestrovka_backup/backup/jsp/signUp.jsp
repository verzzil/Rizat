<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <title>Registration</title>
</head>
<body>
<form class="btn btn-group-vertical btn-outline-danger btn-sm" method="post" action="/signUp">
    <input name="firstName" type="text"
           placeholder="Enter name">
    <input name="lastName" type="text"
           placeholder="Enter lastname">
    <input name="email" type="text"
           placeholder="Enter your email">
    <input name="password" type="password"
           placeholder="Enter password">
    <p></p>
    <input class="btn btn-danger btn-sm" type="submit" value="Registration">
</form>
<ul>
    <c:forEach items="${violations}" var="error">
        <li>${error.getMessage()}</li>
    </c:forEach>
</ul>
<p></p>
<a class="btn btn-danger btn-sm" href="/login">Login</a>
</body>
</html>