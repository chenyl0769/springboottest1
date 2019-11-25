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
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<head>
    <title>Login</title>
    <script src="ui/jquery.min.js" type="text/javascript"></script>
    <script src="ui/jquery.easyui.min.js" type="text/javascript"></script>
    <link rel="stylesheet" href="ui/icon.css" type="text/css">
    <link rel="stylesheet" href="ui/easyui.css" type="text/css">

    <script type="text/javascript">

        function log() {
            $('#dd').dialog({
                title:"登录",
                modal:true,
                buttons:[{text:"登录",handler:function () {

                        $('#form1').form('submit',{
                            success:function (data) {
                                console.log(data);
                            }
                        });
                    }},
                    {text:"注册",handler:function () {
                            alert("11111");
                        }}]
            });
        }
        function sub() {
            $.ajax({
                type:"POST",
                url: "/login",
                data:$("#form1").serialize(),
                success : function(msg) {
                    console.log("提示："+msg);
                    document.getElementById("name").innerText="";
                    document.getElementById("pwd").innerText="";
                    for (var i = 0; i < msg.length; i++) {
                        var a= msg[i].field;

                        var cv= document.getElementById(a);
                        cv.innerText="*"+msg[i].defaultMessage;
                    }
                },
                error:function (data) {
                    console.log(data);
                }
            });
        }
        function flush() {
            document.getElementById("randomcodeimg").setAttribute("src","/img?"+Date.now());
        }
    </script>
</head>
<body>
<c:forEach items="${errors}" var="err">
    ${err.defaultMessage}<br>
</c:forEach>
${uperror}<br>
<a href="/toreg">注册</a>
<div id="dd">
    <form id="form1" action="/login" method="post" enctype="application/x-www-form-urlencoded">
        LOGIN：<input type="text" name="user.name" value="${user.name}"><span id="name"></span><br>
        PWD:<input type="password" name="user.pwd" value="${user.pwd}"><span id="pwd"></span><br>
        Code:<input type="text" name="code"><br>
        <input type="submit" value="Login">
    </form>
    <img src="/img" id="randomcodeimg"><button onclick="flush()">换一张</button><br>

</div>

</body>
</html>
