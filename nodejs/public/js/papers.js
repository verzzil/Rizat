$(document).ready(function () {
    $.get({
        url: "http://localhost:8080/rest/papers",
        success: function (data) {
            data.forEach(function (item) {
                $("#papers").append(
                    `<li>
<h3>${item.title}</h3>
<span>
description: ${item.description}
</span>
</li> <hr>`
                );
            });

        }
    })
});