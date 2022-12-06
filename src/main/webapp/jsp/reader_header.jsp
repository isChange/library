<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-default" role="navigation" style="background-color:#fff">
    <div class="container-fluid">
        <div class="navbar-header" style="margin-left: 8%;margin-right: 1%">
            <a class="navbar-brand " href="<%=request.getContextPath()%>/jsp/reader_main.jsp"><p class="text-primary" style="font-family: 华文行楷; font-size: 200%; ">我的图书馆</p></a>
        </div>
        <div class="collapse navbar-collapse" id="example-navbar-collapse">
            <ul class="nav navbar-nav navbar-left">
                <li class="active">
                    <a href="<%=request.getContextPath()%>/readerCard/list?pageNum=1">
                        图书查询
                    </a>
                </li>
                <li>
                    <a href="<%=request.getContextPath()%>/readerCard/readerInfo" >
                        个人信息
                    </a>
                </li>
                <li >
                    <a href="<%=request.getContextPath()%>/readerCard/lendList?pageNum=1" >
                        我的借还
                    </a>
                </li>
                <li >
                    <a href="<%=request.getContextPath()%>/jsp/reader_repasswd.jsp" >
                        密码修改
                    </a>
                </li>
                <li >
                    <a href="<%=request.getContextPath()%>/order/listByReaderId?pageNum=1" >
                        我的账单
                    </a>
                </li>
                <li >
                    <a href="<%=request.getContextPath()%>/jsp/pay.jsp" >
                        充值
                    </a>
                </li>

            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#">${readercard.username}, 已登录</a></li>
                <li><a href="<%=request.getContextPath()%>/readerCard/logout">退出</a></li>
            </ul>
        </div>
    </div>
</nav>
