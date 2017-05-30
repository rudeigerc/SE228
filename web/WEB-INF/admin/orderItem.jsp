<%@ page import="bookstore.model.OrderItem" %>
<%@ page import="java.util.ArrayList" %>
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

    <title>Bookstore Database - Order Item</title>

    <!-- Bootstrap core CSS -->
    <link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/css/dashboard.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/css/datatables.min.css" rel="stylesheet">

    <script type="text/javascript" src="<%=request.getContextPath()%>/js/datatables.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/decimal.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/orderItem.js"></script>

</head>
<body>
    <%
        ArrayList<OrderItem> orderItemList = new ArrayList<>();
        ArrayList<String> isbnList = new ArrayList<>();
        ArrayList<Integer> orderIdList = new ArrayList<>();
        if (request.getAttribute("orderItems") != null) {
            orderItemList = (ArrayList<OrderItem>) request.getAttribute("orderItems");
        }
        if (request.getAttribute("isbns") != null) {
            isbnList = (ArrayList<String>) request.getAttribute("isbns");
        }
        if (request.getAttribute("orderItems") != null) {
            orderIdList = (ArrayList<Integer>) request.getAttribute("orderIds");
        }
    %>
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
                                <li><a href="<%=request.getContextPath()%>/logout"><span class="glyphicon glyphicon-warning-sign" aria-hidden="true"></span>
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
                    <li><a href="admin">
                        <span class="glyphicon glyphicon-screenshot" aria-hidden="true"></span>
                        Overview
                    </a></li>
                    <li><a href="book">
                        <span class="glyphicon glyphicon-book" aria-hidden="true"></span>
                        Book
                    </a></li>
                    <li><a href="user">
                        <span class="glyphicon glyphicon-user" aria-hidden="true"></span>
                        User
                    </a></li>
                    <li><a href="order">
                        <span class="glyphicon glyphicon-list" aria-hidden="true"></span>
                        Order
                    </a></li>
                    <li class="active"><a href="orderItem">
                        <span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span>
                        Order Item<span class="sr-only">(current)</span>
                    </a></li>
                </ul>
            </div>
            <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                <h2 class="sub-header">Order Item</h2>
                <div class="table-responsive">
                    <table class="table table-striped table-hover" id="orderItem">
                        <thead>
                        <tr>
                            <th>Id</th>
                            <th>Order ID</th>
                            <th>ISBN</th>
                            <th>Quantity</th>
                            <th>Price</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%
                            for (OrderItem orderItem : orderItemList) {
                        %>
                        <tr>
                            <td><%=orderItem.getId()%></td>
                            <td><%=orderItem.getOrderId()%></td>
                            <td><%=orderItem.getIsbn()%></td>
                            <td><%=orderItem.getQuantity()%></td>
                            <td><%=orderItem.getPrice()%></td>
                        </tr>
                        <%
                            }
                        %>
                        </tbody>

                    </table>
                </div>
            </div>
        </div>
        <div class="modal fade" id="info_modal" tabindex="-1" role="dialog" aria-labelledby="info_title" aria-hidden="true">
            <div class="modal-dialog" style="width:400px;">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true" id="info_cross">&times;</button>
                        <h4 class="modal-title" id="info_title">Order Item Information</h4>
                    </div>
                    <form id="orderItem_form">
                        <div class="modal-body">
                            <div class="form-group">
                                <input type="hidden" class="form-control" id="id" name="id" required>
                            </div>
                            <div class="form-group">
                                <label for="orderId">Order ID</label>
                                <select class="form-control" id="orderId" name="orderId">
                                    <option selected="selected"></option>
                                    <%
                                        for (Integer orderId : orderIdList) {
                                    %>
                                    <option value="<%=orderId%>"><%=orderId%></option>
                                    <%
                                        }
                                    %>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="isbn">ISBN</label>
                                <select class="form-control" id="isbn" name="isbn">
                                    <option selected="selected"></option>
                                    <%
                                        for (String isbn : isbnList) {
                                    %>
                                    <option value="<%=isbn%>"><%=isbn%></option>
                                    <%
                                        }
                                    %>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="quantity">Quantity</label>
                                <input type="text" class="form-control" id="quantity" name="quantity" required>
                            </div>
                            <div class="form-group">
                                <label for="price">Price</label>
                                <input type="text" class="form-control" id="price" name="price" required>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal" id="info_cancel">Cancel</button>
                            <button type="button" class="btn btn-primary" id="info_submit">Submit</button>
                        </div>
                    </form>

                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script>window.jQuery || document.write('<script src="<%=request.getContextPath()%>/docs/assets/js/vendor/jquery.min.js"><\/script>')</script>
    <script src="<%=request.getContextPath()%>/docs/dist/js/bootstrap.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="<%=request.getContextPath()%>/docs/assets/js/ie10-viewport-bug-workaround.js"></script>
</body>

</html>
