<%@page isELIgnored="false" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <%--
        引入bootstrap.css
    --%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/boot/css/bootstrap.min.css">
    <%--jqGrid相关css--%>
    <link rel="stylesheet" href="../jqgrid/css/trirand/ui.jqgrid-bootstrap.css">
    <%--
        jquery.js
        bootstrap.js
    --%>
    <script src="${pageContext.request.contextPath}/boot/js/jquery-3.3.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/boot/js/bootstrap.min.js"></script>
    <script src="../jqgrid/js/trirand/jquery.jqGrid.min.js"></script>
    <script src="../jqgrid/js/trirand/i18n/grid.locale-cn.js"></script>
    <%--文件上传--%>
    <script src="../boot/js/ajaxfileupload.js"></script>
    <%--kindeditor--%>
    <script charset="UTF-8" src="${pageContext.request.contextPath}/kindeditor/kindeditor-all-min.js"></script>
    <script charset="UTF-8" src="${pageContext.request.contextPath}/kindeditor/lang/zh-CN.js"></script>

    <title>首页</title>
    <script type="text/javascript">

            $(function () {
                //轮播自动播放
                $('#myCarousel').carousel({
                    //自动3秒播放
                    interval : 3000,
                });
            })

    </script>
    <script>
        function logOut() {
            $.ajax({
                url: "${pageContext.request.contextPath}/admin/logOut",
                datatype: "json",
                success: function () {
                    location.href = "${pageContext.request.contextPath}/login/login.jsp"
                }
            })
        }
    </script>
</head>
<body>
<%--
    导航条
--%>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a href="" class="navbar-brand">
                持明法洲后台管理系统
            </a>
            <button class="navbar-toggle collapsed" data-toggle="collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
        </div>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="">欢迎：<b><shiro:principal></shiro:principal></b></a></li>
                <li><a href="#" onclick="logOut()">
                    <span class="glyphicon glyphicon-share"></span>
                    退出登录
                </a></li>
            </ul>
        </div>
    </div>
</nav>
<%--
    页面主体
