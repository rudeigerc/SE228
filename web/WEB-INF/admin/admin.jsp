<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Yuchen Cheng">
    <link rel="icon" href="<%=request.getContextPath()%>/icon/data_celtic_knot.ico">
    <title>Bookstore Database</title>
    <link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/css/dashboard.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/dt/dt-1.10.15/se-1.2.2/datatables.min.css"/>
</head>

<body>
    <nav class="navbar navbar-inverse navbar-fixed-top">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">Bookstore Database</a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav navbar-right navbar-form">
                    <li class="btn-group">
                        <div class="btn-group">
                            <button type="button" class="btn btn-danger">
                                <span class="glyphicon glyphicon-globe" aria-hidden="true"></span>
                                Administrator
                            </button>
                            <button type="button" class="btn btn-danger dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu">
                                <li><a href="<%=request.getContextPath()%>/index"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>
                                    Home
                                </a></li>
                                <li role="separator" class="divider"></li>
                                <li><a href="<%=request.getContextPath()%>/logout" type="post"><span class="glyphicon glyphicon-warning-sign" aria-hidden="true"></span>
                                    Log out
                                </a></li>
                            </ul>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <div class="container-fluid">
        <div class="row">
            <div class="col-sm-3 col-md-2 sidebar">
                <ul class="nav nav-sidebar">
                    <li class="active"><a href="admin">
                        <span class="glyphicon glyphicon-screenshot" aria-hidden="true"></span>
                        Overview<span class="sr-only">(current)</span>
                    </a></li>
                    <li><a href="book">
                        <span class="glyphicon glyphicon-book" aria-hidden="true"></span>
                        Book
                    </a></li>
                    <li><a href="user">
                        <span class="glyphicon glyphicon-user" aria-hidden="true"></span>
                        User</a>
                    </li>
                    <li><a href="order">
                        <span class="glyphicon glyphicon-list" aria-hidden="true"></span>
                        Order
                    </a></li>
                    <li><a href="orderItem">
                        <span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span>
                        Order Item
                    </a></li>
                </ul>
            </div>
            <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                <div class="alert alert-danger" role="alert">
                    <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                    <span class="sr-only">WARNING:</span>
                    <strong>WARNING: </strong>This is the DATABASE of the site. Only the system administrator can do the operation.
                </div>
                <div class="table-responsive">
                    <div class="row" style="margin-left: 0;">
                        <div style="display: inline; margin-right: 10px;">
                            <label for="from">From:</label>
                            <input type="Date" id="from" placeholder="yyyy-MM-dd">
                        </div>
                        <div style="display: inline;">
                            <label for="to">To:</label>
                            <input type="Date" id="to" placeholder="yyyy-MM-dd">
                        </div>
                    </div>
                    <table class="table table-striped table-hover" id="stat">
                        <thead>
                            <tr>
                                <th>Username</th>
                                <th>Time</th>
                                <th>Title</th>
                                <th>Category</th>
                                <th>Amount</th>
                                <th>Price</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tfoot>
                            <tr>
                                <th>Username</th>
                                <th></th>
                                <th>Title</th>
                                <th>Category</th>
                                <th></th>
                                <th></th>
                                <th></th>
                            </tr>
                        </tfoot>
                        <tbody>
                            <tr>
                            </tr>
                        </tbody>
                    </table>
                </div>

            </div>
        </div>

    </div>


    <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/decimal.min.js"></script>
    <script type="text/javascript" src="https://unpkg.com/vue/dist/vue.js"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/vue-resource@1.3.4"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            $.fn.dataTable.ext.search.push(
                function( settings, data, dataIndex ) {
                    var from = $('#from').val();
                    var to = $('#to').val();
                    var date = data[1].split(' ')[0];
                    if ((from === "" && to === "") || (from === "" && date <= to ) || (from <= date && to === "" ) || (from <= date && date <= to ) ) {
                        return true;
                    } else {
                        return false;
                    }
                }
            );

            var stat = $('#stat').DataTable({
                "destroy": true,
                "ajax": {
                    "url": "stat",
                    "type": "get",
                    "dataSrc": function (data) {
                        var json = JSON.parse(data);
                        $.each(json, function (i, it) {
                            it[6] = new Decimal(it[4]).times(new Decimal(it[5])).toString();
                        });
                        return json;
                    }
                },
                "columns": [
                    { "data": 0 },
                    { "data": 1 },
                    { "data": 2 },
                    { "data": 3 },
                    { "data": 4 },
                    { "data": 5 },
                    { "data": 6 }
                ],
                "columnDefs": [
                    { "visible": false, "targets": 6 }
                ],
                "initComplete": function () {
                    this.api().columns().every( function (i) {
                        if (i === 1 || i === 4 || i === 5) {
                            return;
                        }
                        var column = this;
                        var select = $('<select><option value=""></option></select>')
                            .appendTo( $(column.footer()).empty() )
                            .on( 'change', function () {
                                var val = $.fn.dataTable.util.escapeRegex(
                                    $(this).val()
                                );
                                column
                                    .search( val ? '^'+val+'$' : '', true, false )
                                    .draw();
                            } );
                        column.data().unique().sort().each( function ( d, j ) {
                            select.append( '<option value="'+d+'">'+d+'</option>' )
                        } );
                    } );
                },
                "footerCallback": function() {
                    var api = this.api();
                    var total = api.column(6).data().reduce(function(a, b) {
                        var value = Decimal.add(new Decimal(a), new Decimal(b));
                        return value.toString();
                    }, new Decimal(0));

                    var pageTotal = api.column(6, { page: 'current'}).data().reduce(function(a, b) {
                        var value = Decimal.add(new Decimal(a), new Decimal(b));
                        return value.toString();
                    }, new Decimal(0));

                    $( api.column( 5 ).footer() ).html(
                        '$ '+ pageTotal + ' ($ '+ total +' total)'
                    );
                }
            });

            $('#from, #to').keyup( function() {
                stat.draw();
            } );
        });
    </script>
</body>
</html>


