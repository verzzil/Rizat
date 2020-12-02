<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <title>New Post</title>
    <link rel="stylesheet" href="../css/newpost.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
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
<a class= "btn btn-outline-warning" href="/posts">Existing posts</a>
</body>
</html>