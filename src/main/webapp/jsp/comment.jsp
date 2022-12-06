<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>留言板</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/main.css">
    <script src="<%=request.getContextPath()%>/static/js/jquery-3.2.1.js"></script>
</head>
<body>
<a href="<%=request.getContextPath()%>/readerCard/list?pageNum=1"><font color="blue" size="14">返回</font> </a>

<div class="wrap2">
    <div class="wrap3"><img style="position: absolute ;top: 2px;left: 0px ;width: 200px;height: 260px" src="<%=request.getContextPath()%>/static/img/library.jpg"></div>
    <div class="wrap4">
        <h1>
            <em>${book.name}</em>
            <span>
                <a>${book.author}</a>
                "著"
            </span>
        </h1>
        <p >
            <span>作品简介</span>
        </p>
        <p>
            ${book.introduction}
        </p>
    </div>
</div>
<!-- 发布留言的主体 -->
<div class="wrap">
    <div class="wrap-head">
        <div class="head-logo">
        </div>
        <div class="head-txt">
            <a class="title-txt" href="javascript:void(0)">网络不是法外之地，请您谨言慎行！！！！！</a>
        </div>
    </div>
    <div class="main-txt">
        <textarea name="" rows="" cols="" id="content" class="main-area"></textarea>
    </div>
    <div class="warp-footer">

        <div class="warp-footer-btns">
            <input type="button" id="btn" class="release-btn" value="发布" >
        </div>
    </div>
</div>
<!-- 显示留言的主体 -->
<c:forEach items="${comments}" var="comment">
    <div class="show">
        <div class="show-name">${comment.name}</div>
        <div class="show-txt">
            <p class="">${comment.content}</p>
        </div>
        <div class="show-time">${comment.createDate}</div>
    </div>
</c:forEach>
    <script>
        $("#btn").click(function () {
            if (confirm("你确定要发布吗?")){
                $.ajax({
                    url:'<%=request.getContextPath()%>/comment/add',
                    type:'POST',
                    data:{
                        bookId:<%=request.getParameter("bookId")%>,
                        content: $("#content").val()
                    },
                    dataType:'json',
                    success:function (resp) {
                        if (resp.code == 0){
                            alert("发表成功")
                            location.href = '<%=request.getContextPath()%>/comment/detail?bookId=' + <%=request.getParameter("bookId")%>
                        }else {
                            alert("发表失败")
                        }
                    }

                })
            }else {
                return false
            }

        })
    </script>
</body>

</html>

