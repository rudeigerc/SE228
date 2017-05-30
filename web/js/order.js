$(document).ready(function() {
    var table = $('#order').DataTable( {
        "columns": [
            { "data": "orderId" },
            { "data": "username" },
            { "data": "time" },
            { "data": "total" },
            { "data": "status" }
        ],
        "order": [[0, 'asc']],
        "dom": '<"toolbar">frtip'
    } );


    $("div.toolbar").html(
        '<button type="button" class="btn btn-success" id="add" data-toggle="modal" name="add">Add</button> ' +
        '<button type="button" class="btn btn-warning" id="edit" data-toggle="modal" disabled="disabled" name="edit">Edit</button> ' +
        '<button type="button" class="btn btn-danger" id="delete" disabled="disabled" name="delete">Delete</button> ' +
        '<button type="button" class="btn btn-info" id="detail" disabled="disabled" name="detail" data-toggle="modal" data-target="#orderItem_modal">Detail</button>'
    );

    function reset_all() {
        $("#orderId").val("");
        $("#username").val("");
        $("#time").val("");
        $("#total").val("");
        $("#status").val("");
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
            $('#detail').attr("disabled", "disabled");
            $(this).removeClass('selected');
        }
        else {
            $('#edit').attr("disabled", false);
            $('#delete').attr("disabled", false);
            $('#detail').attr("disabled", false);
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
        $("#edit").attr("data-target", "#info_modal");
        $("#orderId").val(row.orderId);
        $("#username").val(row.username);
        $("#time").val(row.time);
        $("#total").val(row.total);
        $("#time").attr("readonly", "readonly");

    });

    $('#delete').click( function () {
        var row = get_selected_row();
        var confirm = window.confirm("WARNING: Irrevocable operation.");
        if (!confirm) return;
        $.ajax({
            url: "deleteOrder",
            data: {"orderId": row.orderId},
            type: "post",
            success: function (data) {
                location.reload();
            }
        });

    });

    $('#info_submit').click(function () {
        var form_data = $('#order_form').serialize();
        var orderId = form_data.replace(/^orderId=|&.*/g, "");
        if (orderId == "") {
            $.ajax({
                url: "addOrder",
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
                url: "updateOrder",
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

    $('#detail').click( function () {
        var row = get_selected_row();
        document.getElementById('order_header').innerHTML= "Order #" + row.orderId + " User: " + row.username;
        var detail_table = $('#orderDetail').DataTable( {
            "destroy": true,
            "ajax": {
                "url": "orderDetail",
                "type": "POST",
                "data": function ( d ) {
                    return $.extend( {}, d, {
                        "orderId": row.orderId
                    } );
                },
                "dataSrc": function ( json ) {
                    json = eval("("+json+")");
                    return json.data;
                }
            },
            "columns": [
                { "data": "isbn" },
                { "data": "quantity" },
                { "data": "price" }
            ],
            "paging": false,
            "ordering": false,
            "info": false,
            "searching": false,
            "footerCallback": function () {
                var api = this.api();
                var total = api.column(2).data().reduce( function (a, b) {
                        var value = new Decimal(a).plus(new Decimal(b));
                        return value.toString();
                    }, 0 );
                $( api.column(2).footer() ).html("$" + total);
            }
        } );
    });
} );