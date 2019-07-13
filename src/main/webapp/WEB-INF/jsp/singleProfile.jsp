<%--
  Created by IntelliJ IDEA.
  User: 10449
  Date: 2019/6/28
  Time: 17:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>这是一个查看页面</h2>
<table>
    <tr>
        <td>编号</td>
        <td>${profile.id}</td>
    </tr>
    <tr>
        <td>姓名</td>
        <td>${profile.name}</td>
    </tr>
    <tr>
        <td>生日</td>
        <td>${profile.birthday}</td>
    </tr>
    <tr>
        <td>性别</td>
        <td>${profile.gender}</td>
    </tr>
    <tr>
        <td>职业</td>
        <td>${profile.career}</td>
    </tr>
    <tr>
        <td>住所</td>
        <td>${profile.address}</td>
    </tr>
    <tr>
        <td>电话</td>
        <td>${profile.mobile}</td>
    </tr>

</table>
<img src="../../upload/${profile.picture}" width="300px" height="300px">
<a href="/user/list">返回首页</a>
</body>
</html>
