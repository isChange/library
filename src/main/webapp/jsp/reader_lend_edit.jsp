<%@ page import="java.util.Date" %>
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
                借阅信息
            </h3>
        </div>
        <div class="panel-body">
            <form action="#" id="edit" >
                <div class="input-group">
                    <span  class="input-group-addon">读者证号</span>
                    <input type="text" disabled="disabled" readonly="readonly" class="form-control" name="readerId" id="readerId" value="${readercard.readerId}">
                </div>
                <div class="input-group">
                    <span class="input-group-addon">姓名</span>
                    <input type="text" disabled="disabled" class="form-control" name="name" id="name" value="${readercard.username}" >
                </div>
                <div class="input-group">
                    <span  class="input-group-addon">借阅书籍ID</span>
                    <input type="text" disabled="disabled" class="form-control" name="sex" id="bookId"  value="${book.bookId}" >
                </div>
                <div class="input-group">
                    <span class="input-group-addon">借阅书籍名称</span>
                    <input type="text" disabled="disabled" class="form-control"   value="${book.name}" >
                </div>
                <div class="input-group">
                    <span  class="input-group-addon">归还时间</span>
                    <input type="date" class="form-control" id="backDate"  value="" >
                </div>
                <br/>
                <input type="button" id="addLend" value="确定" class="btn btn-success btn-sm" class="text-left">
                <script>

                    $("#addLend").click(function () {
                        if ($("#backDate").val() == null){
                            alert("请输入归还时间")
                            return false;
                        }
                        if (confirm("你确定借阅" + $(this).parents("tr").find("td:eq(1)").text() + "吗")){
                            $.ajax({
                                url : '<%=request.getContextPath()%>/readerCard/lend',
                                type: 'POST',
                                data:{
                                    bookId: $("#bookId").val(),
                                    backDate: $("#backDate").val(),
                                },
                                dataType:'json',
                                success:function (resp) {
                                    if (resp.code == 0){
                                        alert("借阅成功！")
                                        location.href = "<%=request.getContextPath()%>/readerCard/list?pageNum=" + <%=request.getParameter("pageNum")%>
                                    }else {
                                        alert("你的余额不足请充值！或网络不流畅！！")
                                    }
                                }
                            })

                        }else {
                            return false;
                        }

                    })
                </script>
            </form>
        </div>
    </div>
</div>
</body>
</html>
