$(document).ready(function() {

    var websocket;
    var avatar = $('#user_avatar').attr('src');
    /* chatting record */
    var lastChat = -1;


    /* WebSocket */
    if ('WebSocket' in window) {
        websocket = new WebSocket("ws://localhost:8080/bookstore/chat");
    } else if ('MozWebSocket' in window) {
        websocket = new MozWebSocket("ws://localhost:8080/bookstore/chat");
    } else {
        websocket = new SockJS("http://localhost:8080/bookstore/chat/sockjs");
    }

    websocket.onopen = function (event) {
        console.log("WebSocket: connected");
        //console.log(event);
    };

    websocket.onclose = function (event) {
        console.log("WebSocket: disconnected");
        //console.log(event);
    };

    websocket.onmessage = function (msg) {
        var data = JSON.parse(msg.data);
        addContent("#4A90E2", "/bookstore/" +data.avatar, data.fromName, data.date, data.text);
        $('#chat_icon').attr('style', 'color: #4A90E2');
        $('#chatbox').scrollTop($('#chatbox')[0].scrollHeight);
    };

    /* Date */
    Date.prototype.Format = function (fmt) {
        var o = {
            "M+": this.getMonth() + 1,
            "d+": this.getDate(),
            "h+": this.getHours(),
            "m+": this.getMinutes(),
            "s+": this.getSeconds(),
            "q+": Math.floor((this.getMonth() + 3) / 3),
            "S": this.getMilliseconds()
        };
        if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        for (var k in o)
            if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        return fmt;
    };

    $('#showChat').click(function() {
        $('#chat_icon').removeAttr('style');
        var chat, chatContent;
        if(document.getElementById("chatContent").innerHTML === "") {
            $.ajax({
                url: "/bookstore/getGroupChat",
                processData: true,
                dataType: "text",
                data: {
                    lastChat : lastChat
                },
                success: function (data) {
                    var json = JSON.parse(data);
                    lastChat = json.lastChat;
                    var currentUser = json.currentUser;
                    var chatList = JSON.parse(json.result);
                    for (var i = 0; i < chatList.length; i++) {
                        if(chatList[i].uid === currentUser){
                            prependContent("#00cc7d", "/bookstore/" +chatList[i].avatar, chatList[i].username, chatList[i].datetime, chatList[i].content);
                        } else {
                            prependContent("#4A90E2", "/bookstore/" +chatList[i].avatar, chatList[i].username, chatList[i].datetime, chatList[i].content);
                        }
                    }
                    if(lastChat !== -2) {
                        $("#chatContent").prepend("<div style='text-align: center;'><button type='button' class='btn btn-link more-record' id='moreChat'>更多记录</button></div>");
                    } else {
                        $("#chatContent").prepend("<div class='no-more-record'>没有更多记录</div>");
                    }
                    $('#chatbox').scrollTop($('#chatbox')[0].scrollHeight);
                }
            })
        }
    });

    $("#chatContent").on("click","#moreChat",function(){
        $("#moreChat").remove();
        $.ajax({
            url: "/bookstore/getGroupChat",
            processData: true,
            dataType: "text",
            data: {
                lastChat : lastChat
            },
            success: function (data) {
                var json = JSON.parse(data);
                lastChat = json.lastChat;
                var currentUser = json.currentUser;
                var chatList = JSON.parse(json.result);
                var originScrollHeight = $('#chatbox')[0].scrollHeight;
                for (var i = 0; i < chatList.length; i++) {
                    if(chatList[i].uid === currentUser){
                        prependContent("#00cc7d", "/bookstore/" +chatList[i].avatar, chatList[i].username, chatList[i].datetime, chatList[i].content);
                    } else {
                        prependContent("#4A90E2", "/bookstore/" +chatList[i].avatar, chatList[i].username, chatList[i].datetime, chatList[i].content);
                    }
                }
                $('#chatbox').scrollTop($('#chatbox')[0].scrollHeight - originScrollHeight);
                if(lastChat !== -2) {
                    $("#chatContent").prepend("<div style='text-align: center;'><button type='button' class='btn btn-link more-record' id='moreChat'>更多记录</button></div>");
                } else {
                    $("#chatContent").prepend("<div class='no-more-record'>没有更多记录</div>");
                }
            }
        })
    });

    function addContent(colorCode, avatar, name, date, text) {
        $("#chatContent").append("<div style='margin-bottom: 10px;'>" +
            "<label style='color:" + colorCode + "'>" +
            "<div class='media'>" +
            "<img class='d-flex mr-3 img-50px rounded' src='" + avatar + "'>" +
            "<div class='media-body'>" +
            name + "<br>" + "<small style='color: lightgrey'>" + date + "</small>" +
            "</div>" +
            "</div>" +
            "</label>" +
            "<div>" + text.replace(/\n/g, '<br>') + "</div>" +
            "</div>");
    }

    function prependContent(colorCode, avatar, name, date, text) {
        $("#chatContent").prepend("<div style='margin-bottom: 10px;'>" +
            "<label style='color:" + colorCode + "'>" +
            "<div class='media'>" +
            "<img class='d-flex mr-3 img-50px rounded' src='" + avatar + "'>" +
            "<div class='media-body'>" +
            name + "<br>" + "<small style='color: lightgrey'>" + date + "</small>" +
            "</div>" +
            "</div>" +
            "</label>" +
            "<div>" + text.replace(/\n/g, '<br>') + "</div>" +
            "</div>");
    }

    $('#sendMsg').click(function() {
        //var notebookId = $('.notebook').attr("id");
        var text = $("#msg").val();
        var re = /\n/g;
        var check = text.replace(re, "");
        if (check === "") {
            $("#msg").val("");
            return;
        } else {
            $.ajax({
                url: "/bookstore/sendMsg",
                processData: true,
                dataType: "text",
                type: "post",
                data: {
                    text: text
                },
                success: function (data) {
                    var json = JSON.parse(data);
                    if (json.result === "success") {
                        addContent("#00cc7d", avatar, json.sender, new Date().Format("yyyy-MM-dd hh:mm:ss"), text);
                        $("#msg").val("");
                        $('#chatbox').scrollTop($('#chatbox')[0].scrollHeight);
                    } else {
                        alert("消息发送失败，请稍后再试。");
                    }
                }
            });
        }
    });

    /* keydown */
    $(document).keydown(function(event){
        if (event.shiftKey && event.keyCode === 13) {
            return;
        }

        if( event.keyCode === 13 ) {
            $("#sendMsg").trigger("click");
        }
    });

    $('#clearMsg').click(function(){
        $("#chatContent").empty()
    });
});