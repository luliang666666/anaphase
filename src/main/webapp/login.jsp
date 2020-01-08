<%@page isELIgnored="false" contentType="text/html; utf-8" pageEncoding="UTF-8" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<h1>
    登录页面
</h1>
<form action="${pageContext.request.contextPath}/users/login">
    Username: <input type="text" name="username" placeholder="username">
    <br/>
    Password: <input type="text" name="password" placeholder="password">
    <br/>
    <input type="submit" value="提交">
</form>
</body>
</html>