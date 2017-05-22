function format ( book ) {
    return '<blockquote><small>' +
        '<B>Title:</B> ' + book.title + '<br>' +
        '<B>Author:</B> ' + book.author + '<br>' +
        '<B>ISBN-13:</B> ' + book.ISBN + '<br>' +
        '<B>Publisher:</B> ' + book.publisher + '<br>' +
        '<B>Published Date:</B> ' + book.published_date + '<br>' +
        '<B>Language:</B> ' + book.language + '<br>' +
        '<B>Classification:</B> ' + book.classification + '<br>' +
        '<B>Price:</B> $' + book.price + '<br>' +
        '<B>Inventory:</B> ' + book.inventory + '</small></blockquote>'
}

$(document).ready(function() {
    var table = $('#book').DataTable( {
        "processing": true,
        "serverSide": true,
        "ajax": "./book",
        "columns": [

            { "data": "title" },
            { "data": "author" },
            { "data": "ISBN" },
            { "data": "publisher" },
            { "data": "price" },
            { "data": "inventory" }
        ],
        "order": [[1, 'asc']],
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
        var tr = $(this).closest('tr');
        var row = table.row( tr );
        var idx = $.inArray( tr.attr('id'), detailRows );

        if ( row.child.isShown() ) {
            tr.removeClass( 'details' );
            row.child.hide();

            // Remove from the 'open' array
            detailRows.splice( idx, 1 );
        }
        else {
            tr.addClass( 'details' );
            row.child( format( row.data() ) ).show();

            // Add to the 'open' array
            if ( idx === -1 ) {
                detailRows.push(tr.attr('id'));
            }
        }

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
        $("#ISBN").val("");
        $("#publisher").val("");
        $("#published_date").val("");
        $("#language").val("");
        $("#classification").val("");
        $("#price").val("");
        $("#inventory").val("");
        $('#ISBN').removeAttr("readonly");
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
        $("#add").attr("data-target", "#info_modal");
    });

    $('#edit').click( function () {
        var row = get_selected_row();
        $("#edit").attr("data-target", "#info_modal");
        $("#title").val(row.title);
        $("#author").val(row.author);
        $("#ISBN").val(row.ISBN);
        $("#publisher").val(row.publisher);
        $("#published_date").val(row.published_date);
        $("#language").val(row.language);
        $("#classification").val(row.classification);
        $("#price").val(row.price);
        $("#inventory").val(row.inventory);
        $('#ISBN').attr("readonly","readonly")

    });

    $('#delete').click( function () {
        var row = get_selected_row();
        var confirm = window.confirm("WARNING: Irrevocable operation.");
        if (!confirm) return;
        $.ajax({
            url: "./delete_book",
            data: {"ISBN": row.ISBN},
            type: "post",
            success: function (data) {
                location.reload();
            }
        });

    });

    $('#info_submit').click(function () {
        var form_data = $('#book_form').serialize();
        $.ajax({
            url: "./add_book",
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


} );
