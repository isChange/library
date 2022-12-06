<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>编辑读者信息《 ${readerInfo.readerId}》</title>
    <%@include file="head.jsp"%>
    <script>
        $(function () {
            $('#header').load('admin_header.html');
        })
    </script>
</head>
<body background="img/book2.jpg" style=" background-repeat:no-repeat ;
background-size:100% 100%;
background-attachment: fixed;">
<div id="header" style="padding-bottom: 80px"></div>
<div class="col-xs-6 col-md-offset-3" style="position: relative;">
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h3 class="panel-title">编辑读者信息《 ${readerInfo.readerId}》</h3>
        </div>
        <div class="panel-body">
            <form action="#"  id="readeredit" >
                <input type="hidden" name="readerId" value="${readerInfo.readerId}">
                <div class="input-group">
                    <span class="input-group-addon">姓名</span>
                    <input type="text" class="form-control" name="name" id="name" value="${readerInfo.name}" >
                </div>
                <div class="input-group">
                    <span  class="input-group-addon">性别</span>
                    <input type="text" class="form-control" name="sex" id="sex"  value="${readerInfo.sex}" >
                </div>
                <div class="input-group">
                    <span class="input-group-addon">生日</span>
                    <input type="text" class="form-control" name="birth" id="birth"  value="${readerInfo.birth}" >
                </div>
                <div class="input-group">
                    <span  class="input-group-addon">地址</span>
                    <input type="text" class="form-control" name="address" id="address"  value="${readerInfo.address}" >
                </div>
                <div class="input-group">
                    <span class="input-group-addon">电话</span>
                    <input type="text" class="form-control" name="phone" id="phone" value="${readerInfo.phone}" >
                </div>
                <input type="button" id="btn" value="确定" class="btn btn-success btn-sm" class="text-left">

            </form>
        </div>
    </div>

</div>
<script>

    $("#btn").click(function () {
        $.ajax({
            url: 'http://localhost:8080/library/readerInfo/update',
            type: 'POST',
            data:$("#readeredit").serialize(),
            dataType:'json',
            success:function (resp) {
                if (resp.code == 0){
                    alert("修改成功!")
                    location.href = 'http://localhost:8080/library/readerInfo/list?pageNum=1'
                }else {
                    alert("修改失败！发生未知异常")
                }
            }
        })
    })


</script>

</body>
</html>
