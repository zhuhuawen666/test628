<%--
  Created by IntelliJ IDEA.
  User: 10449
  Date: 2019/6/28
  Time: 20:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="/profile/update1" method="post" enctype="multipart/form-data">

        <input type="hidden" value="${profile.id}" name="id">
        姓名：<input type="text" name="name" value="${profile.name}"><br>
        生日：<input type="text" name="birthday" value="${profile.birthday}"><br>
        性别：<select name="gender">
        <option value="男">男</option>
        <option value="女">女</option>
        </select><br>
        职业：<input type="text" name="career" value="${profile.career}"><br>
        地址：<input type="text" value="${profile.address}" name="address"><br>
        电话：<input type="text" value="${profile.mobile}" name="mobile"><br>
        头像：<input type="file" name="file"><br>
            <input type="submit" value="提交">
    </form>
    <a href="/user/list">返回首页</a>
</body>
</html>
