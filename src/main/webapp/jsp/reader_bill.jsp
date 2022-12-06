<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2022/10/4
  Time: 16:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.igeek.library.utils.DateUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>我的账单</title>
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

<div class="panel panel-default" style="width: 90%;margin-left: 5%;margin-top: 5%">
    <div class="panel-heading">
        <h3 class="panel-title">
            我的账单
        </h3>
    </div>
    <div class="panel-body">
        <table class="table table-hover">
            <thead>
            <tr>
                <th>充值人</th>
                <th>充值去向</th>
                <th>充值时间</th>
                <th>充值金额</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${page.items}" var="alog">
                <tr>
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

</body>
</html>
