<%--
  Created by IntelliJ IDEA.
  User: yamco
  Date: 2020/2/2
  Time: 8:34
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>所有博客</title>
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
                <li class="active"><a href="${pageContext.request.contextPath}/blog/allBlog">博客管理</a></li>
                <li><a href="${pageContext.request.contextPath}/reply/allReply">回复管理</a></li>
<%--                <li><a href="${pageContext.request.contextPath}/notice/allNotice">通知管理</a></li>--%>
            </ul>


        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
<div class="container">

    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small>博客列表 —— 显示所有博客</small>
                </h1>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-md-4 column">
<%--            <a class="btn btn-primary" href="${pageContext.request.contextPath}/blog/toAddBlog">新增</a>--%>
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/blog/allBlog">显示所有博客</a>
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/blog/showUnhealth">被举报博客</a>
        </div>
        <div class="col-md-8 column">
            <form class="form-inline" action="${pageContext.request.contextPath}/blog/queryBlog" method="post" style="float: right">
                <span style="color: red;font-weight: bold">${error}</span>
                <input type="text" name="title" class="form-control" placeholder="请输入要查询的博客标题">
                <input type="submit" value="查询" class="btn btn-primary">
            </form>
        </div>
    </div>

    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-hover table-striped">
                <thead>
                <tr>
                    <th>博客编号</th>
                    <th>用户名</th>
                    <th>标题</th>
                    <th>更新时间</th>
                    <th>点赞数</th>
                    <th>评论数</th>
                    <th>访问量</th>
                    <th>分类名</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach var="blog" items="${requestScope.get('list')}">
                    <tr>
                        <td>${blog.getId()}</td>
                        <td>${blog.getUsername()}</td>
                        <td class="blog-title">
<%--                            <a href="${pageContext.request.contextPath}/blog/goBlogDetail/${blog.getId()}">--%>
                                    ${blog.getTitle()}
<%--                            </a>--%>

                        </td>
                        <td>${blog.getUpdateTime()}</td>
                        <td>${blog.getAppreciate()}</td>
                        <td>${blog.getCommunicate()}</td>
                        <td>${blog.getVisitedNumber()}</td>
                        <td>${blog.getClassname()}</td>
                        <td>${blog.getStatus()}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/blog/goBlogDetail/${blog.getId()}">查看</a>
                            |
                            <a class="red" href="${pageContext.request.contextPath}/blog/del/${blog.getId()}">删除</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
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
    .blog-title{
        overflow: hidden;
        text-overflow: ellipsis;
        word-break: keep-all;
        white-space: nowrap;
        max-width: 200px;
    }
    .nav-modify{
        margin-left: 120px;
    }
</style>
</html>
