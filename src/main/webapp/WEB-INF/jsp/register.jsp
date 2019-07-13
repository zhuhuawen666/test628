<%--
  Created by IntelliJ IDEA.
  User: 10449
  Date: 2019/6/28
  Time: 16:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
<script src="../../js/jquery-3.4.1.js"></script>
    <script>
        $(function(){
            $(".t1").change(function () {
                var username = $(".t1").val()
                if(username != ""){
                    $.post("/user/aaa",{username:username},function (d) {
                        var f = d.resp;
                        if(f == "false"){
                            $(".p1").html("用户已存在");
                        }else{
                            $(".p1").html("");
                        }
                    },"json");
                }else{
                    $(".p1").html("用户名不能为空");
                }

            });
            $(".t2").change(function () {
                var password = $(".t2").val();
                if(password == ""){
                    $(".p2").html("密码不能为空");
                }else{
                    $(".p2").html("");
                }

            })
        });

    </script>
</head>
<h2>用户注册</h2>
<body>
<form action="/user/register" method="post">
    用户名：<input type="text" name="username" class="t1"><span class="p1"></span><br>
    密码：<input type="password" name="password" class="t2"><span class="p2"></span><br>
    <input type="button" value="注册" onclick="ss()">
</form>
<script>
    function ss() {
        var aa = $(".t1").val();
        var bb = $(".t2").val();
        var cc = $("p1").val();
        var dd = $("p2").val();
        if (aa !="" && bb !=""){
            if (cc =="" && dd ==""){
                $("form").submit()
            } else {
                alert("注册失败")

            }
        } else{
            alert("注册失败")

        }

    }
</script>
<a href="/user/st1">登录</a>

</body>
</html>
