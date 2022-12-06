<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>全部读者</title>
    <%@include file="head.jsp"%>
    <script>
        $(function () {
            $('#header').load('<%=request.getContextPath()%>/jsp/admin_header.jsp');
        })
    </script>
</head>
<body background="<%=request.getContextPath()%>/static/img/u1.jpg" style=" background-repeat:no-repeat ;
background-size:100% 100%;
background-attachment: fixed;">
<div id="header"></div>
<c:if test="${!empty info}">
    <script>alert("${info}");window.location.href="allreaders.html"</script>
</c:if>

<div style="position: relative;top: 15%">
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


<div class="panel panel-default" style="position:relative;top: 80px;width: 90%;margin-left: 5%">
    <div class="panel-heading">
        <h3 class="panel-title">
            全部读者
        </h3>
    </div>
    <div class="panel-body">
        <table class="table table-hover" >
            <thead>
            <tr>
                <th>读者号</th>
                <th>姓名</th>
                <th>性别</th>
                <th>生日</th>
                <th>地址</th>
                <th>电话</th>
                <th>编辑</th>
                <th>删除</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${page.items}" var="reader">
                <tr>
                    <td><c:out value="${reader.readerId}"></c:out></td>
                    <td><c:out value="${reader.name}"></c:out></td>
                    <td><c:out value="${reader.sex}"></c:out></td>
                    <td><c:out value="${reader.birth}"></c:out></td>
                    <td><c:out value="${reader.address}"></c:out></td>
                    <td><c:out value="${reader.phone}"></c:out></td>
                    <td><a href="<%=request.getContextPath()%>/readerInfo/findById?readerId=<c:out value="${reader.readerId}"></c:out>"><button type="button" class="btn btn-info btn-xs">编辑</button></a></td>
                    <td><a name="del" href="<%=request.getContextPath()%>/readerInfo/del?readerId=<c:out value="${reader.readerId}"></c:out>"><button type="button"  class="btn btn-danger btn-xs">删除</button></a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div align="center"><%@include file="page_nav.jsp"%></div>
        <span hidden="hidden" id="msg">${msg}</span>
    </div>
</div>
    <script>
        $(document).ready(function () {
            if ($("#msg").text() != ''){
                alert("${msg}")
            }
        })
        $("a[name=del]").click(function () {
            if (confirm("你确定要删除吗")){
                return true;
            }else {
                return false;
            }
        })
    </script>
</body>
</html>
