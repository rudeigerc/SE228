$(document).ready(function() {
    var table = $('#book').DataTable( {
        "columns": [
            { "data": "title" },
            { "data": "author" },
            { "data": "isbn" },
            { "data": "publisher" },
            { "data": "publishedDate" },
            { "data": "category" },
            { "data": "price" },
            { "data": "inventory" }
        ],
        "order": [[0, 'asc']],
        "dom": '<"toolbar">frtip'

    } );

    $("div.toolbar").html(
        '<button type="button" class="btn btn-success" id="add" data-toggle="modal" name="add">Add</button> ' +
        '<button type="button" class="btn btn-warning" id="edit" data-toggle="modal" disabled="disabled" name="edit">Edit</button> ' +
        '<button type="button" class="btn btn-danger" id="delete" disabled="disabled" name="delete">Delete</button>'
    );


    // Array to track the ids of the details displayed rows
    var detailRows = [];

    $('#book tbody').on( 'click', 'tr', function () {

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


    function reset_all() {

        $("#title").val("");
        $("#author").val("");
        $("#isbn").val("");
        $("#publisher").val("");
        $("#publishedDate").val("");
        $("#category").val("");
        $("#price").val("");
        $("#inventory").val("");
        $('#isbn').removeAttr("readonly");
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

    $('#add').click( function () {
        reset_all();
        $('#isAdd').attr("value", "true");
        $("#add").attr("data-target", "#info_modal");
    });

    $('#edit').click( function () {
        var row = get_selected_row();
        $("#edit").attr("data-target", "#info_modal");
        $("#title").val(row.title);
        $("#author").val(row.author);
        $("#isbn").val(row.isbn);
        $("#publisher").val(row.publisher);
        $("#publishedDate").val(row.publishedDate);
        $("#category").val(row.category);
        $("#price").val(row.price);
        $("#inventory").val(row.inventory);
        $('#isbn').attr("readonly","readonly")
        $('#isAdd').attr("value", "false")

    });

    $('#delete').click( function () {
        var row = get_selected_row();
        var confirm = window.confirm("WARNING: Irrevocable operation.");
        if (!confirm) return;
        $.ajax({
            url: "deleteBook",
            data: {"isbn": row.isbn},
            dataType : "text",
            type: "post",
            success: function (data) {
                location.reload();
            }
        });

    });

    $('#info_submit').click(function () {
        var form_data = $('#book_form').serialize();
        var isAdd = form_data.replace(/^.*isAdd=/g, "");
        form_data = form_data.replace(/isAdd=.*$/g, "");
        if (isAdd == "true") {
            $.ajax({
                url: "addBook",
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
                url: "updateBook",
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
