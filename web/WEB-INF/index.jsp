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
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
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
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/validator.min.js"></script>
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
        if ("error".equals(request.getParameter("signup"))) {
            response.getWriter().println("<script>alert('Sorry, the username has been used')</script>");
        }
        if ("error".equals(request.getParameter("login"))) {
            response.getWriter().println("<script>alert('Invalid username or password')</script>");
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
                <a class="navbar-brand" href="<%=request.getContextPath()%>">Bookstore</a>
            </div>
            <div id="_navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li><a href="index">Home<span class="sr-only">(current)</span></a></li>
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
                        <input type="text" class="form-control" placeholder="Search">
                        <span class="input-group-btn">
                            <button type="button" class="btn btn-default">Submit</button>
                        </span>
                    </div>
                    <div class="btn-group" role="group">
                        <button type="button" class="btn btn-default">
                            <span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>
                            <span class="badge">0</span>
                        </button>
                        <div class="btn-group" role="group">
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
                                <li><a href="<%=request.getContextPath()%>/index"><span class="glyphicon glyphicon-asterisk" aria-hidden="true"></span>
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
                    <li><a href="index">Home<span class="sr-only">(current)</span></a></li>
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
                <form class="navbar-form navbar-right" id="login_form" action="login" method="post">
                    <div class="form-group">
                        <input type="text" placeholder="Username" class="form-control" id="login_username" name="login_username">
                    </div>
                    <div class="form-group">
                        <input type="password" placeholder="Password" class="form-control" id="login_password" name="login_password">
                    </div>
                    <button type="submit" class="btn btn-success" id="sign_in" >Sign in</button>
                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#signup_modal">Sign up</button>
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
        <div class="row">
            <div class="col-md-3">
                <img src="<%=request.getContextPath()%>/image/<%=book.getIsbn()%>.jpg" style="width: 200px; height: 200px;" class="img-rounded">
            </div>
            <div class="col-md-9">
                <h3><%=book.getTitle()%> <small><%=book.getPublishedDate()%></small> </h3>
                <h5><%=book.getAuthor()%></h5>
                <h4>$<%=book.getPrice()%></h4>
                <p class="text-danger"><%=book.getInventory()%> left in stock</p>
                <form id="toCart" role="form">
                    <div class="input-group spinner input-group-sm" data-trigger="spinner" style="width: 90px; ">
                        <input type="text" class="form-control text-center" value="1" data-rule="quantity" max="<%=book.getInventory()%>">
                        <div class="input-group-addon">
                            <a href="javascript:" class="spin-up" data-spin="up"><i class="fa fa-caret-up" aria-hidden="true" style="margin-top: 0"></i></a>
                            <a href="javascript:" class="spin-down" data-spin="down"><i class="fa fa-caret-down" aria-hidden="true" style="margin-top: 0"></i></a>
                        </div>
                    </div>
                    <button class="btn btn-default" role="button" disabled="disabled">
                        <span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>
                        Add to Cart
                    </button>
                </form>

            </div>
        </div>
        <h1></h1>
        <%
            }
        %>

        <footer>
            <p>&copy; 2017 Yuchen Cheng</p>
        </footer>
    </div> <!-- /container -->


    <div class="modal fade" id="signup_modal" tabindex="-1" role="dialog" aria-labelledby="info_title" aria-hidden="true">
        <div class="modal-dialog" style="width:500px;">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true" id="info_cross">&times;</button>
                    <h4 class="modal-title" id="info_title">Create an Account</h4>
                </div>
                <form id="user_form" data-toggle="validator" role="form">
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="username">Username</label>
                            <input type="text" class="form-control" id="username" name="username" required>
                            <span id="username_help" class="help-block">This will be your username.</span>
                        </div>
                        <div class="form-group">
                            <label for="password">Password</label>
                            <input type="password" class="form-control" id="password" name="password" data-minlength="6" required>
                            <span id="password_help" class="help-block">Use at least one lowercase letter, one numeral, and six characters.</span>
                        </div>
                        <div class="form-group">
                            <label for="phone">Phone</label>
                            <input type="text" class="form-control" id="phone" name="phone" required>
                            <span id="phone_help" class="help-block">Only support the phone numbers used in the People's Republic of China.</span>
                        </div>
                        <div class="form-group">
                            <label for="email">E-mail</label>
                            <input type="email" class="form-control" id="email" name="email" data-error="The E-mail address is invalid." required>
                            <span id="email_help" class="help-block with-errors">We promise not to share your email with anyone.</span>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal" id="info_cancel">Cancel</button>
                        <button type="button" class="btn btn-primary" id="info_submit">Sign up</button>
                    </div>
                </form>

            </div>
        </div>
    </div>

</body>


<script>
    $(document).ready(function() {
        $('#info_submit').click(function () {
            var form_data = $('#user_form').serialize();
            $.ajax({
                url: "signUp",
                data: form_data,
                type: "post",
                success: function (data) {
                    $('#signup_modal').modal('hide');
                    location.reload();
                }
            })
        });
    });
</script>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script>window.jQuery || document.write('<script src="docs/assets/js/vendor/jquery.min.js"><\/script>')</script>
<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="<%=request.getContextPath()%>/docs/assets/js/ie10-viewport-bug-workaround.js"></script>


</html>
