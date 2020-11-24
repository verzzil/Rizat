<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<form method="post" action="/signUp">
    <input name="firstName" type="text"
           placeholder="Enter name">
    <input name="lastName" type="text"
           placeholder="Enter lastname">
    <input name="email" type="text"
           placeholder="Enter your email">
    <input name="password" type="password"
           placeholder="Enter password">
    <input type="submit" value="Registration">
</form>
<ul>
    <c:forEach items="${violations}" var="error">
        <li>${error.getMessage()}</li>
    </c:forEach>
</ul>
<a href="/login">Login</a>
</body>
</html>