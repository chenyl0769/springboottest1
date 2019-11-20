<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/10/4
  Time: 9:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<script>
     function aa() {
        var bb= document.getElementById("newpwd");
        var cc =document.getElementById("enterpwd");
        if (bb.value!=cc.value){
            alert("密码不一致");
            return false ;
        }
    }
</script>
<body>
用户：${se_name.name}&nbsp;&nbsp;<a href="/tomain">返回主页</a>
<form action="/updatepwd" method="post" enctype="application/x-www-form-urlencoded">
    新密码:<input type="password" id="newpwd" name="pwd"><br>
    确认密码：<input type="password" id="enterpwd"><br>
    <button id="updatepwd" onclick="return aa()">修改</button>
</form>
<c:forEach items="${errors}" var="err">
    ${err.defaultMessage}<br>
</c:forEach>
</body>
</html>
