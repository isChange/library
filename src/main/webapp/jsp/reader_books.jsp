<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>全部图书信息</title>
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

<div id="header"></div>

<div style="padding: 20px 550px 10px">
    <form method="post" action="<%=request.getContextPath()%>/readerCard/search?pageNum=1" class="form-inline" id="searchform">
        <div class="input-group">
            <input type="text" placeholder="输入图书名" class="form-control" id="search" name="searchWord"
                   class="form-control">
            <span class="input-group-btn">
                <input type="submit" value="搜索" class="btn btn-default">
            </span>
        </div>
    </form>
    <script>



    </script>
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
                <th>借还</th>
                <th>详情</th>
                <th>评论</th>
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
                    <c:set var="flag" value="false"/>
                    <c:forEach var="lend" items="${myLendList}">
                        <c:if test="${lend.bookId eq book.bookId}">
                            <c:set var="flag" value="true"/>
                        </c:if>
                    </c:forEach>
                    <c:if test="${flag}">
                            <td><a name="return" href="#">
                                <button type="button" class="btn btn-danger btn-xs">归还</button>
                            </a></td>
                    </c:if>
                    <c:if test="${not flag}">
                        <c:if test="${book.number>0}">
                            <td><a name="lend" href="#">
                                <button type="button" class="btn btn-primary btn-xs">借阅</button>
                            </a></td>
                        </c:if>
                        <c:if test="${book.number==0}">
                            <td><a name="return" href="#">
                                <button type="button" class="btn btn-danger btn-xs" disabled="disabled">借空</button>
                            </a></td>
                        </c:if>
                    </c:if>
                    <td><a href="<%=request.getContextPath()%>/readerCard/detail?bookId=<c:out value="${book.bookId}"></c:out>">
                        <button type="button" class="btn btn-success btn-xs">详情</button>
                    </a></td>
                    <td><a href="<%=request.getContextPath()%>/comment/detail?bookId=<c:out value="${book.bookId}"></c:out>">
                        <button type="button" class="btn btn-success btn-xs">评论</button>
                    </a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div align="center"><%@include file="page_nav.jsp"%></div>
    </div>
</div>
    <script>
            $("a[name=lend]").click(function () {
                if (confirm("你确定借阅" + $(this).parents("tr").find("td:eq(1)").text() + "吗")){
                    location.href = '<%=request.getContextPath()%>/lendList/lendEdit?bookId=' + $(this).parents("tr").find("td:eq(0)").text() + '&pageNum=' + ${page.pageNo}
                    <%--$.ajax({--%>
                    <%--    url : '<%=request.getContextPath()%>/readerCard/lendEdit',--%>
                    <%--    type: 'POST',--%>
                    <%--    data:{--%>
                    <%--        bookId: $(this).parents("tr").find("td:eq(0)").text()--%>
                    <%--    },--%>
                    <%--    dataType:'json',--%>
                    <%--    success:function (resp) {--%>
                    <%--        if (resp.code == 0){--%>
                    <%--            alert("借阅成功！")--%>
                    <%--            location.href = "<%=request.getContextPath()%>/readerCard/list?pageNum=${page.pageNo}"--%>
                    <%--        }else {--%>
                    <%--            alert("借阅失败")--%>
                    <%--        }--%>
                    <%--    }--%>
                    <%--})--%>

                }else {
                    return false;
                }

            })

            $("a[name=return]").click(function () {
                if (confirm("你确定归还" + $(this).parents("tr").find("td:eq(1)").text() + "吗")){
                    $.ajax({
                        url : '<%=request.getContextPath()%>/readerCard/return',
                        type: 'POST',
                        data:{
                            bookId: $(this).parents("tr").find("td:eq(0)").text()
                        },
                        dataType:'json',
                        success:function (resp) {
                            if (resp.code == 0){
                                alert("归还成功！")
                                location.href = "<%=request.getContextPath()%>/readerCard/list?pageNum=${page.pageNo}"
                            }else {
                                alert("归还失败")
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
