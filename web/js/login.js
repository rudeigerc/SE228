$(document).ready(function() {
    $('#info_submit').click(function () {
        var form_data = $('#user_form').serialize();
        $.ajax({
            url: "./add_user",
            data: form_data,
            type: "post",
            success: function (data) {
                $('#signup_modal').modal('hide');
                location.reload();
            }
        })

    });

    $("body").keydown(function() {
        if (event.keyCode == "13") {
            $('#sign_in').click();
        }
    });
});