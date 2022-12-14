<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>借还日志</title>
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
<div class="panel panel-default" style="width: 90%;margin-left: 5%">
    <div class="panel-heading">
        <h3 class="panel-title">
            借还日志
        </h3>
    </div>
    <div class="panel-body">
        <table class="table table-hover">
            <thead>
            <tr>
                <th>流水号</th>
                <th>图书号</th>
                <th>读者证号</th>
                <th>借出日期</th>
                <th>归还日期</th>
                <th>删除</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${page.items}" var="alog">
                <tr>
                    <td><c:out value="${alog.serNum}"></c:out></td>
                    <td><c:out value="${alog.bookId}"></c:out></td>
                    <td><c:out value="${alog.readerId}"></c:out></td>
                    <td><c:out value="${alog.lendDate}"></c:out></td>
                    <td><c:out value="${alog.backDate}"></c:out></td>
                    <td>
                        <a name="del" href="#">
                            <c:if test="${!empty alog.backDate}">
                                <button type="button" class="btn btn-danger btn-xs">删除</button>
                            </c:if>
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div align="center"><%@include file="page_nav.jsp"%></div>
    </div>
        <script>
            $("a[name=del]").click(function () {
                if (confirm("你确定要删除吗")){
                    $.ajax({
                        url:'<%=request.getContextPath()%>/lendList/del/'+$(this).parents("tr").find("td:eq(0)").text() +'/' + ${page.pageNo},
                        type:'POST',
                        dataType:'json',
                        success:function (resp) {
                            if (resp.code == 0){
                                alert("删除成功!")
                                location.href = '<%=request.getContextPath()%>/lendList/list?pageNum=' + resp.data
                            }else {
                                alert("有人在借阅无法删除!")
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
