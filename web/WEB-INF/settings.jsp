<%--
  Created by IntelliJ IDEA.
  User: rudeigerc
  Date: 2017/7/16
  Time: 21:44
  To change this template use File | Settings | File Templates.
--%>

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
    <link href="https://cdn.bootcss.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/css/font-awesome.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/css/datatables.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/css/bookstore.css" rel="stylesheet">
</head>

<body>
    <nav class="navbar navbar-toggleable-md navbar-inverse bg-inverse fixed-top">
        <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="navbar-brand">Settings</div>
        <div class="collapse navbar-collapse" id="navbarsExampleDefault">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="<%=request.getContextPath()%>/index">Home <span class="sr-only">(current)</span></a>
                </li>
            </ul>
        </div>
    </nav>
    <div class="container container-center">
        <div class="card" id="settings">
            <div class="card-block">
                <nav class="nav nav-tabs" id="settingsTab" role="tablist">
                    <a class="nav-item nav-link active" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-expanded="true">Profile</a>
                    <a class="nav-item nav-link" id="account-tab" data-toggle="tab" href="#account" role="tab" aria-controls="profile" aria-expanded="true">Account</a>
                </nav>
                <div class="tab-content" id="settings-tabContent">
                    <div class="tab-pane fade show active" id="profile" role="tabpanel" aria-labelledby="profile-tab">
                        <div class="row">
                            <div class="col-md-6">
                                <form role="form" style="width: 450px;">
                                    <div class="form-group">
                                        <input type="hidden" class="form-control" id="userId">
                                    </div>
                                    <div class="form-group">
                                        <h6><label for="username" class="form-control-label">Username</label></h6>
                                        <input type="text" class="form-control" id="username" readonly="readonly">
                                    </div>
                                    <div class="form-group">
                                        <h6><label for="email" class="form-control-label">E-mail</label></h6>
                                        <input type="email" class="form-control" id="email" name="email">
                                    </div>
                                    <div class="form-group">
                                        <h6><label for="phone" class="form-control-label">Phone</label></h6>
                                        <input type="text" class="form-control" id="phone" name="phone">
                                    </div>
                                    <div class="dropdown-divider"></div>
                                    <div class="form-group">
                                        <h6><label for="name" class="form-control-label">Name</label></h6>
                                        <input type="text" class="form-control" id="name" name="name">
                                    </div>
                                    <div class="form-group">
                                        <h6><label for="address" class="form-control-label">Address</label></h6>
                                        <input type="text" class="form-control" id="address" name="address">
                                    </div>
                                    <button type="button" class="btn btn-warning" id="profile_submit">Edit</button>
                                </form>
                            </div>
                            <div class="col-md-4" style="margin-top: 12px;">
                                <form role="form" action="uploadAvatar" enctype="multipart/form-data" method="post">
                                    <h6>Avatar</h6>
                                    <img src="" id="avatar" class="img-150px">
                                    <div class="btn-group" role="group">
                                        <button type="button" class="btn btn-secondary file-chooser-label" id="file_upload_label" style="font-size: 14px;">Upload new picture
                                            <input type="file" class="form-control-file file-chooser" id="file" name="file">
                                        </button>
                                        <button type="submit" class="btn btn-secondary btn-file-upload" disabled="disabled" id="file_upload">Submit</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="tab-pane fade" id="account" role="tabpanel" aria-labelledby="account-tab">
                        <!-- modify password -->
                        <h5 class="card-title" style="margin-top: 12px;">
                            <i class="fa fa-unlock-alt" aria-hidden="true"></i>&nbsp;Update Password
                        </h5>
                        <div class="dropdown-divider"></div>
                        <form data-toggle="validator" role="form" style="width: 300px;" id="password_modification">
                            <div class="form-group">
                                <h6>
                                    <label for="old_password" class="form-control-label">Old Password</label>
                                </h6>
                                <input type="password" class="form-control" id="old_password" name="originalRawPassword" required>
                            </div>
                            <div class="form-group has-feedback">
                                <h6>
                                    <label for="new_password" class="form-control-label">New Password</label>
                                    <span class="fa form-control-feedback" aria-hidden="true"></span>
                                </h6>
                                <input type="password" data-minlength="6" class="form-control" id="new_password" name="newRawPassword" data-error="Use at least six characters." required>
                                <div class="help-block with-errors text-danger"></div>
                            </div>
                            <div class="form-group has-feedback">
                                <h6>
                                    <label for="new_password_confirm" class="form-control-label">Confirm new password</label>
                                    <span class="fa form-control-feedback" aria-hidden="true"></span>
                                </h6>
                                <input type="password" class="form-control" id="new_password_confirm" data-error="" data-match="#new_password" data-match-error="Password doesn't match the confirmation." required>
                                <div class="help-block with-errors text-danger"></div>
                            </div>
                            <button type="button" class="btn btn-warning" id="modify_password_submit">Update password</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <footer>
            <p>&copy; 2017 Yuchen Cheng</p>
        </footer>
    </div>

    <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="https://cdn.bootcss.com/popper.js/1.9.3/umd/popper.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"></script>
    <script type="text/javascript" src="https://cdn.bootcss.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/datatables.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/decimal.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/validator.js"></script>
    <script type="text/javascript" src="https://unpkg.com/vue/dist/vue.js"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/vue-resource@1.3.4"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            $.ajax({
                url: "detail",
                type: "get",
                success: function(data) {
                    var json = JSON.parse(data);
                    var username = json.username;
                    var name = json.userInfo.name;
                    var address = json.userInfo.address;
                    var phone = json.phone;
                    var email = json.email;
                    var avatar = json.userInfo.avatar;
                    $('#username').val(username);
                    $('#name').val(name);
                    $('#address').val(address);
                    $('#phone').val(phone);
                    $('#email').val(email);
                    $('#avatar').attr('src', "<%=request.getContextPath()%>" + avatar);
                }
            });

            $('#profile_submit').click(function() {
               $.ajax({
                   url: "updateUserInfo",
                   type: "post",
                   data: {
                       email: $('#email').val(),
                       phone: $('#phone').val(),
                       name: $('#name').val(),
                       address: $('#address').val()
                   },
                   success: function () {
                       alert("Your profile was updated.");
                       location.reload();
                   }
               })
            });

            $('#modify_password_submit').click(function() {
                var old_password = $('#old_password').val();
                var new_password = $('#new_password').val();
                var new_password_confirm = $('#new_password_confirm').val();
                if (old_password === "") {
                    alert("Please input old password.");
                    return;
                }
                if (old_password === new_password) {
                    alert("New password is the same as the old one.");
                    return;
                }
                if (new_password !== new_password_confirm) {
                    alert("Password doesn't match the confirmation.");
                    return;
                }
                if (new_password.length < 6) {
                    alert("Use at least six characters.");
                    return;
                }
                $.ajax({
                    url: "updatePassword",
                    dataType: "text",
                    data: {
                        originalRawPassword: old_password,
                        newRawPassword: new_password
                    },
                    type: "post",
                    success: function() {
                        window.location.href = "/logout";
                    },
                    error: function () {
                        alert("Old password was wrong, please input again.");
                    }
                });
            });

            document.getElementById("file").onchange = function() {
                $('#file_upload').removeAttr('disabled');
                var path = $('#file').val();
                var $input = document.getElementById("file");
                var $label = $('#file_upload_label');
                var file_name = path.split("\\")[2];
                if (file_name.length > 12) {
                    file_name = file_name.substr(0, 12) + "...";
                }
                $label[0].innerText = file_name;
                $label.append($input);
            };
        });
    </script>
</body>
</html>
