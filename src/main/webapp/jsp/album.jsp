<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" contentType="text/html; UTF-8" isELIgnored="false"%>
<C:set var="app" value="${pageContext.request.contextPath}"></C:set>

<div class="page-header">
    <h3>专辑管理</h3>
</div>
<ul class="nav nav-tabs" role="tablist">
    <li role="presentation" class="active">
        <a href="#home" aria-controls="home" role="tab" data-toggle="tab">专辑信息</a>
    </li>
</ul>
    <script>
        $(function () {
            var subGridTableId;
            $("#albumList").jqGrid({
                url:"${app}/album/queryByPager",
                styleUI:"Bootstrap",
                datatype:"json",
                autowidth:true,
                records:true,
                rowNum:3,
                rowList:[3,6,9],
                height:400,
                //caption:"专辑",
                pager:"#albumPage",
                colNames:[
                    "id","title","img","score","author","broadcaster","count","brief","create_date","status"
                ],
                colModel:[
                    {name:"id"},
                    {name:"title"},
                    {name:"img",edittype:'file',
                        formatter:function(cellvalue, options, rowObject){
                            /*获取头像*/
                            //console.log(cellvalue);
                            return "<img style='width:100%;height:100px' src='${app}/img/"+cellvalue+"'/>";
                        }
                    },
                    {name:"score"},
                    {name:"author"},
                    {name:"broadcaster"},
                    {name:"count"},
                    {name:"brief"},
                    {name:"create_date"},
                    {name:"status"}

                ],
                subGrid:true,   //开启子表格
                subGridRowExpanded:function (subGridId,albumId) {
                    //添加子表格的方法
                    addSubGrid(subGridId,albumId);
                }
            }).jqGrid("navGrid","#albumPage",{search:false,addtext:"添加", add:false,edittext:"修改",edit:false,deltext:"删除",del:false},

            )
        })
        //添加子表格
        function addSubGrid(subGridId,album_id) {
            //动态table  id
             subGridTableId = subGridId + "table";
            //动态div id
            var subGridDivId = subGridId + "div";
            //动态添加子表格
            $("#"+subGridId).html("<table id='"+subGridTableId+"'></table>"+
                "<div id='"+subGridDivId+"' style='height: 50px'></div>"
            )
            $("#"+subGridTableId).jqGrid({
                url:"${app}/chapter/queryByPage?id="+album_id,
                editurl:"${app}/chapter/edit?fuid="+album_id,
                styleUI:"Bootstrap",
                datatype:"json",
                autowidth:true,
                records:true,
                rowNum:3,
                caption:"章节",
                toolbar:[true,"top"],
                pager:"#"+subGridDivId,
                rowList:[3,6,9],
                colNames: [
                    "id","title","size","duration","src","status"
                ],
                colModel: [
                    {name:"id"},
                    {name:"title",editable:true},
                    {name:"size"},
                    {name:"duration"},
                    {name:"src",editable:true,edittype:'file'},
                    {name:"status",editable:true,edittype: "select",
                        editoptions:{value:"激活:激活;不激活:不激活"}
                    }
                ]
            }).jqGrid("navGrid","#"+subGridDivId,{search:false,addtext:"添加",edittext:"修改",deltext:"删除"},
                {
                    /*
                    * 修改
                    * */
                    closeAfterEdit:true,
                    afterSubmit:function (response) {
                        var id = response.responseJSON.bannerId;
                        $.ajaxFileUpload({
                            url:"${app}/chapter/upload",
                            fileElementId: 'src',
                            data:{bannerId:id},
                            type:"post",
                            success:function () {
                                $("#"+subGridDivId).jqGrid().trigger("reloadGrid");
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
                    /*添加*/
                    closeAfterAdd:true,
                    afterSubmit:function (response) {
                        var id = response.responseJSON.bannerId;
                        $.ajaxFileUpload({
                            url:"${app}/chapter/upload",
                            fileElementId: 'src',
                            data:{bannerId:id},
                            type:"post",
                            success:function () {
                                //页面更新
                                $("#"+subGridDivId).jqGrid().trigger("reloadGrid");
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
                    /*删除*/
                }
            );

            //添加按钮
            $("#t_"+subGridTableId).html("<button class='btn btn-danger' onclick=\"play('"+subGridTableId+"')\">播放 <span class='glyphicon glyphicon-play'></span></button>"+
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
                "<button class='btn btn-danger' onclick=\"download('"+subGridTableId+"')\">下载 <span class='glyphicon glyphicon-arrow-down'></span></button>"
            )
        }

        //播放
        function play(subGridTableId) {
            // 判断 用户是否选中一行  未选中->null         选中->被选中行的id
            var gr = $("#"+subGridTableId).jqGrid('getGridParam', 'selrow');
            if(gr == null){
                alert("请选中要播放的音频");
            }else{
                //1.请求后台
                //2.jqgrid 提供的方法 根据id拿到对应的值
                var data = $("#"+subGridTableId).jqGrid('getRowData', gr);
                //console.log(data);
               /* var embed = $("#embed");
                    // embed设置播放地址
                embed.prop("src","/mp3/"+data.src);
                embed.prop("autoplay",true); //立马开始播放音乐*/
                $('#myModal').modal('show');
                $("#myAudio").attr("src","${app}/mp3/"+data.src);
            }
        }

        //下载
        function download(subGridTableId) {
            // 判断 用户是否选中一行  未选中->null         选中->被选中行的id
            var gr = $("#" + subGridTableId).jqGrid('getGridParam', 'selrow');
            if (gr == null) {
                alert("请选中要下载的音频");
            } else {
                //1.请求后台
                //2.jqgrid 提供的方法 根据id拿到对应的值
                var data = $("#" + subGridTableId).jqGrid('getRowData', gr);
                console.log(data);
                location.href="${app}/chapter/download?fileName="+data.src;
            }
        }

    </script>
<table id="albumList"></table>
<div id="albumPage" style="height: 50px"></div>
<%--
<audio id="embed" src="#"></audio>--%>

<div class="modal fade" tabindex="-1" role="dialog" id="myModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">播放器</h4>
            </div>
            <div class="modal-body">
                <audio autoplay controls src="" id="myAudio"></audio>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">退出</button>
            </div>
        </div>
    </div>
</div>
