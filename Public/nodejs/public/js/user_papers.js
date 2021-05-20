$(document).ready(function () {
    $("#btn").on('click', function () {
        let currentUserId = $("#input_user_id").val();
        if (currentUserId !== "") {
            $("#papers").empty();
            $.get({
                url: "http://localhost:8080/rest/papers/user",
                data: {
                    "userId": currentUserId
                },
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
            });
        }
    });
});