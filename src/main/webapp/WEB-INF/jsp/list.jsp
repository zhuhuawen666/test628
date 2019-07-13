<%--
  Created by IntelliJ IDEA.
  User: 10449
  Date: 2019/6/28
  Time: 16:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<script src="../../js/jquery-3.4.1.js"></script>
<html>
<head>
    <title>Title</title>
    <script>
        $(function () {
            $(".delete").click(function () {
                var del = $(this).attr("id");
                $.post("/profile/delete",{id:del},function (d) {
                    alert(d.resp);
                    $("#" + del).remove();
                },"json");

            });
            $("#sel").blur(function () {
                var sel = $(this).val();

                $("form").submit();
            });
        });
    </script>
    <script>

    </script>
</head>
<body>

你好${sessionScope.user.username}&nbsp;&nbsp;<a href="/user/logout">退出登录</a><br>
<a href="/leave">添加请假</a>
<a href="/leaveList">审批假条</a>
<table>
    <tr>
        <td>编号</td>
        <td>姓名</td>
        <td>生日</td>
        <td>性别</td>
        <td>职业</td>
        <td>住所</td>
        <td>电话</td>
        <td>所属管理员</td>
        <td>操作</td>

    </tr>
<c:forEach items="${pageInfo.list}" var="i" varStatus="c">
    <tr id="${i.id}">
        <td>${i.id}</td>
        <td>${i.name}</td>
        <td>${i.birthday}</td>
        <td>${i.gender}</td>
        <td>${i.career}</td>
        <td>${i.address}</td>
        <td>${i.mobile}</td>
        <td>${i.user.username}</td>
        <td><a href="/profile/singleP?id=${i.id}">明细</a>&nbsp;&nbsp;<a href="/profile/update?id=${i.id}">修改</a>
            &nbsp;&nbsp;
            <shiro:hasPermission name="user:delete">
            <a href="javascript:void(0)" class="delete" id="${i.id}">删除</a></td>
            </shiro:hasPermission>

    </tr>
</c:forEach>


</table>

<c:if test="${pageInfo.isFirstPage == true}">
    首页
</c:if>
<c:if test="${pageInfo.isFirstPage == false}">
    <a href="/user/list?pageNum=${pageInfo.firstPage}">首页</a>
</c:if>
<c:if test="${pageInfo.hasPreviousPage == true}">
    <a href="/user/list?pageNum=${pageInfo.pageNum - 1}">上一页</a>
</c:if>
<c:if test="${pageInfo.hasPreviousPage == false}">
    上一页
</c:if>
<c:if test="${pageInfo.hasNextPage == false}">
    下一页
</c:if>
<c:if test="${pageInfo.hasNextPage == true}">
    <a href="/user/list?pageNum=${pageInfo.pageNum + 1}">下一页</a>
</c:if>
<c:if test="${pageInfo.hasNextPage == false}">
    尾页
</c:if>
<c:if test="${pageInfo.hasNextPage == true}">
    <a href="/user/list?pageNum=${pageInfo.lastPage}">尾页</a>
</c:if>
页数：<select id="sel" onchange="window.location=this.value;">
        <option value="javascript:void(0)"></option>
    <c:forEach begin="${pageInfo.firstPage}" end="${pageInfo.lastPage}" var="i">
        <option value="/user/list?pageNum=${i}">${i}</option>
    </c:forEach>
</select>
当前第${pageInfo.pageNum}页，共${pageInfo.lastPage}页
</body>
</html>
