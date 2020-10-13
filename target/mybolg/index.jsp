<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>首页</title>
<%--    <script src="js/vue.js" type="text/javascript" charset="utf-8"></script>--%>

    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

</head>
<script src="./WEB-INF/js/jquery.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>


<body>
    <div class="outer">
        <form class="form-signin" method="get" action="${pageContext.request.contextPath}/user/isLoginManager" id="myform">
            <h2 class="form-signin-heading">后台管理系统</h2>
            <div class="form-item">
                <label class="sr-only">管理员</label>
                <input type="text" id="username" name="username" class="form-control" placeholder="管理员" required autofocus>
            </div>
            <div class="form-item">
                <label class="sr-only">密码</label>
                <input type="password" id="password" name="password" class="form-control" placeholder="密码" required>
            </div>
            <div class="form-item">
                <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
            </div>
        </form>
    </div>
</body>
<script>
<%--    先简单判断一下--%>
    function login(){
        var username = $("#username").val();
        var password = $("#password").val();
        console.log(username);
        console.log(password);
        var form = $("myform");
        if(username=="admin" && password=="123456"){
            return true;
        }
        else{
            return false;
        }

    }
</script>
<style>
    .outer{
        width: 400px;
        height: 300px;
        margin: 0 auto;
        margin-top: 150px;
        border-radius: 5px;
        padding: 10px;
        box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
        background:wheat;
    }
    .form-item{
        margin-top: 20px;
    }
</style>
</html>
