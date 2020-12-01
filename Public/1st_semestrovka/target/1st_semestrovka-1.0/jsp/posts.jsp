<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Posts</title>
</head>
<body>
<a href="/posts/new">Create new post</a>
<c:forEach items="${posts}" var="post">
    <h2>${post.getName()}</h2>
    <p>Posted by ${post.getUserName()} at ${post.getCreationDate()}</p>
    <p>${post.getText()}</p>
</c:forEach>
</body>
</html>