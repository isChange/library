<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>编辑《 ${book.name}》</title>
    <%@include file="head.jsp" %>
    <script>
        $(function () {
            $('#header').load('<%=request.getContextPath()%>/jsp/admin_header.jsp');
        })
    </script>
</head>
<body background="<%=request.getContextPath()%>/static/img/book2.jpg" style=" background-repeat:no-repeat ;
background-size:100% 100%;
background-attachment: fixed;">

<div id="header" style="padding-bottom: 80px"></div>

<div class="col-xs-6 col-md-offset-3" style="position: relative;">
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h3 class="panel-title">编辑《 ${book.name}》</h3>
        </div>
        <div class="panel-body">
            <form action="http://localhost:8080/library/book/update?pageNum=<%=request.getParameter("pageNum")%>" method="post" id="updateBook">
                <input type="hidden" name="bookId" value="${book.bookId}">
                <input type="hidden" name="name" value="${book.name}">
                <div class="input-group">
                    <span class="input-group-addon">作者</span>
                   <input type="text" class="form-control" name="author" id="author" value="${book.author}">
                </div>
                <div class="input-group">
                    <span class="input-group-addon">出版社</span>
                    <input type="text" class="form-control" name="publish" id="publish" value="${book.publish}">
                </div>
                <div class="input-group">
                    <span class="input-group-addon">ISBN</span>
                    <input type="text" class="form-control" name="isbn" id="isbn" value="${book.isbn}">
                </div>
                <div class="input-group">
                    <span class="input-group-addon">简介</span>
                    <input type="text" class="form-control" name="introduction" id="introduction"
                           value="${book.introduction}">
                </div>
                <div class="input-group">
                    <span class="input-group-addon">语言</span>
                    <input type="text" class="form-control" name="language" id="language" value="${book.language}">
                </div>
                <div class="input-group">
                    <span class="input-group-addon">价格</span>
                    <input type="text" class="form-control" name="price" id="price" value="${book.price}">
                </div>
                <div class="input-group">
                    <span class="input-group-addon">出版日期</span>
                    <input type="date" class="form-control" name="pubDate" id="pubDate" value="${book.pubDate}">
                </div>
                <div class="input-group">
                    <span class="input-group-addon">分类号</span>
                    <select id="classInfo" name="classId"></select>
                </div>
                <div class="input-group">
                    <span class="input-group-addon">数量</span>
                    <input type="text" class="form-control" name="number" id="number" value="${book.number}">
                </div>
                <input type="submit" id="btn" value="确定" class="btn btn-success btn-sm" class="text-left">

            </form>
        </div>
    </div>

</div>
<script>
    $("#btn").click(function () {
        console.log($("#classInfo").val())
        if (confirm("你确定修改")){
            return true
        }else {
            return false
        }
    })
    $(document).ready(function () {
        $.ajax({
            url: '<%=request.getContextPath()%>/classInfo/list',
            type: 'GET',
            dataType: 'json',
            success: function (resp) {
                $("#classInfo").empty();
                let list = resp.data;
                for (let i = 0; i < list.length; i++) {
                    let id = list[i].classId;
                    $("#classInfo").append("<option value='"+id+"'>"+list[i].className+"</option>")
                }
            }

        })


    })

    // 监视单机事件
    <%--$(function () {--%>
    <%--    $("#btn").click(function () {--%>
    <%--        console.log($("#updateBook").serialize())--%>
    <%--        //发生ajax的put请求--%>
    <%--        $.ajax({--%>
    <%--            url:'http://localhost:8080/library/book/update',--%>
    <%--            type:'POST',--%>
    <%--            //保证请求类型能够被接收--%>
    <%--            contentType: 'application/json',--%>
    <%--            data: $("#updateBook").serialize(),--%>
    <%--            dataType:"json",--%>
    <%--            success: function (resp) {--%>
    <%--                console.log("resp.code:"+resp.code)--%>
    <%--                if (resp.code == 0){--%>
    <%--                    alert('${book.name}' + "修改成功！！")--%>
    <%--                    location.href = '<%=request.getContextPath()%>/book/list'--%>
    <%--                }else {--%>
    <%--                    alert("修改失败！")--%>
    <%--                }--%>
    <%--            }--%>
    <%--        })--%>

    <%--    })--%>
    <%--})--%>


</script>

</body>
</html>
