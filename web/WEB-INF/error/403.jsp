<%--
  Created by IntelliJ IDEA.
  User: rudeigerc
  Date: 2017/7/11
  Time: 16:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <%
            String path = request.getContextPath();
        %>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Bookstore - 403</title>
        <meta name="author" content="Yuchen Cheng">
        <meta name="description" content="">
        <link rel="icon" href="<%=path%>/icon/data_celtic_knot.ico">
        <link type="text/css" rel="stylesheet" href="<%=path%>/css/bookstore.css"/>
    </head>
    <body>
        <div id="particles-js">
            <div class="container container-center nbs">
                <h1 style="text-align: center; color: #D0021B;">403 FORBIDDEN</h1>
            </div>
        </div>
        <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
        <script type="text/javascript" src="https://cdn.jsdelivr.net/particles.js/2.0.0/particles.min.js"></script>
        <script type="text/javascript">
            particlesJS.load('particles-js', 'json/particles-error.json', function() {
                console.log('callback - particles.js config loaded');
            });
        </script>
    </body>
</html>
