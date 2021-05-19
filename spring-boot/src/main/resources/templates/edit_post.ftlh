<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Редактировать пост</title>
</head>
<body>
<form action="/paper/${paperId}/edit/${userId}" method="post">
    <input type="text" name="title" value="${paper.title}">
    <input type="text" name="description" value="${paper.description}">
    <input type="hidden" name="id" value="${paper.id}">
    <input type="submit" value="Редактировать">
</form>
</body>
</html>