<%@ page import="java.util.Collection" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="org.springframework.security.core.userdetails.UserDetails" %>
<%@ page import="org.springframework.security.core.authority.SimpleGrantedAuthority" %>
<%@ page import="bookstore.model.Book" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="A naive web bookstore.">
    <meta name="author" content="Yuchen Cheng">
    <link rel="icon" href="<%=request.getContextPath()%>/icon/data_celtic_knot.ico">

    <title>Bookstore</title>

    <link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/docs/examples/jumbotron/jumbotron.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/docs/examples/carousel/carousel.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/css/font-awesome.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/css/bootstrap-spinner.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/css/datatables.min.css" rel="stylesheet">

    <script type="text/javascript" src="<%=request.getContextPath()%>/js/datatables.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/decimal.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.spinner.js"></script>
</head>

<body>

    <%
        ArrayList<Book> bookList = new ArrayList<>();
        ArrayList<String> categoryList = new ArrayList<>();
        if (request.getAttribute("books") != null) {
            bookList = (ArrayList<Book>) request.getAttribute("books");
        }
        if (request.getAttribute("categories") != null) {
            categoryList = (ArrayList<String>) request.getAttribute("categories");
        }
        if (request.getUserPrincipal() != null) {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username = userDetails.getUsername();
            Collection<?> authorities= userDetails.getAuthorities();

    %>
    <nav class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#_navbar" aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="<%=request.getContextPath()%>/index">Bookstore</a>
            </div>
            <div id="_navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li><a href="<%=request.getContextPath()%>/index">Home<span class="sr-only">(current)</span></a></li>
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Category <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="index">All</a></li>
                            <%
                                for (String category : categoryList) {
                            %>
                            <li><a href="index?category=<%=category%>"><%=category%></a></li>
                            <%
                                }
                            %>
                        </ul>
                    </li>
                </ul>
                <ul class="nav navbar-nav navbar-right navbar-form">
                    <div class="input-group">
                        <input type="text" class="form-control" placeholder="Search" id="keyword" name="keyword">
                        <span class="input-group-btn">
                            <button type="button" class="btn btn-default" id="search">Submit</button>
                        </span>
                    </div>
                    <div class="btn-group" role="group">
                        <button type="button" class="btn btn-default" id="btn-cart" data-toggle="modal" data-target="#cart_modal">
                            <span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>
                            <span class="badge" id="cart_badge">{{ count }}</span>
                        </button>
                        <div class="btn-group" role="group" id="_login">
                            <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="glyphicon glyphicon-user" aria-hidden="true"></span>
                                <%=username%>
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu">
                                <li><a href="<%=request.getContextPath()%>/index"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>
                                    Home
                                </a></li>
                                <% if (authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) { %>
                                <li><a href="<%=request.getContextPath()%>/admin/admin"><span class="glyphicon glyphicon-wrench" aria-hidden="true"></span>
                                    Manage
                                </a></li>
                                <% } else if (authorities.contains(new SimpleGrantedAuthority("ROLE_USER"))) { %>
                                <li><a href="<%=request.getContextPath()%>/settings"><span class="glyphicon glyphicon-asterisk" aria-hidden="true"></span>
                                    Settings
                                </a></li>
                                <% } %>
                                <li role="separator" class="divider"></li>
                                <li><a href="<%=request.getContextPath()%>/logout"><span class="glyphicon glyphicon-warning-sign" aria-hidden="true"></span>
                                    Log out
                                </a></li>
                            </ul>
                        </div>
                    </div>
                </ul>
            </div>
        </div>
    </nav>
    <% } else { %>
    <nav class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">Bookstore</a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li><a href="<%=request.getContextPath()%>/index">Home<span class="sr-only">(current)</span></a></li>
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Category <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="index">All</a></li>
                            <%
                                for (String category : categoryList) {
                            %>
                            <li><a href="index?category=<%=category%>"><%=category%></a></li>
                            <%
                                }
                            %>
                        </ul>
                    </li>
                </ul>
                <form class="navbar-form navbar-right">
                    <button type="button" class="btn btn-default" id="btn-cart" data-toggle="modal" data-target="#cart_modal">
                        <span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>
                        <span class="badge" id="cart_badge">{{ count }}</span>
                    </button>
                    <a type="button" class="btn btn-success" href="<%=request.getContextPath()%>/auth">Log in</a>
                    <a type="button" class="btn btn-primary" href="<%=request.getContextPath()%>/auth">Sign up</a>
                </form>
            </div>
        </div>
    </nav>
    <%
        }
    %>

    <div id="myCarousel" class="carousel slide" data-ride="carousel">
        <!-- Indicators -->
        <ol class="carousel-indicators">
            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
            <li data-target="#myCarousel" data-slide-to="1"></li>
            <li data-target="#myCarousel" data-slide-to="2"></li>
        </ol>
        <div class="carousel-inner" role="listbox">
            <div class="item active">
                <img class="first-slide" src="<%=request.getContextPath()%>/image/slide_bg1.png" alt="First slide">
                <div class="container">
                    <div class="carousel-caption">
                        <h1>Welcome to the bookstore!</h1>
                        <p>This is a naive web bookstore.</p>
                        <p><a class="btn btn-lg btn-primary" href="https://github.com/rudeigerc/SE228" role="button">View the Github project</a></p>
                    </div>
                </div>
            </div>
            <div class="item">
                <img class="second-slide" src="<%=request.getContextPath()%>/image/slide_bg2.jpg" alt="Second slide">
                <div class="container">
                    <div class="carousel-caption">
                    </div>
                </div>
            </div>
            <div class="item">
                <img class="third-slide" src="<%=request.getContextPath()%>/image/slide_bg3.jpg" alt="Third slide">
                <div class="container">
                    <div class="carousel-caption">
                    </div>
                </div>
            </div>
        </div>
        <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
            <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
            <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div><!-- /.carousel -->


    <div class="container">
        <!-- Example row of columns -->
        <%
            for (Book book : bookList) {
        %>
        <div class="row" style="margin-bottom: 10px; margin-top: 10px;" id="B_<%=book.getIsbn()%>">
            <div class="col-md-3">
                <img src="<%=request.getContextPath()%>/image/<%=book.getIsbn()%>.jpg" style="width: 200px; height: 200px;" class="img-rounded">
            </div>
            <div class="col-md-9">
                <h3><%=book.getTitle()%> <small><%=book.getPublishedDate()%></small> </h3>
                <h5><%=book.getAuthor()%></h5>
                <h4>$<%=book.getPrice()%></h4>
                <p class="text-danger"><%=book.getInventory()%> left in stock</p>
                <div>
                    <label for="quantity_<%=book.getIsbn()%>"></label>
                    <input id="quantity_<%=book.getIsbn()%>" type="number" class="form-control text-center quantity" value="1" min="0" max="<%=book.getInventory()%>" style="width: 60px; display: inline;">
                    <button class="btn btn-default btn-add-to-cart" role="button">
                        <span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>&nbsp;Add to Cart
                    </button>
                    <button class="btn btn-primary btn-detail" role="button" data-toggle="modal" data-target="#detail_modal">
                        <span class="glyphicon glyphicon-book" aria-hidden="true"></span>&nbsp;Detail
                    </button>
                </div>
            </div>
        </div>
        <%
            }
        %>

        <footer>
            <p>&copy; 2017 Yuchen Cheng</p>
        </footer>
    </div> <!-- /container -->


    <div class="modal fade" id="detail_modal" tabindex="-1" role="dialog" aria-labelledby="detail_title" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content" v-if="detail !== null">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="detail_title">
                        <span class="glyphicon glyphicon-book" aria-hidden="true"></span>&nbsp;{{ detail.title }}
                    </h4>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="col-sm-4">
                            <img src="" :src="'<%=request.getContextPath()%>/image/' + detail.isbn + '.jpg'" style="width: 200px; height: 200px;" class="img-rounded">
                        </div>
                        <div class="col-sm-8">
                            <strong>Author: </strong>{{ detail.author }}<br>
                            <strong>ISBN: </strong>{{ detail.isbn }}<br>
                            <strong>Publisher: </strong>{{ detail.publisher }}<br>
                            <strong>Published Date: </strong>{{ detail.publishedDate }}<br>
                            <strong>Category: </strong>{{ detail.category }}<br>
                            <strong>Price: </strong>{{ detail.price }}<br>
                            <strong>Inventory: </strong>{{ detail.inventory }}<br>
                            <!--
                            <div style="margin-top: 10px;">
                                <label :for="'_quantity_' + detail.isbn"></label>
                                <input :id="'_quantity_' + detail.isbn" type="number" class="form-control text-center" :value="1" min="0" :max="detail.inventory" style="width: 60px; display: inline;">
                                <button class="btn btn-default" role="button">
                                    <span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>&nbsp;Add to Cart
                                </button>
                            </div>
                            -->
                        </div>
                    </div>
                    <div class="row" style="margin: 10px;">
                        <div v-if="detail.description !== undefined">
                            <blockquote>{{ detail.description }}</blockquote>
                        </div>
                        <div v-else="">
                            <blockquote>No description.</blockquote>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="cart_modal" tabindex="-1" role="dialog" aria-labelledby="cart_title" aria-hidden="true">
        <div class="modal-dialog" style="width: 800px;">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="cart_title">
                        <span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>&nbsp;Cart
                    </h4>
                </div>
                <div class="modal-body" v-if="book.length !== 0">
                    <div class="table-responsive">
                        <table class="table table-striped table-hover" id="cart">
                            <thead>
                            <tr>
                                <th></th>
                                <th>Title</th>
                                <th>ISBN</th>
                                <th>Price</th>
                                <th>Amount</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr v-for="_book in book" :id="'C_' + _book.isbn">
                                <td>
                                    <label for="order_check"></label>
                                    <input type="checkbox" id="order_check" checked>
                                </td>
                                <td>{{ _book.title }}</td>
                                <td>{{ _book.isbn }}</td>
                                <td>{{ _book.price }}</td>
                                <td>{{ _book.amount }}</td>
                                <td>
                                    <button class="btn btn-danger btn-cart-delete" @click="deleteFromCart">
                                        <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                                    </button>
                                </td>
                            </tr>
                            </tbody>
                            <tfoot>
                            <tr>
                                <th colspan="4" style="text-align:right">Total:</th>
                                <th>{{ total }}</th>
                            </tr>
                            </tfoot>
                        </table>
                    </div>
                </div>
                <div class="modal-body" v-else="">
                    <div class="alert alert-info" role="alert">No book in the cart.</div>
                </div>
                <div class="modal-footer" v-if="book.length !== 0">
                    <button class="btn btn-default" id="proceed" @click="proceed">
                        <span class="glyphicon glyphicon-check" aria-hidden="true"></span>&nbsp;Proceed to checkout
                    </button>
                </div>
            </div>
        </div>
    </div>
