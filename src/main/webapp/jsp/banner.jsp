<%@page isELIgnored="false" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <div class="page-header">
        <h3>轮播图管理</h3>
    </div>
    <script>
        $(function () {
            $("#bannerList").jqGrid({
                url:"${pageContext.request.contextPath}/banner/queryPager",
                editurl:"${pageContext.request.contextPath}/banner/add",
                datatype:"json",
                styleUI:"Bootstrap",
                colNames:["id","title","img","create_date","status"],
                pager:"#bannerPager",               //分页
                rowNum:3,                            //每页显示多少条
                rowList:[3,6,9],
                viewrecords:true,                   //是否显示总记录数
                autowidth:true,                     //自适应父容器
                multiselect:true,                   //是否多选
                height:'400px',
                colModel:[
                    {name:"id"},
                    {name:"title",'editable':true},
                    {name:"img",'editable':true,edittype:'file',
                        formatter:function(cellvalue, options, rowObject){
                        /*获取头像*/
                            console.log(cellvalue);
                            return "<img style='width:100%;height:100px' src='${pageContext.request.contextPath}/img/"+cellvalue+"'/>";
                        }
                    },
                    {name:"create_date",editable:true,edittype:"date"},
                    {name:"status",editable:true,edittype: "select",
                        editoptions:{value:"激活:激活;不激活:不激活"}
                    }
                ]
            }).jqGrid("navGrid","#bannerPager",{search:false,addtext:"添加",edittext:"修改",deltext:"删除"},
                {
                    /*
                    * 修改
                    * */
                    closeAfterEdit:true,
                    afterSubmit:function (response) {
                        var id = response.responseJSON.bannerId;
                        $.ajaxFileUpload({
                            url:"${pageContext.request.contextPath}/banner/upload",
                            fileElementId: 'img',
                            data:{bannerId:id},
                            type:"post",
                            success:function () {
                                $("bannerList").jqGrid().trigger("reloadGrid");
                                $("#msgDiv").show();
                                setTimeout(function () {
                                    $("#msgDiv").hide();
                                }, 3000)
                            }
                        })
                        return response;
                    }

                },
                {
                    /*
                    * 添加
                    * */
                    closeAfterAdd:true,
                    afterSubmit:function (response) {
                        var id = response.responseJSON.bannerId;
                        $.ajaxFileUpload({
                            url:"${pageContext.request.contextPath}/banner/upload",
                            fileElementId:'img',
                            data:{bannerId:id},
                            type:"post",
                            success:function () {
                                $("bannerList").trigger("reloadGrid");
                                $("#msgDiv").show();
                                setTimeout(function () {
                                    $("#msgDiv").hide();
                                },3000)
                            }
                        })
                        return response;
                    }
                },
                {
                    /*
                    * 删除
                    * */
                }
            );
        })
    </script>
</head>
<body>
    <table id="bannerList"></table>
    <div id="bannerPager" style="height: 50px"></div>
    <div class="alert alert-success" style="display:none" id="msgDiv">
        添加成功
    </div>
</body>
</html>