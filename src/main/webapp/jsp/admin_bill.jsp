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
<form   method="post" action="http://localhost:8080/library/order/search?pageNum=1" class="form-inline"  id="searchform">
    <div style="position: absolute;top: 80px;left: 78.5%" class="input-group">
        <input type="text" name="search" class="form-control"  style="width: 200px" id="search">
        <span class="input-group-btn">
                <input type="submit" id="sub" value="搜索" class="btn btn-default">
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
                <th>充值人ID</th>
                <th>充值人姓名</th>
                <th>充值去向</th>
                <th>充值时间</th>
                <th>充值金额</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${page.items}" var="alog">
                <tr>
                    <td><c:out value="${alog.readerId}"></c:out></td>
                    <td><c:out value="${alog.name}"></c:out></td>
                    <td><c:out value="${alog.title}"></c:out></td>
                    <td><c:out value="${alog.createDate}"></c:out></td>
                    <td><c:out value="${alog.price}"></c:out></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div align="center"><%@include file="page_nav.jsp"%></div>
    </div>

</div>
    <script>
            $(document).ready(function () {
                console.log(${search})
                $("#search").val(${search})
            })
    </script>

</body>
</html>


