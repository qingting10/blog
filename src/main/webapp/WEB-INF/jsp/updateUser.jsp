<%--
  Created by IntelliJ IDEA.
  User: yamco
  Date: 2020/2/7
  Time: 14:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改用户</title>
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
            <a class="navbar-brand" href="#">首页</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="active"><a href="${pageContext.request.contextPath}/user/allUser">用户管理 <span class="sr-only">(current)</span></a></li>
                <li><a href="#">博客管理</a></li>
                <li><a href="#">分类管理</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">互动管理 <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">关注管理</a></li>
                        <li><a href="#">收藏管理</a></li>
                        <li><a href="#">评论管理</a></li>
                    </ul>
                </li>

            </ul>
            <form class="navbar-form navbar-left">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Search">
                </div>
                <button type="submit" class="btn btn-default">搜索</button>
            </form>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#">系统管理</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">我的 <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">发通知</a></li>
                        <li><a href="#">用户注册</a></li>
                        <li><a href="#">用户登录</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="#">找回密码</a></li>
                        <li><a href="#">用户注销</a></li>
                    </ul>
                </li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
<div class="container">

    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small>修改用户信息</small>
                </h1>
            </div>
        </div>
    </div>
    <form action="${pageContext.request.contextPath}/user/updateUser" method="post">
        <input type="hidden" name="id" value="${user.getId()}"/>
        <input type="hidden" name="avatar" value="${user.getAvatar()}"/>
        <input type="hidden" name="visitedNumber" value="${user.getVisitedNumber()}"/>
        <input type="hidden" name="createTime" value="${user.getCreateTime()}"/>
        <div class="input-group">
            <span class="input-group-addon" >名称</span>
            <input type="text" name="username" class="form-control" value="${user.getUsername()}" placeholder="Username" aria-describedby="basic-addon1">
        </div>
        <div class="input-group">
            <span class="input-group-addon">密码</span>
            <input type="text" name="password" class="form-control" value="${user.getPassword()}" placeholder="Password" aria-describedby="basic-addon1">
        </div>
        <div class="input-group">
            <span class="input-group-addon">邮箱</span>
            <input type="text" name="email" class="form-control" value="${user.getEmail()}" placeholder="email" aria-describedby="basic-addon1">
        </div>
        <div class="input-group">
            <span class="input-group-addon">状态</span>
            <label class="radio radio-inline">
                <input type="radio" name="status" value="正常" checked="checked" />正常
            </label>
            <label class="radio radio-inline">
                <input type="radio" name="status" value="禁用"/>禁用
            </label>
            <!-- <input type="text" name="status" class="form-control" value="${user.getStatus()}" placeholder="status" aria-describedby="basic-addon1"> -->
        </div>
        <div class="btn-group" role="group" aria-label="...">
            <button type="submit" class="btn btn-default">修改</button>
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
    .radio{
        margin-left: 30px;
        margin-top:0px;
        height: 34px;
        line-height: 34px;
    }
    .radio>input{
        margin-top: 11px;
    }
    .input-group-addon{
        height: 34px;
    }
</style>
</html>
