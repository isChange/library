<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${readerinfo.name}的主页</title>
    <%@include file="head.jsp"%>
    <script>
        $(function () {
            $('#header').load('<%=request.getContextPath()%>/jsp/reader_header.jsp');
        })
    </script>
</head>
<body background="<%=request.getContextPath()%>/static/img/lizhi.jpg" style=" background-repeat:no-repeat ;
background-size:100% 100%;
background-attachment: fixed;">
<div id="header" style="padding-bottom: 80px"></div>
<div class="col-xs-5 col-md-offset-3">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title">
                信息修改
            </h3>
        </div>
        <div class="panel-body">
            <form action="#" id="edit" >
                <div class="input-group">
                    <span  class="input-group-addon">读者证号</span>
                    <input type="text" readonly="readonly" class="form-control" name="readerId" id="readerId" value="${readerinfo.readerId}">
                </div>
                <div class="input-group">
                    <span class="input-group-addon">姓名</span>
                    <input type="text" class="form-control" name="name" id="name" value="${readerinfo.name}" >
                </div>
                <div class="input-group">
                    <span  class="input-group-addon">性别</span>
                    <input type="text" class="form-control" name="sex" id="sex"  value="${readerinfo.sex}" >
                </div>
                <div class="input-group">
                    <span class="input-group-addon">生日</span>
                    <input type="date" class="form-control" name="birth" id="birth"  value="${readerinfo.birth}" >
                </div>
                <div class="input-group">
                    <span  class="input-group-addon">地址</span>
                    <input type="text" class="form-control" name="address" id="address"  value="${readerinfo.address}" >
                </div>
                <div class="input-group">
                    <span  class="input-group-addon">电话</span>
                    <input type="text" class="form-control" name="phone" id="phone"  value="${readerinfo.phone}" >
                </div>
                <br/>
                <input type="button" id="update" value="确定" class="btn btn-success btn-sm" class="text-left">
                <script>
                        $("#update").click(function (){
                                $.ajax({
                                    url:'<%=request.getContextPath()%>/readerCard/updateReaderInfo',
                                    type:'POST',
                                    data:$("#edit").serialize(),
                                    dataType:"json",
                                    success:function (resp) {
                                        if(resp.code == 0){
                                            alert("修改成功")
                                            location.href = '<%=request.getContextPath()%>/readerCard/readerInfo'
                                        }else {
                                            alert("修改失败")
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
