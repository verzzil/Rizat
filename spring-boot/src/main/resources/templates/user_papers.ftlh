<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Papers of ${user.fullName}</title>
</head>
<body>
<h1>Посты ${user.fullName}</h1>
<ul>
    <#if papers??>
        <#list papers as paper>
            <li>
                <h3>${paper.title}</h3>
                <p>${paper.description}</p>
            </li>
            <#if isMyPage??>
                <a href="/paper/${paper.id}/remove/${user.id}">Удалить</a>
                <form action="/paper/${paper.id}/edit/${user.id}" method="get">
                    <input type="hidden" name="id" value="${paper.id}">
                    <input type="hidden" name="title" value="${paper.title}">
                    <input type="hidden" name="description" value="${paper.description}">
                    <input type="submit" value="Редактировать">
                </form>
            </#if>
            <hr>
        </#list>
    </#if>
</ul>
</body>
</html>