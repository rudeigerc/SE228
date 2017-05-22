$(document).ready(function() {
    var table = $('#order').DataTable( {
        "processing": true,
        "serverSide": true,
        "ajax": "./order",
        "columns": [
            { "data": "order_ID" },
            { "data": "username" },
            { "data": "time" },
            { "data": "total" }
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
        $("#order_ID").val("");
        $("#username").val("");
        $("#time").val("");
        $("#total").val("");
        $('#time').removeAttr("readonly");
        $('#username').removeAttr("disabled");
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

    $('#order tbody').on( 'click', 'tr', function () {
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
        $("#username option").remove();
        $("#add").attr("data-target", "#info_modal");
        $.ajax({
            url: "./select_user",
            success: function (data) {
                if (data) {
                    $("#username").append("<option selected='selected'></option>")
                    for (var i = 0; i < data.username.length; i++) {
                        $("#username").append("<option value='" + data.username[i] + "'>" + data.username[i] + "</option>");
                    }
                }
            }
        });
    });

    $('#edit').click( function () {
        var row = get_selected_row();
        $("#edit").attr("data-target", "#info_modal");
        $("#order_ID").val(row.order_ID);
        $("#username").append("<option value='" + row.username + "' selected='selected'>" + row.username + "</option>");
        $("#time").val(row.time);
        $("#total").val(row.total);
        $("#time").attr("readonly", "readonly");
        $("#username").attr("disabled", "disabled");

    });

    $('#delete').click( function () {
        var row = get_selected_row();
        var confirm = window.confirm("WARNING: Irrevocable operation.");
        if (!confirm) return;
        $.ajax({
            url: "./delete_order",
            data: {"order_ID": row.order_ID},
            type: "post",
            success: function (data) {
                location.reload();
            }
        });

    });

    $('#info_submit').click(function () {
        var form_data = $('#order_form').serialize();
        $.ajax({
            url: "./add_order",
            data: form_data,
            type: "post",
            success: function (data) {
                reset_all();
                $('#info_modal').modal('hide');
                location.reload();
            }
        })

    });

    $('#info_cancel').click(reset_all());

    $('#info_cross').click(reset_all());


    function getNowFormatDate() {
        var date = new Date();
        var seperator1 = "-";
        var seperator2 = ":";
        var month = date.getMonth() + 1;
        var strDate = date.getDate();
        var hour = date.getHours();
        var minute = date.getMinutes();
        var second = date.getSeconds();

        if (month >= 1 && month <= 9) month = "0" + month;
        if (strDate >= 0 && strDate <= 9) strDate = "0" + strDate;
        if (hour >= 0 && hour <= 9) hour = "0" + hour;
        if (minute >= 0 && minute <= 9) minute = "0" + minute;
        if (second >= 0 && second <= 9) second = "0" + second;

        var currentDate = date.getFullYear() + seperator1 + month + seperator1 + strDate
            + " " + hour + seperator2 + minute + seperator2 + second;
        return currentDate;
    }

    $('#time').val(getNowFormatDate());
} );