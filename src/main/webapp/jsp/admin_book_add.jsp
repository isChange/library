<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>图书信息添加</title>
    <%@include file="head.jsp"%>
    <style>
        .form-group {
            margin-bottom: 0;
        }
    </style>
    <script>
        $(function () {
            $('#header').load('admin_header.jsp');
        })
    </script>
</head>
<body background="<%=request.getContextPath()%>/static/img/sky.jpg" style=" background-repeat:no-repeat ;
background-size:100% 100%;
background-attachment: fixed;">

<div id="header"></div>
<div style="position: relative;padding-top: 60px; width: 80%;margin-left: 10%">
    <form action="#"  id="addbook">
        <div class="form-group">
            <label for="name">图书名</label>
            <input type="text" class="form-control" name="name" id="name" placeholder="请输入书名">
        </div>
        <div class="form-group">
            <label for="author">作者</label>
            <input type="text" class="form-control" name="author" id="author" placeholder="请输入作者名">
        </div>
        <div class="form-group">
            <label for="publish">出版社</label>
            <input type="text" class="form-control" name="publish" id="publish" placeholder="请输入出版社">
        </div>
        <div class="form-group">
            <label for="isbn">ISBN</label>
            <input type="text" class="form-control" name="isbn" id="isbn" placeholder="请输入ISBN">
        </div>
        <div class="form-group">
            <label for="introduction">简介</label>
            <textarea class="form-control" rows="3" name="introduction" id="introduction"
                      placeholder="请输入简介"></textarea>
        </div>
        <div class="form-group">
            <label for="language">语言</label>
            <input type="text" class="form-control" name="language" id="language" placeholder="请输入语言">
        </div>
        <div class="form-group">
            <label for="price">价格</label>
            <input type="text" class="form-control" name="price" id="price" placeholder="请输入价格">
        </div>
        <div class="form-group">
            <label for="pubstr">出版日期</label>
            <input type="date" class="form-control" name="pubDate" id="pubstr" placeholder="请输入出版日期">
        </div>
        <div class="form-group">
            <label >分类</label>
            <select id="classInfo" name="classId"></select>
        </div>
        <div class="form-group">
            <label for="number">数量</label>
            <input type="text" class="form-control" name="number" id="number" placeholder="请输入图书数量">
        </div>

        <input type="button" id="addbtn" value="添加" class="btn btn-success btn-sm" class="text-left">

    </form>
</div>
    <script>
        $("#addbtn").click(function () {
            $.ajax({
                url:'<%=request.getContextPath()%>/book/add',
                type:'POST',
                data:$("#addbook").serialize(),
                dataType:'json',
                success:function (resp) {
                    if (resp.code == 0){
                        alert("添加成功!")
                        location.href = '<%=request.getContextPath()%>/book/list?pageNum=1'
                    }else {
                        alert("添加失败")
                    }
                }
            })
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
                        $("#classInfo").append("<option value='"+list[i].classId+"'>"+list[i].className+"</option>")
                    }
                }

            })


        })
    </script>
</body>
</html>
