<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="Yuchen Cheng">
    <link rel="icon" href="<%=request.getContextPath()%>/icon/data_celtic_knot.ico">

    <title>Bookstore Database</title>

    <!-- Bootstrap core CSS -->
    <link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/css/dashboard.css" rel="stylesheet">
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

            </div>
        </div>

    </div>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script>window.jQuery || document.write('<script src="<%=request.getContextPath()%>/docs/assets/js/vendor/jquery.min.js"><\/script>')</script>
    <script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="<%=request.getContextPath()%>/docs/assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>


