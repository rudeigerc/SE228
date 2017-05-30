$(document).ready(function() {
    var table = $('#orderItem').DataTable( {
        "columns": [
            { "data": "id"},
            { "data": "orderId" },
            { "data": "isbn" },
            { "data": "quantity" },
            { "data": "price" }
        ],
        "order": [[1, 'asc']],
        "columnDefs": [
            { "visible": false, "targets": 1 }
        ],
        "drawCallback": function ( settings ) {
            var api = this.api();
            var rows = api.rows( {page:'current'} ).nodes();
            var last = null;
            api.column(1, {page:'current'} ).data().each( function ( group, i ) {
                if ( last !== group ) {
                    $(rows).eq( i ).before(
                        '<tr class="group"><td colspan="5"><strong>'+"Order #"+group+'</strong></td></tr>'
                    );

                    last = group;
                }
            } );
        },
        "dom": '<"toolbar">frtip'
    } );

    $("div.toolbar").html(
        '<button type="button" class="btn btn-success" id="add" data-toggle="modal" name="add">Add</button> ' +
        '<button type="button" class="btn btn-warning" id="edit" data-toggle="modal" disabled="disabled" name="edit">Edit</button> ' +
        '<button type="button" class="btn btn-danger" id="delete" disabled="disabled" name="delete">Delete</button>'
    );



    function reset_all() {
        $('#orderId').val("");
        $('#isbn').val("");
        $('#quantity').val("");
        $('#price').val("");
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


    $('#orderItem tbody').on( 'click', 'tr.odd, tr.even', function () {
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
        $("#edit").attr("data-target", "#info_modal");
        $("#orderId").val(row.orderId);
        $("#isbn").val(row.isbn);
        $("#quantity").val(row.quantity);
        $("#price").val(row.price);
    });

    $('#delete').click( function () {
        var row = get_selected_row();
        var confirm = window.confirm("WARNING: Irrevocable operation.");
        if (!confirm) return;
        $.ajax({
            url: "deleteOrderItem",
            data: {"id": row.id},
            dataType : "text",
            type: "post",
            success: function (data) {
                location.reload();
            }
        });

    });

    $('#info_submit').click(function () {
        var form_data = $('#orderItem_form').serialize();
        var id = form_data.replace(/^id=|&.*/g, "");
        if (id == "") {
            $.ajax({
                url: "addOrderItem",
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
                url: "updateOrderItem",
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