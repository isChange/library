<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>全部图书信息</title>
    <%@include file="head.jsp"%>
    <script>
        $(function () {
            $('#header').load('<%=request.getContextPath()%>/jsp/admin_header.jsp');
        })
    </script>
</head>
<body background="<%=request.getContextPath()%>/static/img/book2.jpg" style=" background-repeat:no-repeat ;
background-size:100% 100%;
background-attachment: fixed;" >

<div id="header"></div>

<div style="padding: 70px 550px 10px">
    <form   method="post" action="http://localhost:8080/library/book/search?pageNum=1" class="form-inline"  id="searchform">
        <div class="input-group">
           <input type="text" placeholder="输入图书名" class="form-control" id="searchWord" name="searchWord" class="form-control">
            <span class="input-group-btn">
                            <input type="submit" value="搜索" class="btn btn-default">
            </span>
        </div>
    </form>
</div>
<div style="position: relative;top: 10%">
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
<div class="panel panel-default" style="width: 90%;margin-left: 5%">
    <div class="panel-heading" style="background-color: #fff">
        <h3 class="panel-title">
            全部图书
        </h3>
    </div>
    <div class="panel-body">
        <table class="table table-hover">
            <thead>
            <tr>
                <th hidden="hidden"></th>
                <th>书名</th>
                <th>作者</th>
                <th>出版社</th>
                <th>ISBN</th>
                <th>价格</th>
                <th>剩余数量</th>
                <th>详情</th>
                <th>编辑</th>
                <th>删除</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${page.items}" var="book">
            <tr>
                <td hidden="hidden"><c:out value="${book.bookId}"></c:out></td>
                <td><c:out value="${book.name}"></c:out></td>
                <td><c:out value="${book.author}"></c:out></td>
                <td><c:out value="${book.publish}"></c:out></td>
                <td><c:out value="${book.isbn}"></c:out></td>
                <td><c:out value="${book.price}"></c:out></td>
                <td><c:out value="${book.number}"></c:out></td>
                <td><a href="<%=request.getContextPath()%>/book/detail?bookId=<c:out value="${book.bookId}"></c:out>">
                    <button type="button" class="btn btn-success btn-xs">详情</button>
                </a></td>
                <td><a href="<%=request.getContextPath()%>/book/findBookById?id=${book.bookId}&pageNum=${page.pageNo}"><button type="button" class="btn btn-info btn-xs">编辑</button></a></td>
                <td><a  href="#"><button type="button" name="btn" class="btn btn-danger btn-xs">删除</button></a></td>
            </tr>
            </c:forEach>
            </tbody>
        </table>
        <div align="center"><%@include file="page_nav.jsp"%></div>
    </div>

</div>
    <script>
        $("button[name=btn]").click(function () {
            if (confirm("你确定删除吗")){
                $.ajax({
                    url:"<%=request.getContextPath()%>/book/del/"+$(this).parents("tr").find("td:eq(0)").text() + "/" + ${page.pageNo},
                    type:'POST',
                    dataType:'json',
                    success:function (resp) {
                        if (resp.code == 0){
                            alert("删除成功！")
                            location.href = '<%=request.getContextPath()%>/book/list?pageNum=' + <%=request.getParameter("pageNum")%>
                        }else {
                            alert("有人在借阅无法删除！")
                        }
                    }

                })
            }else {
                return false;
            }
        })

    </script>
</body>
</html>
