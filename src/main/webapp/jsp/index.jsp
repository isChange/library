<%@ page contentType="text/html;charset=UTF-8"  language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>图书馆首页</title>
    <link rel="stylesheet" href="/library/static/css/bootstrap.min.css">
    <script src="/library/static/js/jquery-3.2.1.js"></script>
    <script src="/library/static/js/bootstrap.min.js" ></script>
    <script src="/library/static/js/js.cookie.js"></script>

    <style>
        #login{
           height: 50%;
            width: 28%;
            margin-left: auto;
            margin-right: auto;
            margin-top: 5%;
            display: block;
            position: center;
        }

        .form-group {
            margin-bottom: 0;
        }
        * {
            padding:0;
            margin:0;
        }
    </style>
</head>
<body background="/library/static/img/timg.jpg" style=" background-repeat:no-repeat ;
background-size:100% 100%;
background-attachment: fixed;">
<h2 style="text-align: center; color: white; font-family: '华文行楷'; font-size: 500%">图 书 馆</h2>

<div class="panel panel-default" id="login">
    <div class="panel-heading" style="background-color: #fff">
        <h3 class="panel-title">请登录</h3>
    </div>

        <div class="panel-body">
            <div class="form-group">
                <label for="id">账号</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label style="color: red" id="message1" ></label>
                <input type="text" class="form-control" name="username" id="id" placeholder="请输入账号">
            </div>
            <div class="form-group">
                <label for="passwd">密码</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label style="color: red" id="message2"></label>
                <input type="password" class="form-control" name="password" id="passwd" placeholder="请输入密码">
            </div>
            <div class="form-group">
                <label for="passwd">验证码</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label style="color: red" id="message3"></label>
                <input type="password" class="form-control" name="password" id="passwd2" placeholder="请输入密码">
                <img id="codeImg" src="<%=request.getContextPath()%>/Kaptcha.jpg"><a id="change" href="#">看不清换一张</a>
            </div>
            <div class="checkbox text-left">
                <label>
                    <input type="radio"  id="amdin" >管理员
                    <input type="radio"  id="reader" checked="checked">读者
                </label>
            </div>

            <p style="text-align: right;color: red;position: absolute" id="info"></p><br/>
            <button id="loginButton"  class="btn btn-primary  btn-block">登陆
            </button>
        </div>
</div>
    <script>



        $(function (){
            $("#change").click(function (){
                $("#codeImg").attr("src","<%=request.getContextPath()%>/Kaptcha.jpg")
            })

            $("#amdin").change(function () {
                $("#reader")[0].checked = !$("#reader")[0].checked
            })

            $("#reader").change(function () {
                $("#amdin")[0].checked = !$("#amdin")[0].checked
            })


                $("#loginButton").click(function () {
                    if($("#id").val().trim() == ''){
                        $("#message1").html("用户名不能为空")
                    }
                    if ($("#passwd").val().trim() == ''){
                        $("#message2").html("密码不能为空")
                    }
                    if ($("#amdin").prop("checked")){
                        $.ajax({
                            url: 'http://localhost:8080/library/admin/login',
                            type:'POST',
                            data:{
                                username : $("#id").val(),
                                password : $("#passwd").val(),
                                code: $("#passwd2").val()
                            },
                            dataType:'json',
                            success:function (resp) {
                                console.log(resp)
                                if (resp.code == -1){
                                    alert("用户名错误!!")
                                }else if (resp.code == -2){
                                    alert("用户密码错误！")
                                }else if (resp.code == -3){
                                    alert(resp.data)
                                }else if (resp.code == 0){
                                    alert("登入成功!")
                                    location.href = 'http://localhost:8080/library/jsp/admin_main.jsp'

                                }
                            }

                        })
                    }else {
                        var str = /^[a-z]+$/
                        if(str.test($("#id").val())){
                            alert("请选择管理员登入")
                            return false;
                        }
                        $.ajax({
                            url: 'http://localhost:8080/library/readerCard/login',
                            type:'POST',
                            data:{
                                readerId : $("#id").val(),
                                password : $("#passwd").val(),
                                code: $("#passwd2").val()
                            },
                            dataType:'json',
                            success:function (resp) {
                                console.log(resp)
                                if (resp.code == -1){
                                    alert("用户名错误!!")
                                }else if (resp.code == -2){
                                    alert("用户密码错误！")
                                }else if (resp.code == -3){
                                    alert(resp.data)
                                }else if (resp.code == 0){
                                    alert("登入成功!")
                                    location.href = 'http://localhost:8080/library/jsp/reader_main.jsp'

                                }
                            }

                        })

                    }

            })

        })


    </script>
</div>

</body>
</html>
