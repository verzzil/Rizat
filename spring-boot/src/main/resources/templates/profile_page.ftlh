<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Profile</title>
</head>
<body>
<h1>${user.getFullName()}</h1>

<#if user.email??>
    Email: ${user.email}<br>
</#if>
<#if user.age??>
    Возраст: ${user.age}<br>
</#if>
<#if user.phone??>
    Телефон: ${user.phone}<br>
</#if>
<#if user.sex??>
    Пол: ${user.sex}<br>
</#if>

<a href="/files/upload">Загрузить файл</a>
<br>
<a href="/papers/user/${user.id}">Посты этого пользователя</a>
</body>
</html>