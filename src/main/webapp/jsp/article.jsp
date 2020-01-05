<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" contentType="text/html; UTF-8" isELIgnored="false"%>
<C:set var="app" value="${pageContext.request.contextPath}"></C:set>

    <script>

        $(function () {
            $("#articleList").jqGrid({
                url:"${app}/article/queryByPage",
                editurl:"${app}/article/deleteById",
                styleUI:"Bootstrap",
                datatype:"json",
                autowidth:true,
                records:true,
                multiselect:true,
                rowNum:3,
                rowList:[3,6,9],
                height:400,
                //caption:"文章",
                pager:"#articlePage",
                colNames:[
                    "ID","标题","作者","上师ID","日期","状态","文本","操作"
                ],
                colModel:[
                    {name:"id"},
                    {name:"title"},
                    {name:"author"},
                    {name:"guruId"},
                    {name:"createDate"},
                    {name:"status"},
                    {name:"content"},
                    {name:"other",
                        formatter:function(cellvalue, options, rowObject){
                            return "<a href='#' class='glyphicon glyphicon-th-list' onclick=\"showArticle('"+rowObject.id+"')\"></a>"+
                                "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"+
                                "<a href='javascript:void(0)' class='glyphicon glyphicon-pencil' onclick=\"showEditMoadl()\"></a>";

                        }
                    }
                ]
            }).jqGrid("navGrid","#articlePage",{search:false,addtext:"添加",add:false,edittext:"修改",edit:false,deltext:"删除"});

            /*kindeditor*/
            editor = KindEditor.create("#addTextarea", {
                afterBlur:function() {       //失去焦点时触发
                    //刷新富文本的文本域
                    this.sync()
                },
                afterChange:function(){
                    this.sync()
                },
                width:'550px',
                height:'300px',
                minHeight:150,
                minWidth:550,
                resizeType:0,  //0表示不可拖动
                allowFileManager:true,    //是否展示 图片空间
                filePostName:'img',       //上传是后台接收的名字
                uploadJson:'${app}/kindeditor/uploadImg', //上传后台的路径
                fileManagerJson:"${app}/kindeditor/getAllImgs",
            });
        })
        //添加模态框
        function showAddMoadl() {
            //展示模态框
            $("#myaddModal").modal("show");
            $("#myFotter").empty();

            $("#myFotter").append("<button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">取消</button>" +
                "<button type=\"button\" class=\"btn btn-primary\" onclick=\"addSubmit()\">保存</button>")
        }
        function addSubmit() {
            $.ajax({
                url: "${app}/article/insertArticle",
                data: $("#addForm").serialize(),
                success:function () {
                    $("#myaddModal").modal("hide");
                }
            })
        }
        //修改
        function showEditMoadl() {
            var id = jQuery("#articleList").jqGrid('getGridParam', 'selrow');
            if(id != null){
                $("#myaddModal").modal("show");
                var data = jQuery("#articleList").jqGrid('getRowData',id);
                $("#id").val(data.id)
                $("#title").val(data.title);
                $("#author").val(data.author);
                $("#status").val(data.status);
                editor.html(data.content);
                $("#myFotter").empty();
                $("#myFotter").append("<button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">取消</button>" +
                    "<button type=\"button\" class=\"btn btn-primary\" onclick=\"editSubmit();\">保存</button>")
            }else{
                alert("请选择要修改的数据")
            }
        }
        function editSubmit() {
            $.ajax({
                url:"${app}/article/updateArticle",
                data:$("#addForm").serialize(),
                success:function () {
                    $("#myaddModal").modal("hide");
                }
            })
        }
        //查看详情
        function showArticle(id) {
            $("#showArticleModal").modal("show")
            var data = jQuery("#articleList").jqGrid('getRowData', id);
            $("#myArticleContent").html(data.content);
        }
    </script>
    <ul class="nav nav-tabs">
        <li role="presentation" class="active"><a href="#">文章信息</a></li>
        <li><a href="javascript:void(0)" onclick="showAddMoadl();">添加文章</a></li>
    </ul>

<table id="articleList"></table>
<div id="articlePage" style="height: 50px"></div>

<%--模态框--%>
<div class="modal fade" id="myaddModal">
    <div class="modal-dialog">
        <%--内容--%>
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span>&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">添加文章</h4>
            </div>
            <div class="modal-body">
                <form id="addForm" class="form-horizontal">
                    <input id="id" name="id" style="display: none">
                    <div class="form-group">
                        <label for="title" class="col-sm-2 control-label col-sm-offset-2">标题</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" id="title" name="title">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="author" class="col-sm-2 control-label col-sm-offset-2">作者</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" id="author" name="author">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="status" class="col-sm-2 control-label col-sm-offset-2">状态</label>
                        <div class="col-sm-5">
                            <select class="form-control" id="status" name="status">
                                <option value="激活">激活</option>
                                <option value="未激活">未激活</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <textarea id="addTextarea" name="content">

                        </textarea>
                    </div>
                </form>
            </div>
            <div class="modal-footer" id="myFotter">

            </div>
        </div>
    </div>
</div>
<%--回显模态框--%>
<div class="modal fade" id="showArticleModal">
    <div class="modal-dialog">
        <%-- 内容 --%>
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span>&times;</span></button>
                <h4 class="modal-title">文章详情</h4>
            </div>
            <div class="modal-body">
                <div id="myArticleContent">

                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>