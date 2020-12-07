<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <style>
        body {
            background-image: url("https://img4.thuthuatphanmem.vn/uploads/2020/07/05/hinh-anh-background-mau-nong-vang-cam-do_034912082.jpg");
            background-size: cover;
            display: grid;
            justify-items: center;
            padding: 10px;
        }

        body form {
            display: grid;
            width: 60%;
        }

        body form input {
            padding: 10px 20px;
        }

        body form input[type="submit"] {
            margin-top: 10px;
            border-radius: 10px;
            cursor: pointer;
            outline: none;
        }
        body form input[type="submit"]:hover  {
            background:yellow;
        }
    </style>
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
<a class="btn btn-outline-warning" href="/signIn">Login</a>
</body>
</html>