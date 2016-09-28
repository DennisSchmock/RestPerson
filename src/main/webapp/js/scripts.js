/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(function () {
    $("#addeditform").hide();

    //Rendering form
    $("#clickme").on("click", function () {
        renderList();
        $("#addeditform").slideUp(250);

    });

    //Adding a person
    $("#addperson").on("click", function () {
        $("#addeditform").slideToggle(250);
        $("#btnedit").hide();
    });

    $("btnedit").click(function () {
        $("#persontable").hide();
        var id = $(this).data("personid");
        $('#personlist').text(id);
    });

    //Adding a
    $("#addeditform").submit(function (event) {
        event.preventDefault();
        var formData = {
            'firstName': $("#fName").val(),
            'lastName': $("#lName").val(),
            'phone': $("#phone").val()
        };
        $.ajax({
            type: 'POST',
            url: 'api/person',
            data: JSON.stringify(formData),
            dataType: 'json',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        })
                .done(function (data) {
                     resetForm($("#addeditform"));
                    renderList();
                });

    });



    function renderList() {
        $.ajax({
            type: 'GET',
            url: "api/person",
            dataType: "json", // data type of response
            success: function (data) {
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


    }
    
    function resetForm($form) {
    $form.find('input:text, input:password, input:file, select, textarea').val('');
    $form.find('input:radio, input:checkbox')
         .removeAttr('checked').removeAttr('selected');
}
});


