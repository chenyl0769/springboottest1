<%@ page import="org.springframework.validation.ObjectError" %>
<%@ page import="java.util.List" %>
<%@ page import="org.springframework.validation.FieldError" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/10/1
  Time: 0:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <title>Login</title>
</head>
<body>
<%
    String ss;
    if (request.getAttribute("errors")!=null){
        for (ObjectError error :(List<ObjectError>)request.getAttribute("errors")){
            FieldError fieldError = (FieldError) error;
            request.setAttribute(fieldError.getField(),fieldError.getDefaultMessage());
        }
    }
%>




<form action="/login" method="post" enctype="application/x-www-form-urlencoded">
    LOGIN：<input type="text" name="name" value="${user.name}">${name}<br>
    PWD:<input type="password" name="pwd" value="${user.pwd}">${pwd}<br>
    <input type="submit" value="Login">
</form>
${uperror}
</body>
</html>
