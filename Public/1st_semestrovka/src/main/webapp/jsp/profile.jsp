<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <title>Profile</title>
    <style>
        body {
            background-image: url("https://img4.thuthuatphanmem.vn/uploads/2020/07/05/hinh-anh-background-mau-nong-vang-cam-do_034912082.jpg");
            background-size: cover;
            display: grid;
        }

        #user ul {
            list-style: none;
        }

        body a {
            justify-self: center;
            padding: 10px 20px;
            text-decoration: none;
        }

        body a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
    <nav class="my-2 my-md-0 mr-md-3">
        <a class="btn btn-outline-primary" href="/files">Upload an image</a>
        <a class="btn btn-outline-danger" href="/uploaded/files">Uploaded images</a>
        <a class="btn btn-outline-warning" href="/posts/new">Create a post</a>
        <a class="btn btn-outline-success" href="/posts">Created posts</a>
        <a class="btn btn-outline-dark" href="/about">About</a>
    </nav>
</div>
<h1>${lastName} ${firstName}</h1>
<ul>
    <li>Email: ${email}</li>
</ul>
</body>
</html>
