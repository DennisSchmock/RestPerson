/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(function () {
    var rootURL = "http://localhost:8085/Rest1/api/quote/";
    loadQuote();


    $("#btnQuote").click(function () {
      loadQuote();
    });

    


    function loadQuote() {
        console.log('load quote');
        $.ajax({
            type: 'GET',
            url: rootURL + "random",
            dataType: "json", // data type of response
            success: setQuote
        });
    }
    
    function newQuote(){
        $.ajax({
            type: 'POST',
            url: rootURL,
            dataType: formToJSON(),
            success: addQuote
        });
    }

    function setQuote(data) {

        var list = data == null ? [] : (data instanceof Array ? data : [data]);

        $.each(list, function (index, quote) {
            $('#quote').text(quote.quote);
        });

    }
    



});
