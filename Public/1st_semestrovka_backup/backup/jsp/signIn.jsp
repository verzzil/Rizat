<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <title>Login</title>
</head>
<body>
<form class="btn btn-group-vertical btn-outline-danger btn-sm" method="post" action="/signIn">
    <input name="email" type="text"
           placeholder="Enter email">
    <input name="password" type="password"
           placeholder="Enter password">
    <p></p>
    <input type="submit" class="btn btn-danger btn-sm" value="Sign In">
</form>
<p></p>
<a class="btn btn-danger btn-sm" href="/signUp">Registration</a>
</body>
</html>