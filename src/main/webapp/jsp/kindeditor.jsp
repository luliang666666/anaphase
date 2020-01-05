<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" contentType="text/html; utf-8" pageEncoding="UTF-8"%>
<c:set var="app" value="${pageContext.request.contextPath}"/>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <script charset="UTF-8" src="${app}/kindeditor/kindeditor-all-min.js"></script>
    <script charset="UTF-8" src="${app}/kindeditor/lang/zh-CN.js"></script>
    <script>
        KindEditor.ready(function(K) {
            K.create('#editor_id',{
                width:'900px',
                height:'500px',
                minHeight:400,      //最小高度
                resizeType:0,    //0：表示不可拖动
                allowFileManager:true,    //是否展示 图片空间
                filePostName:'img',       //上传时后台接收的名字
                uploadJson:'${app}/kindeditor/uploadImg', //上传后台的路径
                fileManagerJson:"${app}/kindeditor/getAllImgs"  //获取所有图片路径
            });
        });
    </script>
</head>
<body>
<form>
    <textarea id="editor_id" name="content" style="width:100px;height:100px;">
        请输入内容

    </textarea>
</form>
</body>
</html>