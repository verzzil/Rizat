<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Files</title>
</head>
<body>
<a href="/files">Upload an image</a>
<c:forEach items="${files}" var="file">
    <img src="${file.getContent()}" style="border: 1px solid #ddd; border-radius: 4px; padding: 5px; height: 150px; width: 150px;">
    <p>${file.getFilename()}</p>
</c:forEach>
</body>
</html>

