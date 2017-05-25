$(document).ready(function() {
    var table = $('#user').DataTable( {
        "columns": [
            { "data": "uid"},
            { "data": "username" },
            { "data": "password" },
            { "data": "phone" },
            { "data": "email" },
            { "data": "role"}
        ],
        "order": [[0, 'asc']],
        "dom": '<"toolbar">frtip'
    } );

    $("div.toolbar").html(
        '<button type="button" class="btn btn-success" id="add" data-toggle="modal" name="add">Add</button> ' +
        '<button type="button" class="btn btn-warning" id="edit" data-toggle="modal" disabled="disabled" name="edit">Edit</button> ' +
        '<button type="button" class="btn btn-danger" id="delete" disabled="disabled" name="delete">Delete</button>'
    );



    function reset_all() {
        $('#uid').val("");
        $('#username').val("");
        $('#password').val("");
        $('#phone').val("");
        $('#email').val("");
        $('#role').val("");
        $('#uid').removeAttr("readonly");
        $('#username').removeAttr("readonly");
    }


    function get_selected_row() {
        var nTrs = table.rows().nodes();
        for(var i = 0; i < nTrs.length; i++) {
            if($(nTrs[i]).hasClass('selected')) {
                var row = table.rows().data(nTrs[i]);
                return row[i];
            }
        }
    }


    $('#user tbody').on( 'click', 'tr', function () {
        if ( $(this).hasClass('selected') ) {
            $('#edit').attr("disabled", "disabled");
            $('#delete').attr("disabled", "disabled");
            $(this).removeClass('selected');
        }
        else {
            $('#edit').attr("disabled", false);
            $('#delete').attr("disabled", false);
            table.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
    } );

    $('#add').click( function () {
        reset_all();
        $("#add").attr("data-target", "#info_modal");
    });

    $('#edit').click( function () {
        var row = get_selected_row();
        if (row.uid == 1 || row.uid == 2) {
            alert("Permission denied.");
            $('#edit').removeAttr("data-target");
            return;
        }
        $("#edit").attr("data-target", "#info_modal");
        $("#uid").val(row.uid);
        $("#username").val(row.username);
        $("#password").val(row.password);
        $("#phone").val(row.phone);
        $("#email").val(row.email);
        $("#role").val(row.role);
        $('#uid').attr("readonly","readonly");
        $('#username').attr("readonly","readonly");

    });

    $('#delete').click( function () {
        var row = get_selected_row();
        if (row.uid == 1 || row.uid == 2) {
            alert("Permission denied.");
            return;
        }
        var confirm = window.confirm("WARNING: Irrevocable operation.");
        if (!confirm) return;
        $.ajax({
            url: "deleteUser",
            data: {"uid": row.uid},
            dataType : "text",
            type: "post",
            success: function (data) {
                location.reload();
            }
        });

    });

    $('#info_submit').click(function () {
        var form_data = $('#user_form').serialize();
        var uid = form_data.replace(/^uid=|&.*/g, "");
        if (uid == "") {
            $.ajax({
                url: "addUser",
                data: form_data,
                type: "post",
                success: function (data) {
                    reset_all();
                    $('#info_modal').modal('hide');
                    location.reload();
                }
            })
        }

        else {
            $.ajax({
                url: "updateUser",
                data: form_data,
                type: "post",
                success: function (data) {
                    reset_all();
                    $('#info_modal').modal('hide');
                    location.reload();
                }
            })
        }


    });


    $('#info_cancel').click(reset_all());

    $('#info_cross').click(reset_all());


} );