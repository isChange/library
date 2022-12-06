<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.igeek.library.utils.DateUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>我的借还</title>
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
            我的借还日志
        </h3>
    </div>
    <div class="panel-body">
        <table class="table table-hover">
            <thead>
            <tr>
                <th>图书名</th>
                <th>借出日期</th>
                <th>归还日期</th>
                <th>状态</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${page.items}" var="alog">
                <tr>
                    <td><c:out value="${alog.bookName}"></c:out></td>
                    <td><c:out value="${alog.lendDate}"></c:out></td>
                    <td><c:out value="${alog.backDate}"></c:out></td>
                    <%
                        String nowDate = DateUtils.createDate();
                        request.setAttribute("nowDate",java.sql.Date.valueOf(nowDate));
                    %>
                    <fmt:parseDate value="${alog.backDate}" pattern="yyyy-MM-dd" var="backDate"></fmt:parseDate>
                    <c:if test="${backDate > nowDate}">
                        <td>待还</td>
                    </c:if>
                    <c:if test="${!(backDate > nowDate)}">
                        <td>逾期</td>
                    </c:if>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div align="center"><%@include file="page_nav.jsp"%></div>
    </div>
</div>

</body>
</html>
