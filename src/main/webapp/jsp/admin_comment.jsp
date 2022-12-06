<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2022/10/2
  Time: 23:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>评论管理</title>
    <%@include file="head.jsp"%>
    <script>
        $(function () {
            $('#header').load('<%=request.getContextPath()%>/jsp/admin_header.jsp');
        })
    </script>
</head>
<body background="<%=request.getContextPath()%>/static/img/u5.jpeg" style=" background-repeat:no-repeat ;
background-size:100% 100%;
background-attachment: fixed;">

<div id="header"></div>

<div style="position: relative;padding-top: 100px">
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
<form   method="post" action="http://localhost:8080/library/comment/search?pageNum=1" class="form-inline"  id="searchform">
    <div style="position: absolute;top: 80px;left: 78.5%" class="input-group">
        <select name="bookId" class="form-control"  style="width: 200px" id="search"></select>
        <span class="input-group-btn">
                <input type="submit" value="搜索" class="btn btn-default">
            </span>
    </div>
</form>
<div class="panel panel-default" style="width: 90%;margin-left: 5%">

    <div class="panel-heading">
        <h3 class="panel-title">
            评论管理
        </h3>
    </div>
    <div class="panel-body">
        <table class="table table-hover">
            <thead>
            <tr>
                <th>评论流水</th>
                <th>图书名称</th>
                <th>读者姓名</th>
                <th>评论内容</th>
                <th>评论时间</th>
                <th>删除</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${page.items}" var="alog">
                <tr>
                    <td><c:out value="${alog.id}"></c:out></td>
                    <td><c:out value="${alog.bookName}"></c:out></td>
                    <td><c:out value="${alog.readerName}"></c:out></td>
                    <td><c:out value="${alog.content}"></c:out></td>
                    <td><c:out value="${alog.createDate}"></c:out></td>
                    <td>
                        <a name="del" >
                                <button type="button" class="btn btn-danger btn-xs">删除</button>

                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div align="center"><%@include file="page_nav.jsp"%></div>
    </div>
    <script>
        $(document).ready(function () {
            $.ajax({
                url: '<%=request.getContextPath()%>/comment/bookList',
                type: 'GET',
                dataType: 'json',
                success: function (resp) {
                    $("#classInfo").empty();
                    let list = resp.data;
                    for (let i = 0; i < list.length; i++) {
                        let id = list[i].bookId;
                        $("#search").append("<option value='"+id+"'>"+list[i].name+"</option>")
                    }
                }

            })


        })

        $("a[name=del]").click(function () {
            if (confirm("你确定要删除吗")){
                $.ajax({
                    url:'<%=request.getContextPath()%>/comment/del',
                    type:'POST',
                    data:{
                        id: $(this).parents("tr").find("td:eq(0)").text(),
                        bookName: $(this).parents("tr").find("td:eq(1)").text()
                    },
                    dataType:'json',
                    success:function (resp) {
                        if (resp.code == 0){
                            alert("删除成功!")
                            location.href = '<%=request.getContextPath()%>/comment/list?pageNum=1'
                        }else {
                            alert("修改失败!")
                        }
                    }
                })
            }else {
                return false;
            }
        })
    </script>
</div>

</body>
</html>
