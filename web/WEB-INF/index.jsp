<%@ taglib prefix="c" uri="/struts-tags" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="A naive web bookstore.">
    <meta name="author" content="Yuchen Cheng">
    <link rel="icon" href="icon/data_celtic_knot.ico">

    <title>Bookstore</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="docs/assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="docs/examples/jumbotron/jumbotron.css" rel="stylesheet">
    <link href="docs/examples/carousel/carousel.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/bs/jq-2.2.4/dt-1.10.13/b-1.2.4/se-1.2.0/datatables.min.css"/>

    <script type="text/javascript" src="https://cdn.datatables.net/v/bs/jq-2.2.4/dt-1.10.13/b-1.2.4/se-1.2.0/datatables.min.js"></script>

    <!-- Validator, for Bootstrap 3 -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/1000hz-bootstrap-validator/0.11.9/validator.min.js"></script>
</head>

<body>


<%
    HttpSession _session = request.getSession(false);
    String isLogin = (String) _session.getAttribute("isLogin");
    PrintWriter _out = response.getWriter();
    if ("true".equals(isLogin)) {
        String username = (String) _session.getAttribute("username");
        _out.println("    <nav class=\"navbar navbar-inverse navbar-fixed-top\">\n" +
                "        <div class=\"container\" id=\"container\">\n" +
                "            <div class=\"navbar-header\">\n" +
                "                <button type=\"button\" class=\"navbar-toggle collapsed\" data-toggle=\"collapse\" data-target=\"#navbar\" aria-expanded=\"false\" aria-controls=\"navbar\">\n" +
                "                    <span class=\"sr-only\">Toggle navigation</span>\n" +
                "                    <span class=\"icon-bar\"></span>\n" +
                "                    <span class=\"icon-bar\"></span>\n" +
                "                    <span class=\"icon-bar\"></span>\n" +
                "                </button>\n" +
                "                <a class=\"navbar-brand\" href=\"#\">Bookstore</a>\n" +
                "            </div>\n" +
                "            <div id=\"navbar\" class=\"navbar-collapse collapse\">" +
                "                <ul class=\"nav navbar-nav navbar-right navbar-form\">\n" +
                "                    <li class=\"btn-group\">\n" +
                "                        <div class=\"btn-group\">\n" +
                "                            <button type=\"button\" class=\"btn btn-primary dropdown-toggle\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">\n" +
                username +
                "                                <span class=\"caret\"></span>\n" +
                "                            </button>\n" +
                "                            <ul class=\"dropdown-menu\">\n" +
                "                                <li><a href=\"index.jsp\"><span class=\"glyphicon glyphicon-home\"></span>\n" +
                "                                    Home\n" +
                "                                </a></li>\n" +
                "                                <li role=\"separator\" class=\"divider\"></li>\n" +
                "                                <li><a href=\"logout\"><span class=\"glyphicon glyphicon-warning-sign\"></span>\n" +
                "                                    Log out\n" +
                "                                </a></li>\n" +
                "                            </ul>\n" +
                "                        </div>\n" +
                "                    </li>\n" +
                "                </ul>\n" +
                "            </div>\n" +
                "        </nav>");

    } else {
        _out.println("    <nav class=\"navbar navbar-inverse navbar-fixed-top\">\n" +
                "        <div class=\"container\" id=\"container\">\n" +
                "            <div class=\"navbar-header\">\n" +
                "                <button type=\"button\" class=\"navbar-toggle collapsed\" data-toggle=\"collapse\" data-target=\"#navbar\" aria-expanded=\"false\" aria-controls=\"navbar\">\n" +
                "                    <span class=\"sr-only\">Toggle navigation</span>\n" +
                "                    <span class=\"icon-bar\"></span>\n" +
                "                    <span class=\"icon-bar\"></span>\n" +
                "                    <span class=\"icon-bar\"></span>\n" +
                "                </button>\n" +
                "                <a class=\"navbar-brand\" href=\"#\">Bookstore</a>\n" +
                "            </div>\n" +
                "            <div id=\"navbar\" class=\"navbar-collapse collapse\">" +
                "                    <form class=\"navbar-form navbar-right\" id=\"login_form\" action=\"login\" method=\"post\">\n" +
                "                        <div class=\"form-group\">\n" +
                "                            <input type=\"text\" placeholder=\"Username\" class=\"form-control\" id=\"login_username\" name=\"login_username\">\n" +
                "                        </div>\n" +
                "                        <div class=\"form-group\">\n" +
                "                            <input type=\"password\" placeholder=\"Password\" class=\"form-control\" id=\"login_password\" name=\"login_password\">\n" +
                "                        </div>\n" +
                "                        <input type=\"hidden\" name=\"${_csrf.parameterName}\" value=\"${_csrf.token}\"/>\n" +
                "                        <button type=\"submit\" class=\"btn btn-success\" id=\"sign_in\" >Sign in</button>\n" +
                "                        <button type=\"button\" class=\"btn btn-primary\" data-toggle=\"modal\" data-target=\"#signup_modal\">Sign up</button>\n" +
                "                    </form>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "        </nav>");
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
                <img class="first-slide" src="image/slide_bg1.png" alt="First slide">
                <div class="container">
                    <div class="carousel-caption">
                        <h1>Welcome to the bookstore!</h1>
                        <p>This is a naive web bookstore.</p>
                        <p><a class="btn btn-lg btn-primary" href="https://github.com/rudeigerc/SE228" role="button">View the Github project</a></p>
                    </div>
                </div>
            </div>
            <div class="item">
                <img class="second-slide" src="image/slide_bg2.jpg" alt="Second slide">
                <div class="container">
                    <div class="carousel-caption">
                    </div>
                </div>
            </div>
            <div class="item">
                <img class="third-slide" src="image/slide_bg3.jpg" alt="Third slide">
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
        <div class="row">
            <div class="col-md-3">
                <img src="https://images-na.ssl-images-amazon.com/images/I/51JZNVJ-1PL._AC_US436_QL65_.jpg" style="width: 200px; height: 200px;" class="img-rounded">
            </div>
            <div class="col-md-9">
                <h3>Natural Language Processing with Python <small>2017-03-25</small> </h3>

                <h5>Steven Bird, Ewan Klein, Edward Loper</h5>
                <h4>$35.99</h4>
                <p class="text-danger">0 left in stock</p>
                <p><a class="btn btn-default disabled" href="#" role="button">View details &raquo;</a></p>
            </div>
        </div>
        <h1></h1>
        <div class="row">
            <div class="col-md-3">
                <img src="https://images-na.ssl-images-amazon.com/images/I/516rrbL-RWL._AC_US436_FMwebp_QL65_.jpg" style="width: 200px; height: 200px;" class="img-rounded">
            </div>
            <div class="col-md-9">
                <h3>Database System Concepts 6th Edition <small>2010-01-27</small> </h3>

                <h5>Abraham Silberschatz, Henry Korth, S. Sudarshan</h5>
                <h4>$206.21</h4>
                <p class="text-danger">14 left in stock</p>
                <p><a class="btn btn-default disabled" href="#" role="button">View details &raquo;</a></p>
            </div>
        </div>
        <h1></h1>
        <div class="row">
            <div class="col-md-3">
                <img src="https://images-na.ssl-images-amazon.com/images/I/51nrRHBSPHL._AC_US436_FMwebp_QL65_.jpg" style="width: 200px; height: 200px;" class="img-rounded">
            </div>
            <div class="col-md-9">
                <h3>Thinking in Java (4th Edition) <small>2006-02-20</small> </h3>

                <h5>Bruce Eckel</h5>
                <h4>$45.68</h4>
                <p class="text-danger">3 left in stock</p>
                <p><a class="btn btn-default disabled" href="#" role="button">View details &raquo;</a></p>
            </div>
        </div>
        <h1></h1>

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
                                <input type="hidden" class="form-control" id="uid" name="uid" required>
                            </div>
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
        <footer>
            <p>&copy; 2017 Yuchen Cheng</p>
        </footer>
    </div> <!-- /container -->

</body>




<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script>window.jQuery || document.write('<script src="docs/assets/js/vendor/jquery.min.js"><\/script>')</script>
<script src="js/bootstrap.min.js"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="docs/assets/js/ie10-viewport-bug-workaround.js"></script>

</html>
