<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap Login Form Template</title>
    <!-- CSS -->
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
    <link rel="stylesheet" href="../login/assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="../login/assets/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="../login/assets/css/form-elements.css">
    <link rel="stylesheet" href="../login/assets/css/style.css">


    <link rel="shortcut icon" href="../login/assets/ico/favicon.png">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="../login/assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="../login/assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="../login/assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="../login/assets/ico/apple-touch-icon-57-precomposed.png">

    <script src="../boot/js/jquery-3.3.1.min.js"></script>
    <script src="../login/assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="../login/assets/js/jquery.backstretch.min.js"></script>
    <script src="../login/assets/js/scripts.js"></script>
    <%--
    jquery-validate.js
    中文相关
    --%>
    <script src="../jquery/jquery.validate.min.js"></script>
    <script src="../jquery/messages_zh.min.js"></script>

    <script>
            getTime = function(){
                /*
                    年月日时分秒
                */
                var date = new Date();
                /*年*/
                var year = date.getFullYear();
                /*月*/
                var month = date.getMonth()+1;
                /*日*/
                var date1 = date.getDate();
                /*时*/
                var hours = date.getHours();
                /*分*/
                var minutes = date.getMinutes();
                /*秒*/
                var seconds = date.getSeconds();
                var time = year+"/"+month+"/"+date1+"<br/>"+hours+":"+minutes+":"+seconds;
                $("#time").html(time);
            }
                /*1000毫秒调用一次getTime()*/
                setInterval("getTime()",1000)

        /*验证码*/
       $(function () {
           $("#captchaImage").click(function () {
               $("#captchaImage").prop("src","${pageContext.request.contextPath}/code/getCode?time="+new Date().getTime())
           });
           /*ajax登录*/
           $("#loginButtonId").click(function () {
               var serialize = $("#loginForm").serialize();
               $.ajax({
                   type:"POST",
                   url:"${pageContext.request.contextPath}/admin/login",
                   //async:false,//同步  当有返回值时执行后面的程序
                   data:serialize,  //请求需要发送的数据
                   success:function (result) {
                       if ("ok" == result["msg"]) {//根据返回值进行跳转
                           location.href="${pageContext.request.contextPath}/jsp/admin.jsp"
                       }else {
                           $("#sp").text(result["msg"]);
                       }
                   },
                   datatype:"json"
               })
           })
        /*表单验证*/
           $(function () {

              $("#loginButtonId").click(function () {
                  var valid = $("#loginForm").valid();
                  //console.log(valid);
              })
           })
       })
    </script>
</head>

<body>

<!-- Top content -->
<div class="top-content">

    <div class="inner-bg">
        <div class="container">
            <div class="row">
                <div class="col-sm-8 col-sm-offset-2 text">
                    <h1><strong>CMFZ</strong> Login Form</h1>
                    <div class="description">
                        <p>
                            <a href="#"><strong>CMFZ</strong></a>
                        </p>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-6 col-sm-offset-3 form-box">
                    <div class="form-top" style="width: 450px">
                        <div class="form-top-left">
                            <h3>Login to showall <br/>
                                <a class="navbar-link" href="#" id="time"></a>
                            </h3>
                            <p>Enter your username and password to log on:</p>
                            <span id="sp"></span>
                        </div>
                        <div class="form-top-right">
                            <i class="fa fa-key"></i>
                        </div>
                    </div>
                    <div class="form-bottom" style="width: 450px">
                        <form role="form" action="${pageContext.request.contextPath}/admin/login" method="post" class="login-form" id="loginForm">
                            <span id="msgDiv"></span>
                            <div class="form-group">
                                <label class="sr-only" for="form-username">Username</label>
                                <input type="text" name="username" placeholder="请输入用户名..."
                                       class="form-username form-control" id="form-username" required>
                            </div>
                            <div class="form-group">
                                <label class="sr-only" for="form-password">Password</label>
                                <input type="password" name="password" placeholder="请输入密码..."
                                       class="form-password form-control" id="form-password" required>
                            </div>
                            <%--验证码--%>
                            <div class="form-group">
                                <img id="captchaImage" style="height: 48px" class="captchaImage"
                                     src="${pageContext.request.contextPath}/code/getCode">
                                <input style="width: 289px;height: 50px;border:3px solid #ddd;border-radius: 4px;" type="test" name="enCode" id="form-code" required>
                            </div>
                            <input type="button" style="width: 400px;border:1px solid #9d9d9d;border-radius: 4px;" id="loginButtonId" value="登录">
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>


</body>

</html>