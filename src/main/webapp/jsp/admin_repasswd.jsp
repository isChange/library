<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>更改密码</title>
    <%@include file="head.jsp"%>
    <script>
        $(function () {
            $('#header').load('<%=request.getContextPath()%>/jsp/admin_header.jsp');
        })
    </script>
</head>
<body background="<%=request.getContextPath()%>/static/img/book2.jpg" style=" background-repeat:no-repeat ;
background-size:100% 100%;
background-attachment: fixed;">
<div id="header" style="padding-bottom: 100px"></div>
<div style="position: relative">
    <c:if test="${!empty succ}">
        <div class="alert alert-success alert-dismissable">
            <button type="button" class="close" data-dismiss="alert"
                    aria-hidden="true">
                &times;
            </button>
                ${succ}
        </div>
    </c:if>
    <c:if test="${!empty error}">
        <div class="alert alert-danger alert-dismissable">
            <button type="button" class="close" data-dismiss="alert"
                    aria-hidden="true">
                &times;
            </button>
                ${error}
        </div>
    </c:if>
</div>
<div class="col-xs-6 col-md-offset-3" style="position: relative;">
    <div class="panel panel-primary ">
        <div class="panel-heading">
            <h3 class="panel-title">密码修改</h3>
        </div>
        <div class="panel-body">
            <form action="#" class="form-inline" id="repasswd">
                <div class="input-group">
                    <input type="password" id="oldPasswd" name="oldPasswd" placeholder="输入旧密码" class="form-control"
                           class="form-control">
                    <input type="password" id="newPasswd" name="newPasswd" placeholder="输入新密码" class="form-control"
                           class="form-control">
                    <input type="password" id="reNewPasswd" name="reNewPasswd" placeholder="再次输入新密码"
                           class="form-control" class="form-control">
                    <em id="tishi" style="color: red"></em>
                    <br/>
                    <span>
                            <input type="button" id="updatePwd" value="提交" class="btn btn-default">
                    </span>
                </div>
            </form>
        </div>
    </div>
</div>
<script>
        $("#updatePwd").click(function () {
            if ($("#newPasswd").val() != $("#reNewPasswd").val()){
                alert("新密码与确认密码不相符")
                return false
            }
            $.ajax({
                url:'http://localhost:8080/library/admin/update',
                type:'POST',
                data:{
                    newPassword: $("#newPasswd").val(),
                    oldPassword: $("#oldPasswd").val(),
                },
                dataType:'json',
                success:function (resp) {
                    if (resp.code == 0){
                        alert("修改成功")
                        location.href = 'http://localhost:8080/library/book/list?pageNum=1'
                    }else {
                        alert("你的原密码不正确")
                    }
                }
            })
        })


</script>
</body>
</html>