</body>


<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script>window.jQuery || document.write('<script src="docs/assets/js/vendor/jquery.min.js"><\/script>')</script>
<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/docs/assets/js/ie10-viewport-bug-workaround.js"></script>
<script type="text/javascript" src="https://unpkg.com/vue/dist/vue.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/vue-resource@1.3.4"></script>
<script type="text/javascript">

    $(document).ready(function() {
        $('#search').click(function() {
            var keyword = $('#keyword').val();
            window.location.href = "index?keyword=" + keyword;
            $('#keyword').val(keyword);
        });

        $(document).keydown(function(event){
            if( event.keyCode === 13 ) {
                $("#search").trigger("click");
            }
        });

        $.ajax({
            url: "cart",
            type: "get",
            success: function (data) {
                cart.book = [];
                json = JSON.parse(data);
                for (var _json in json) {
                    cart.book.push(JSON.parse(json[_json]));
                }
                badge.count = cart.book.length;
            }
        });

        var detail = new Vue({
            el: '#detail_modal',
            data: {
                detail: null
            }
        });

        var cart_table = $('#cart').DataTable( {
            "columns": [
                { "data": "check" },
                { "data": "title" },
                { "data": "isbn" },
                { "data": "price" },
                { "data": "amount" },
                { "data": "delete" }
            ],
            "paging": false,
            "info": false,
            "ordering": false,
            "searching": false
        } );

        var cart = new Vue({
            el: '#cart_modal',
            data: {
                book: []
            },
            computed: {
                total: function() {
                    var total = new Decimal(0);
                    for (var _book in this.book) {
                        var amount = this.book[_book].amount;
                        var price = this.book[_book].price;
                        var tmp = new Decimal(price).times(amount);
                        total = Decimal.add(total, tmp);
                    }
                    return total.toString();
                }
            },
            methods: {
                deleteFromCart: function(e) {
                    var isbn = e.currentTarget.parentElement.parentElement.id.replace('C_', '');
                    $.ajax({
                        url: "deleteFromCart",
                        type: "post",
                        data: {
                            isbn: isbn
                        },
                        success: function() {
                            alert("The book was deleted from the cart successfully.");
                            location.reload();
                        }
                    })
                },
                proceed: function() {
                    if ($('#_login')[0] === undefined) {
                        window.location.href = "auth";
                        return;
                    }
                    var json = [];
                    var total = new Decimal(0);
                    $("#cart").find('tbody tr').each(function() {
                        var checked = $(this).children('td:eq(0)')[0].lastChild.checked;
                        if (!checked) return;

                        var object = {};
                        var isbn = $(this).children('td:eq(2)').text();
                        var price = $(this).children('td:eq(3)').text();
                        var amount = parseInt($(this).children('td:eq(4)').text());
                        object.isbn = isbn;
                        object.price = price;
                        object.amount = amount;
                        var tmp = new Decimal(price).times(amount);
                        total = Decimal.add(total, tmp);
                        json.push(object);
                    });
                    var str = JSON.stringify(json);
                    $.ajax({
                        url: "createOrder",
                        type: "post",
                        data: {
                            json: str,
                            total: total.toString()
                        },
                        success: function() {
                            alert("The order was created.");
                            location.reload();
                        },
                        error: function() {
                            alert("Please select the books to be ordered.")
                        }
                    })
                }
            }
        });

        var badge = new Vue({
            el: '#cart_badge',
            data: {
                count: 0
            }
        });

        $('.btn-detail').click(function() {
            var isbn = this.parentElement.parentElement.parentElement.id.replace('B_', '');
            $.ajax({
                url: "bookDetail",
                type: "get",
                data: {
                    isbn: isbn
                },
                success: function (data) {
                    detail.detail = JSON.parse(data).book;
                }
            })
        });

        $('.btn-add-to-cart').click(function() {
            var isbn = this.parentElement.parentElement.parentElement.id.replace('B_', '');
            var amount = $(this.previousElementSibling).val();
            $.ajax({
                url: "addToCart",
                type: "post",
                data: {
                    isbn: isbn,
                    amount: amount
                },
                success: function() {
                    alert("The book was added to the cart successfully.");
                    location.reload();
                }
            })
        });

        $('.quantity').bind('input propertychange', function() {
            var inventory = $(this.parentElement.parentElement.children[3]).text().split(' ')[0];
            if (String($(this).val() | 0) !== $(this).val() || $(this).val() <= "0" || parseInt($(this).val()) > parseInt(inventory)) {
                $(this.nextElementSibling).attr('disabled', 'disabled');
            } else {
                $(this.nextElementSibling).removeAttr('disabled');
            }
        });


    });

</script>

</html>
