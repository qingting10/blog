<%--
  Created by IntelliJ IDEA.
  User: yamco
  Date: 2020/4/13
  Time: 16:16
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>博客详情</title>
    <script src="js/vue.js" type="text/javascript" charset="utf-8"></script>
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

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
            <ul class="nav navbar-nav nav-modify">
                <li><a href="${pageContext.request.contextPath}/user/allUser">用户管理 <span class="sr-only">(current)</span></a></li>
                <li ><a href="${pageContext.request.contextPath}/blog/allBlog">博客管理</a></li>
                <li class="active"><a href="${pageContext.request.contextPath}/reply/allReply">回复管理</a></li>
<%--                <li><a href="${pageContext.request.contextPath}/notice/allNotice">通知管理</a></li>--%>
            </ul>


        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
<div class="container" >

    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
<%--                    <small>${requestScope.get("reply").title}</small>--%>
                </h1>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-md-4 column">
            <a class="btn btn-success" href="${pageContext.request.contextPath}/reply/cancelStatus/${requestScope.get("reply").id}">撤销举报</a>
            <a class="btn btn-danger" href="${pageContext.request.contextPath}/reply/del/${requestScope.get("reply").id}">删除</a>
        </div>
    </div>

    <div class="row clearfix">
        <div class="col-md-12 column" style="margin-top: 20px">
            ${requestScope.get("reply").content}
        </div>
    </div>
</div>

</body>
<style>

    /* 父标签hover悬浮，子标签显示 */
    .dropdown:hover .dropdown-menu{
        display: block;
    }
    .red{
        color: red;
    }
    .red:hover{
        color:darkred;
    }
    .nav-modify{
        margin-left: 120px;
    }
</style>
</html>