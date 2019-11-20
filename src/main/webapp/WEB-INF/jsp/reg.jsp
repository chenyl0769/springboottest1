<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/10/12
  Time: 0:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
    <script type="text/javascript" src="ui/jquery.min.js"></script>
    <script type="text/javascript" src="js/logjs.js"></script>
</head>
<body>
${error}
<form action="/adduser" method="post" enctype="application/x-www-form-urlencoded" onsubmit="return regsub()">
    用户名：<input id="uname" type="text" name="name" onchange="checkregname()"><span id="namecheck"></span><br>
    密码：<input id="pwd" type="password" name="pwd"><span id="pwdcheck"></span><br>
    确认密码：<input id="pwd2" type="password" name="pwd1"><span id="enterpwdcheck"></span><br>
            <input type="submit" value="注册" disabled="disabled" id="registe">
</form>
<c:forEach items="${errors}" var="err">
    ${err.defaultMessage}<br>
</c:forEach>

</body>
</html>
