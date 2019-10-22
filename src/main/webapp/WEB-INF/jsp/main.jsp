<%@ page import="org.springframework.util.ResourceUtils" %>
<%@ page import="com.cyl.springboottest1.entity.User" %>
<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/10/1
  Time: 18:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>主页</title>
    <script type="text/javascript" src="ui/jquery.min.js"></script>
    <script type="text/javascript">
        function upload() {
            $("#aa").trigger("click");
        }
        function su() {
            $("#bc").submit();
        }
         function se() {
            $.ajax({
                url:"/select",
                datatype:"json",
                success:function (data) {
                    $("#asdf").empty();
                    var $trTemp = $("<tr></tr>");
                    $trTemp.append("<td>"+ "角色id"+"</td>");
                    $trTemp.append("<td>"+ "角色名字" +"</td>");
                    $trTemp.append("<td>"+ "角色描述" +"</td>");
                    $trTemp.appendTo("#asdf");
                    for (var ak in data) {
                        var $trTemp1 = $("<tr></tr>");
                        $trTemp1.append("<td>"+ data[ak].id +"</td>");
                        $trTemp1.append("<td>"+ data[ak].cname +"</td>");
                        $trTemp1.append("<td>"+ data[ak].cstr +"</td>");

                        $trTemp1.appendTo("#asdf");
                    }


                }

            });
        }
    </script>
</head>
<body>
欢迎<a href="/toupdatepwd">${se_name.name}</a>登录,<a href="/logout">退出</a><br>
<form action="/upload" id="bc" enctype="multipart/form-data" method="post" hidden="hidden">
    <input type="file" name="headimg" id="aa" required="required" onchange="su()" accept="image/jpg"/>
    <input type="submit" value="提交">
</form><br>
点击图片可以更换：<img id="immg" src="<%="/yong.jpg?timestamp="+ new Date()%>" onclick="upload()"><br>

<button id="btn_se" onclick="se()">根据你用户ID查询coser</button><br>
<table id="asdf" border="1">
</table>

</body>
</html>
