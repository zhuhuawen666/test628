<%--
  Created by IntelliJ IDEA.
  User: 10449
  Date: 2019/7/10
  Time: 11:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="../../js/jquery-3.4.1.js"></script>
<script>
    $(function () {
       $(".agreeBtn").click(function(){
           var lid = $(this).attr("lid");

           $.get("/updateleave",{lid:lid},function (d) {
                alert(d);
               $("#tr"+lid).remove();
           },"text");
       });
    });
</script>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="1">
    <tr>

        <th>序号</th>
        <th>请假人</th>
        <th>开始时间</th>
        <th>结束时间</th>
        <th>请假理由</th>
        <th>操作</th>
    </tr>
    <c:forEach items="${leaveList}" var="i" varStatus="n">
        <tr id="tr${i.lid}">
            <td>${n.count}</td>
            <td>${i.user.username}</td>
            <td>${i.startDate}</td>
            <td>${i.endDate}</td>
            <td>${i.reason}</td>
            <td><button class="agreeBtn" lid="${i.lid}">通过</button> </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
