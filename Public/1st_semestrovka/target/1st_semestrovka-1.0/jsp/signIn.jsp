<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <title>Login</title>
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
<form method="post" action="/signIn">
    <input name="email" type="text"
           placeholder="Enter email">
    <input name="password" type="password"
           placeholder="Enter password">
    <input type="submit" value="Sign In">
</form>
<a class="btn btn-outline-warning" href="/signUp">Registration</a>
</body>
</html>