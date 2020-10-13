<%--
  Created by IntelliJ IDEA.
  User: yamco
  Date: 2020/2/7
  Time: 14:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新增用户</title>
    <!-- 引入 Bootstrap -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#"></a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="active"><a href="${pageContext.request.contextPath}/user/allUser">用户管理 <span class="sr-only">(current)</span></a></li>
                <li><a href="${pageContext.request.contextPath}/blog/allBlog">博客管理</a></li>
                <li><a href="${pageContext.request.contextPath}/reply/allReply">回复管理</a></li>
                <li><a href="${pageContext.request.contextPath}/notice/allNotice">通知管理</a></li>
            </ul>


        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
<div class="container">

    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small>新增管理员</small>
                </h1>
            </div>
        </div>
    </div>
    <form action="${pageContext.request.contextPath}/user/addUser" method="post">
        <div class="input-group">
            <span class="input-group-addon" >名称</span>
            <input type="text" name="username" class="form-control" placeholder="Username" aria-describedby="basic-addon1">
        </div>
        <div class="input-group">
            <span class="input-group-addon">密码</span>
            <input type="text" name="password" class="form-control" placeholder="Password" aria-describedby="basic-addon1">
        </div>
        <div class="input-group">
            <span class="input-group-addon">邮箱</span>
            <input type="text" name="email" class="form-control" placeholder="email" aria-describedby="basic-addon1">
        </div>
        <div class="btn-group" role="group" aria-label="...">
            <button type="submit" class="btn btn-default">添加</button>
        </div>
    </form>

</div>
</body>
<style>
    /* 父标签hover悬浮，子标签显示 */
    .dropdown:hover .dropdown-menu{
        display: block;
    }
    .input-group{
        margin-bottom: 20px;
        width: 400px;
    }
</style>
</html>
