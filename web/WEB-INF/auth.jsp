<%--
  Created by IntelliJ IDEA.
  User: rudeigerc
  Date: 2017/7/16
  Time: 22:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <%
        String path = request.getContextPath();
    %>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Bookstore</title>
    <meta name="author" content="Yuchen Cheng">
    <meta name="description" content="A naive web bookstore.">
    <link rel="icon" href="<%=path%>/icon/data_celtic_knot.ico">
    <link type="text/css" rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
    <link type="text/css" rel="stylesheet" href="<%=path%>/css/font-awesome.min.css"/>
    <link type="text/css" rel="stylesheet" href="<%=path%>/css/bookstore.css"/>
</head>
<body>
<%
    if ("error".equals(request.getParameter("login"))) {
        response.getWriter().println("<script>alert('Invalid username or password')</script>");
    }
%>
<div id="particles-js">
    <div class="container container-center" style="width: 500px;">
        <p class="navbar-brand navbar-brand-center" style="font-size: 40px;">Bookstore</p>
        <nav class="nav nav-tabs" id="authTab" role="tablist">
            <a class="nav-item nav-link active" id="login-tab" data-toggle="tab" href="#login" role="tab" aria-controls="login" aria-expanded="true">Log in</a>
            <a class="nav-item nav-link" id="signup-tab" data-toggle="tab" href="#signup" role="tab" aria-controls="signup">Sign up</a>
        </nav>
        <div class="tab-content" id="nav-tabContent">
            <div class="tab-pane fade show active" id="login" role="tabpanel" aria-labelledby="login-tab">
                <form method="post" action="login">
                    <div class="form-group">
                        <label for="login_username" class="col-form-label">Username</label>
                        <input type="text" class="form-control" id="login_username" name="login_username" placeholder="Username">
                    </div>
                    <div class="form-group">
                        <label for="login_password" class="col-form-label">Password</label>
                        <input type="password" class="form-control" id="login_password" name="login_password" placeholder="Password">
                    </div>
                    <button type="submit" class="btn btn-primary">Log in</button>
                </form>
            </div>
            <div class="tab-pane fade" id="signup" role="tabpanel" aria-labelledby="signup-tab">
                <form data-toggle="validator" role="form" method="post" action="signUp">
                    <div class="form-group has-feedback">
                        <label for="username" class="col-form-label">Username</label>
                        <span class="fa form-control-feedback" aria-hidden="true"></span>
                        <input type="text" class="form-control" id="username" name="username" placeholder="username" data-remote="validate" data-error="The username has been used." required>
                        <div class="help-block with-errors text-danger"></div>
                    </div>
                    <div class="form-group has-feedback">
                        <label for="password" class="col-form-label">Password</label>
                        <span class="fa form-control-feedback" aria-hidden="true"></span>
                        <input type="password" data-minlength="6" class="form-control" id="password" name="password" placeholder="password" data-error="Use at least six characters." required>
                        <div class="help-block with-errors text-danger"></div>
                    </div>
                    <div class="form-group has-feedback">
                        <label for="phone" class="col-form-label">Phone</label>
                        <span class="fa form-control-feedback" aria-hidden="true"></span>
                        <input type="text" pattern="^[0-9]{11}$" class="form-control" id="phone" name="phone" placeholder="phone" data-error="Please input the right phone number." required>
                        <div class="help-block with-errors text-danger"></div>
                    </div>
                    <div class="form-group has-feedback">
                        <label for="email" class="col-form-label">E-mail</label>
                        <span class="fa form-control-feedback" aria-hidden="true"></span>
                        <input type="email" class="form-control" id="email" name="email" placeholder="email" data-error="Please input the right E-mail address." required>
                        <div class="help-block with-errors text-danger"></div>
                    </div>
                    <button type="submit" class="btn btn-success">Sign up</button>
                </form>
            </div>
        </div>
        <footer>
            <p>&copy; 2017 Yuchen Cheng</p>
        </footer>
    </div>
</div>

<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="https://cdn.bootcss.com/popper.js/1.9.3/umd/popper.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"></script>
<script type="text/javascript" src="https://cdn.bootcss.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"></script>
<script type="text/javascript" src="https://unpkg.com/vue/dist/vue.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/particles.js/2.0.0/particles.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/validator.js"></script>
<script type="text/javascript">
    particlesJS.load('particles-js', 'json/particles.json', function() {
        console.log('callback - particles.js config loaded');
    });
</script>
</body>
</html>
