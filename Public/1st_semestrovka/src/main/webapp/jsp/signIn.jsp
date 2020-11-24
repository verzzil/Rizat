<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form method="post" action="/signIn">
    <input name="email" type="text"
           placeholder="Enter email">
    <input name="password" type="password"
           placeholder="Enter password">
    <input type="submit" value="Sign In">
</form>
<a href="/signUp">Registration</a>
</body>
</html>