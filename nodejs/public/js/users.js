$(document).ready(function () {
    $.get({
        url: "http://localhost:8080/rest/users",
        success: function (data) {
            data.forEach(function (item) {
                $("#users").append(
                    `<li>
<h3>${item.fullName}</h3>
<span>
email: ${item.email} <br>
phone: ${item.phone} <br>
sex: ${item.sex}
</span>
</li> <hr>`
                );
            });

        }
    })
});