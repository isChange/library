<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav style="position:fixed;z-index: 999;width: 100%;background-color: #25c6fc" class="navbar navbar-default"
     role="navigation">
    <div class="container-fluid">
        <div class="navbar-header" style="margin-left: 8%;margin-right: 1%">
            <a class="navbar-brand" href="<%=request.getContextPath()%>/jsp/admin_main.jsp" style="font-family: 华文行楷; font-size: 250%; color: white">图书管理系统</a>
        </div>
        <div class="collapse navbar-collapse" >
            <ul class="nav navbar-nav navbar-left">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" style="color: white">
                        图书管理
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="http://localhost:8080/library/book/list?pageNum=1">全部图书</a></li>
                        <li class="divider"></li>
                        <li><a href="<%=request.getContextPath()%>/jsp/admin_book_add.jsp">增加图书</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" style="color: white">
                        读者管理
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="<%=request.getContextPath()%>/readerInfo/list?pageNum=1">全部读者</a></li>
                        <li class="divider"></li>
                        <li><a href="<%=request.getContextPath()%>/jsp/admin_reader_add.jsp">增加读者</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" style="color: white">
                        借还管理
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="<%=request.getContextPath()%>/lendList/list?pageNum=1">借还日志</a></li>
                    </ul>
                </li>
                <li >
                    <a href="<%=request.getContextPath()%>/jsp/admin_repasswd.jsp" style="color: white">
                        密码修改
                    </a>
                </li>
                <li >
                    <a href="<%=request.getContextPath()%>/comment/list?pageNum=1" style="color: white">
                        评论管理
                    </a>
                </li>
                <li >
                    <a href="<%=request.getContextPath()%>/order/list?pageNum=1" style="color: white">
                        充值记录
                    </a>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#" style="color: white">${sessionScope.get("admin").username}已登录</a>
                </li>
                <li><a href="<%=request.getContextPath()%>/admin/logout" style="color: white">退出</a></li>
            </ul>
        </div>
    </div>
</nav>
