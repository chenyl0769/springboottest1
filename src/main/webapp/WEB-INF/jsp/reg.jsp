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
    <script type="text/javascript">
        function checkregname() {
            $.ajax({
                url:"/checkname",
                data:{name:$("#uname").val()},
                datatype:"json",
                success:function (data) {
                    console.log(data);
                    var aa= JSON.stringify(data);
                    console.log(aa);
                    console.log(aa.get("id"));
                },
                error:function (data) {
                    console.log("bb");
                }

            });
        }
    </script>
</head>
<body>
${error}
<form action="/adduser" method="post" enctype="application/x-www-form-urlencoded">
    用户名：<input id="uname" type="text" name="name" onchange="checkregname()"><br>
    密码：<input type="password" name="pwd"><br>
    确认密码：<input type="password" name="pwd"><br>
            <input type="submit" value="注册">
</form>
</body>
</html>
