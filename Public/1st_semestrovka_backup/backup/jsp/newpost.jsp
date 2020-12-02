<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <title>New Post</title>
</head>
<body>
<form method="post" action="/posts/new">
    <input name="name" type="text"
           placeholder="Post name">
    <input name="text" type="text"
           placeholder="Post text">
    <input type="submit" value="Post">
</form>
<ul>
    <c:forEach items="${violations}" var="error">
        <li>${error.getMessage()}</li>
    </c:forEach>
</ul>
<a href="/posts">Existing posts</a>
</body>
</html>