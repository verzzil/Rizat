<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Files</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <style>
        body {
            background-image: url("http://localhost:8080/images?id=17");
            background-size: cover;
            display: grid;
        }

        #upload {
            text-align: center;
            font-size: 1.5em;
            padding: 20px 0;
        }

        .photo {
            display: grid;
            justify-items: center;
            padding: 10px 0;
            transition: all .3s;
        }
        .photo:hover {
            background: darkred;
        }
        .photo:not(:last-child) {
            border-bottom: 1px dashed #000;
        }

        .photo img {
            width: 300px;
            height: 300px;
            object-fit: cover;
        }
    </style>
</head>
<body>
<a class="btn btn-outline-warning" href="/files" id="upload">Upload an image</a>
<c:forEach items="${files}" var="file">
<div class="photo">
    <img src="${file.getContent()}">
    <p>${file.getFilename()}</p>
</div>
</c:forEach>
</body>
</html>

