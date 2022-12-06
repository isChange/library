<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加读者</title>
    <%@include file="head.jsp"%>
    <script>
        $(function () {
            $('#header').load('<%=request.getContextPath()%>/jsp/admin_header.jsp');
        })
    </script>
</head>
<body background="<%=request.getContextPath()%>/static/img/school.jpg" style=" background-repeat:no-repeat ;
background-size:100% 100%;
background-attachment: fixed;">

<div id="header"></div>
<div class="col-xs-6 col-md-offset-3" style="padding-top: 100px;position: relative">
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h3 class="panel-title">添加读者</h3>
        </div>
        <div class="panel-body">
            <form action="#"  id="addreader" >
                <div class="input-group" style="padding-top: 20px;">
                    <span  class="input-group-addon">密码</span>
                    <input  type="password" class="form-control" name="password" id="password" >
                </div>
                <div class="input-group" style="padding-top: 20px;">
                    <span class="input-group-addon">姓名</span>
                    <input type="text" class="form-control" name="name" id="name"  >
                </div>
                <div class="input-group" style="padding-top: 20px;">
                    <span  class="input-group-addon">性别</span>
                    <input type="text" class="form-control" name="sex" id="sex" >
                </div>
                <div class="input-group" style="padding-top: 20px;">
                    <span class="input-group-addon">生日</span>
                    <input type="date" class="form-control" name="birth" id="birth"  >
                </div>
                <div class="input-group" style="padding-top: 20px;">
                    <span  class="input-group-addon">地址</span>
                    <input type="text" class="form-control" name="address" id="address"  >
                </div>
                <div class="input-group" style="padding-top: 20px;">
                    <span class="input-group-addon">电话</span>
                    <input type="text" class="form-control" name="phone" id="phone"  >
                </div>
                <input style="align-items: center" type="button" id="addbtn" value="添加" class="btn btn-success btn-sm"
                       class="text-left">
                <script>
                    $("#addbtn").click(function (){
                        $.ajax({
                            url:'<%=request.getContextPath()%>/readerInfo/add',
                            type:'POST',
                            data:$("#addreader").serialize(),
                            dataType:"json",
                            success:function (resp) {
                                if (resp.code == 0){
                                    alert("添加成功")
                                    location.href = '<%=request.getContextPath()%>/readerInfo/list?pageNum=1'
                                }else {
                                    alert("添加失败")
                                }
                            }

                        })

                    })


                </script>
            </form>
        </div>
    </div>

</div>

</body>
</html>
