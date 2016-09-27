/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(function () {



    $("#clickme").on("click", function () {
        $("#persontable").show();
        $.ajax({
            type: 'GET',
            url: "api/person",
            dataType: "json", // data type of response
            success: renderList
        });
    });

    $("#addperson").on("click", function () {
        $("#persontable").hide();
    });

    $("a.btnedit").click(function () {
        $("#persontable").hide();
        var id = $(this).data("personid");
        $('#personlist').text(id);
    });



    function renderList(data) {
        var list = data == null ? [] : (data instanceof Array ? data : [data]);
        $('#personlist').text("");
        $.each(list, function (index, person) {
            $('#personlist').append("<tr><td>" + person.id + "</td><td>" + person.firstName
                    + "</td><td> " + person.lastName + "</td><td>"
                    + person.phone + "</td><td>"
                    + "<a href='#' class='btnedit' data-personid='" + person.id + "'>edit </a>/<a href='#'> delete</a></td></tr>");
        });

    }
});


