<%--
  Created by IntelliJ IDEA.
  User: 刘燚是锦鲤
  Date: 2022/7/15
  Time: 8:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div id="page_nav">
    <%--只有当前页面不是首页是才显示--%>
    <c:if test="${requestScope.page.pageNo > 1}">
        <a href="${requestScope.page.url}?pageNum=1">首页</a>
        <a href="${requestScope.page.url}?pageNum=${requestScope.page.pageNo - 1}">上一页</a>
    </c:if>

    <%--页码输出的开始--%>
    <%--按情况显示页面如 1 2 3 4 5  --%>

    <c:choose>
        <c:when test="${requestScope.page.pageTotal < 5}">
            <c:set var="begin" value="1"></c:set>
            <c:set var="end" value="${requestScope.page.pageTotal}"></c:set>
        </c:when>
        <c:when test="${requestScope.page.pageTotal >= 5}">
            <c:choose>
                <c:when test="${requestScope.page.pageNo <= 3}">
                    <c:set var="begin" value="1"></c:set>
                    <c:set var="end" value="5"></c:set>
                </c:when>
                <c:when test="${requestScope.page.pageNo > requestScope.page.pageTotal-3}">
                    <c:set var="begin" value="${requestScope.page.pageTotal - 4}"></c:set>
                    <c:set var="end" value="${requestScope.page.pageTotal }"></c:set>
                </c:when>
                <c:otherwise>
                    <c:set var="begin" value="${requestScope.page.pageNo - 2}"></c:set>
                    <c:set var="end" value="${requestScope.page.pageNo + 2}"></c:set>
                </c:otherwise>
            </c:choose>
        </c:when>

    </c:choose>
    <c:forEach begin="${begin}" end="${end}" var="i">
        <c:if test="${i == requestScope.page.pageNo}">
            【${i}】
        </c:if>
        <c:if test="${i != requestScope.page.pageNo}">
            <a href="${requestScope.page.url}?pageNum=${i}&search=${search}">${i}</a>
        </c:if>
    </c:forEach>
    <%--页码输出的结束--%>


    <%--只有当前页面不是末页是才显示--%>
    <c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal}">
        <a href="${requestScope.page.url}?pageNum=${requestScope.page.pageNo + 1}&search=${search}">下一页</a>
        <a href="${requestScope.page.url}?pageNum=${requestScope.page.pageTotal}&search=${search}">末页</a>
    </c:if>

    共${requestScope.page.pageTotal}页，${requestScope.page.pageTotalCount}条记录 到第<input value="" name="pn" id="pn_input"/>页
    <input type="button" id="searchPageBtn" value="确定">

    <script type="text/javascript">
        $(function (){
            $("#searchPageBtn").click(function () {
                var pageNo = $("#pn_input").val();
                var total = ${requestScope.page.pageTotal}
                if (pageNo > total || pageNo <= 0){
                    alert("请输入正常页数")
                    return false
                }
                // javaScript 语言中提供了一个 location 地址栏对象
                // 它有一个属性叫 href.它可以获取浏览器地址栏中的地址
                // href 属性可读，可写
                location.href = "${pageScope.basePath}${requestScope.page.url}?pageNum=" +
                    pageNo +"&search="${search};

            })

        })

    </script>
</div>
</body>
</html>
