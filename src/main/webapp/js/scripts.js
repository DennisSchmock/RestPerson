/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(function () {
    $("#addform").hide();
    $("#editform").hide();

    $("#personlist").on("click", "a.edit", function (data) {
        event.preventDefault();

        $("#addform").slideUp(250);
        var id = $(this).data("personid");
        $.get("api/person/" + id, function (data) {
            $("#id").val(data.id);
            $("#fNameEdit").val(data.firstName);
            $("#lNameEdit").val(data.lastName);
            $("#phoneEdit").val(data.phone);
        });

        $("#editform").slideDown(250);
    });
    //Delete person
    $("#personlist").on("click", "a.delete", function (data) {
        event.preventDefault();
        var id = $(this).data("personid");
        $.ajax({
            type: 'DELETE',
            url: 'api/person/' + id,
            dataType: 'json'
        }).done(function () {
            renderList();
        });

    });


    //Rendering form
    $("#clickme").on("click", function () {
        event.preventDefault();
        renderList();
        $("#addform").slideUp(250);
        $("#editform").slideUp(250);


    });

    //Show add person
    $("#addperson").on("click", function () {
        event.preventDefault();
        $("#addform").slideToggle(250);
        $("#editform").slideUp(250);

    });



    //Adding a new person
    $("#addform").submit(function (event) {
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
                    resetForm($("#addform"));
                    renderList();
                });

    });

    //Edit person
    $("#editform").submit(function (event) {
        event.preventDefault();

        var formData = {
            'id': $("#id").val(),
            'firstName': $("#fNameEdit").val(),
            'lastName': $("#lNameEdit").val(),
            'phone': $("#phoneEdit").val()
        };
        $.ajax({
            type: 'PUT',
            url: 'api/person',
            data: JSON.stringify(formData),
            dataType: 'json',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        })
                .done(function () {
                    resetForm($("#editform"));
                    $("#editform").slideUp(250);
                    renderList();
                });
    });


    //For rendering the personlist
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
                            + "<a href='#' class='edit' data-personid='" + person.id
                            + "'>edit </a>/<a href='#' class='delete' data-personid='" + person.id + "'> delete</a></td></tr>");
                });
            }
        });


    }

    //For clearing a form
    function resetForm($form) {
        $form.find('input:text, input:password, input:file, select, textarea').val('');
        $form.find('input:radio, input:checkbox')
                .removeAttr('checked').removeAttr('selected');
    }


});


