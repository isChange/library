<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${readercard.username}的主页</title>
    <%@include file="head.jsp"%>
    <script>
        $(function () {
            $('#header').load('<%=request.getContextPath()%>/jsp/reader_header.jsp');
        })
    </script>
</head>
<body background="<%=request.getContextPath()%>/static/img/wolf.jpg" style=" background-repeat:no-repeat ;
background-size:100% 100%;
background-attachment: fixed;">
<div id="header"></div>

</body>
</html>
