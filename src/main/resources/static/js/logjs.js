function checkregname() {
    $.ajax({
        url:"/checkname",
        data:{name:$("#uname").val()},
        datatype:"json",
        success:function (data) {
            console.log(data);
            if (data!="") {
                //已经存在
                document.getElementById("registe").disabled=true;
                document.getElementById("namecheck").innerText="用户名已经存在";
            }else {
                document.getElementById("registe").disabled=false;
                document.getElementById("namecheck").innerText="用户名可用";
            }
        },
        error:function (data) {
            console.log("bb");
        }

    });
}
function regsub() {
    var name = $("#uname").val();
    var pwd = $("#pwd").val();
    var enterpwd = $("#pwd2").val();
    console.log(name);
    console.log(pwd);
    console.log(enterpwd);
    if (name==null||name=='') {
        document.getElementById("namecheck").innerText="请输入正确名字";
        return false;
    }
    if (pwd==null||pwd=='') {
        document.getElementById("pwdcheck").innerText="请输入正确密码";
        return false;
    }
    if (pwd!=enterpwd) {
        document.getElementById("enterpwdcheck").innerText="密码不一致";
        return false;
    }
    return true;
}

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