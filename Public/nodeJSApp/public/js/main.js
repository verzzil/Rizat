function getTeachers(){
    $.get("http://localhost:8080/teachers", function (response){
        alert(response )
    })
}