--%>
<div class="container-fluid">
    <div class="row">
        <%--导航菜单--%>
        <div class="col-sm-2">
            <div class="panel-group" id="accordion">
                <%--用户列表--%>
                <div class="panel panel-default text-center" >
                    <div class="panel-heading"id="headingOne">
                        <h4 class="panel-title">
                            <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
                                <h3>用户管理</h3>
                            </a>
                        </h4>
                    </div>
                    <div id="collapseOne" class="panel-collapse collapse">
                        <div class="panel-body">
                            <%--
                                javascript:void(0)  阻止页面提交
                            --%>
                            <a href="javascript:void(0)" onclick="$('#myContent').load('echarts.jsp')" class="btn btn-primary">
                                用户列表
                            </a>
                        </div>
                    </div>
                </div>
                <%--上师列表--%>
                <div class="panel panel-default text-center" >
                    <div class="panel-heading"id="headingOne1">
                        <h4 class="panel-title">
                            <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne1">
                                <h3>上师管理</h3>
                            </a>
                        </h4>
                    </div>
                    <div id="collapseOne1" class="panel-collapse collapse">
                        <div class="panel-body">
                            <%--
                                javascript:void(0)  阻止页面提交
                            --%>
                            <a href="javascript:void(0)" onclick="$('#myContent').load('banner.jsp')" class="btn btn-primary">
                                上师列表
                            </a>
                        </div>
                    </div>
                </div>
                <%--文章列表--%>
                <div class="panel panel-default text-center" >
                    <div class="panel-heading"id="headingOne2">
                        <h4 class="panel-title">
                            <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne2">
                                <h3>文章管理</h3>
                            </a>
                        </h4>
                    </div>
                    <div id="collapseOne2" class="panel-collapse collapse">
                        <div class="panel-body">
                            <%--
                                javascript:void(0)  阻止页面提交
                            --%>
                            <a href="javascript:void(0)" onclick="$('#myContent').load('article.jsp')" class="btn btn-primary">
                                文章列表
                            </a>
                        </div>
                    </div>
                </div>
                <%--专辑列表--%>
                <div class="panel panel-default text-center" >
                    <div class="panel-heading"id="headingOne3">
                        <h4 class="panel-title">
                            <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne3">
                                <h3>专辑管理</h3>
                            </a>
                        </h4>
                    </div>
                    <div id="collapseOne3" class="panel-collapse collapse">
                        <div class="panel-body">
                            <%--
                                javascript:void(0)  阻止页面提交
                            --%>
                            <a href="javascript:void(0)" onclick="$('#myContent').load('album.jsp')" class="btn btn-primary">
                                专辑列表
                            </a>
                        </div>
                    </div>
                </div>
                <%--轮播图管理--%>
                <div class="panel panel-default text-center" >
                    <div class="panel-heading"id="headingOne4">
                        <h4 class="panel-title">
                            <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne4">
                                <h3>轮播图管理</h3>
                            </a>
                        </h4>
                    </div>
                    <div id="collapseOne4" class="panel-collapse collapse">
                        <div class="panel-body">
                            <%--
                                javascript:void(0)  阻止页面提交
                            --%>
                            <a href="javascript:void(0)" onclick="$('#myContent').load('banner.jsp')" class="btn btn-primary">
                                轮播图列表
                            </a>
                        </div>
                    </div>
                </div>

                <%--admin--%>
                <shiro:hasRole name="superAdmin">
                    <div class="panel panel-default text-center">
                        <div class="panel-heading" id="headingOne5">
                            <h4 class="panel-title">
                                <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne5">
                                    <h3>admin管理</h3>
                                </a>
                            </h4>
                        </div>
                        <div id="collapseOne5" class="panel-collapse collapse">
                            <div class="panel-body">
                                    <%--
                                        javascript:void(0)  阻止页面提交
                                    --%>
                                <a href="javascript:void(0)" onclick="$('#myContent').load('echarts.jsp')"
                                   class="btn btn-primary">
                                    admin列表
                                </a>
                            </div>
                        </div>
                    </div>
                </shiro:hasRole>
            </div>
        </div>
        <%--
            内容展示
        --%>
        <div class="col-sm-10" id="myContent">
            <%--
                巨幕
            --%>
            <div class="jumbotron">
                <h4>欢迎来到持明法州后台管理系统</h4>
            </div>
        <%--</div>
            <div class="col-sm-10" id="myContent">--%>
                <div id="myCarousel" class="carousel slide">

                    <ol class="carousel-indicators">

                        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>

                        <li data-target="#myCarousel" data-slide-to="1"></li>

                        <li data-target="#myCarousel" data-slide-to="2"></li>

                    </ol>
                    <div class="carousel-inner">

                        <div class="item active" style="background:#223240">

                            <img style="width: 1249px;height: 500px;" src="${pageContext.request.contextPath}/img/1577408616327_timg.jpg" alt="first img"><!--图片不居中，让图片居中给这个img设置margin:0 auto-->

                        </div>

                        <div class="item" style="background:#F5E4DC;">

                            <img style="width: 1249px;height: 500px;" src="${pageContext.request.contextPath}/img/1577408583790_timg.gif" alt="second img">

                        </div>

                        <div class="item" style="background:#DE2A2D;">

                            <img style="width: 1249px;height: 500px;"  src="${pageContext.request.contextPath}/img/1577408603470_489d-hefphqm4115037.jpg" alt="third img">

                        </div>

                    </div>

                    <a href="#myCarousel" data-slide="prev" class="carousel-control left">

                        <span class="glyphicon glyphicon-chevron-left"></span>

                    </a>

                    <a href="#myCarousel" data-slide="next" class="carousel-control right">

                        <span class="glyphicon glyphicon-chevron-right"></span>

                    </a>
                </div>

            </div>

    </div>
</div>

<nav class="navbar navbar-default navbar-static-top">
    <div class="container">
        <center><h5 style="margin-top: 20px">@百知教育baizhi@zparkhr.com.cn</h5></center>
    </div>
</nav>
</body>
</html>