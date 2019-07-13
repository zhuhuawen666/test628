<%--
  Created by IntelliJ IDEA.
  User: 10449
  Date: 2019/6/28
  Time: 16:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="../../js/jquery-3.4.1.js"></script>

</head>
<h2>用户登录</h2>
<body>
<form action="/user/login" method="post">
    用户名：<input type="text" name="username" id="username"><br>
    密码：<input type="password" name="password" id="password"><br>
    <input type="button" value="登录" onclick="abc()">
</form>
<script>
    function abc() {

        var username = $("#username").val();
        var password = $("#password").val();
        $.post("/user/login2",{username:username,password:password},function (d) {
            if (d == "登陆成功"){
                alert(d);
                $("form").submit();

            } else {
                alert(d);
            }
        },"text");

    }
</script>
<a href="/user/st2">注册</a>
</body>
</html>
