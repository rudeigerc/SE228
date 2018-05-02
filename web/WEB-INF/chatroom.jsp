<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Chatroom</title>
    <link type="text/css" rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/font-awesome.min.css"/>

    <script type="text/javascript" src="https://vuejs.org/js/vue.js"></script>
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/vue-resource@1.3.4"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/chatroom.js"></script>
</head>
<body>
    <nav class="navbar navbar-default" role="navigation">
        <div class="navbar-offcanvas navbar-offcanvas-touch navbar-offcanvas-fade navbar-offcanvas-right" id="right-sidebar">
            <div class="pre-scrollable" id="chatbox" style="max-height: 70%;">
                <div class="panel panel-primary" style="width: 100%; height: 70%;">
                    <div class="panel-body" id="chatContent"></div>
                </div>
            </div>
            <div class="dropdown-divider" style="margin-bottom: 0;"></div>
            <div>
                <label for="msg"></label>
                <textarea rows="4" class="form-control" id="msg"></textarea>
                <input type="hidden" class="btn btn-outline-primary" value="发送" id="sendMsg">
            </div>
        </div>
    </nav>
</body>
</html>
