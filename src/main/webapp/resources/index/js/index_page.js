/**
 * Created by denis on 07.02.16.
 */

function subscribe() {
    //alert($('#subscribe_email').val());
    $.ajax({
        url: '/subscribe',
        type: 'POST',
        data: {email: $('#subscribe_email').val()},
        beforeSend: function()
        {
        //    $('#subscribe_button').toggleClass('active');
        },
        success: function (response) {
            alert(response)
           // $('#subscribe_button #subscribe_span').toggleClass('active');
        },
        error: function () {
            alert("Sorry, something went wrong")
            //$('#subscribe_button #subscribe_span').toggleClass('active');
        }
    })
}
$(function(){
    $('a, button').click(function() {
        $(this).toggleClass('active');
    });
});