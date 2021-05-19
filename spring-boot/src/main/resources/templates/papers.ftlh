<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>All Papers</title>
    <script
            src="https://code.jquery.com/jquery-3.6.0.min.js"
            integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
            crossorigin="anonymous"></script>
</head>
<body>

<input type="text" placeholder="Введите название поста" id="search_input">

<ul id="posts_list">
    <#if papers??>
        <#list papers as paper>
            <li>
                <h3>${paper.title}</h3>
                <p>${paper.description}</p>
                <#if userId?? && userId == paper.authorId>
                    <a href="/paper/${paper.id}/remove/${userId}">Удалить</a>
                    <form action="/paper/${paper.id}/edit/${userId}" method="get">
                        <input type="hidden" name="id" value="${paper.id}">
                        <input type="hidden" name="title" value="${paper.title}">
                        <input type="hidden" name="description" value="${paper.description}">
                        <input type="submit" value="Редактировать">
                    </form>
                </#if>
                <hr>
            </li>
        </#list>
    </#if>
</ul>

<script>
    $(document).ready(function() {
        $("#search_input").on('input', function () {
            $.ajax({
                url: "/papers/get-by-title",
                method: "GET",
                data: {
                    template: $("#search_input").val()
                },
                success: function (data) {
                    $("#posts_list").empty();
                    data.forEach(function (item) {
                        $("#posts_list").append(
                            "<li>" +
                            "<h3>"+item.title+"</h3>" +
                            "<p>"+item.description+"</p>" +
                            "<hr>" +
                            "</li>"
                        );
                    });
                }
            });
        });
    });

</script>
</body>
</html>