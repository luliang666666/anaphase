<%@page isELIgnored="false" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <%--
        引入bootstrap。css
    --%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/boot/css/bootstrap.min.css">
    <%--
        jquery.js
        bootstrap.js
    --%>
    <script src="${pageContext.request.contextPath}/boot/js/jquery-3.3.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/boot/js/bootstrap.min.js"></script>
    <title>首页</title>
</head>
<body>
<div class="panel panel-danger">
    <div class="panel-heading">
        <div class="j-photo">
            <h2>
                <div class="col-sm-3">
                    <p>
                        <a href="/index.php?c=content&amp;a=list&amp;catid=112" target="_blank" style="color:#774522;">持明法洲</a>
                    </p>
                </div>
            </h2>+-
            <div class="j-tab">
                <div class="tab-ch">
                    <div class="floatl">
                        <script type="text/javascript" src="/boot/js/jquery-powerSwitch-min.js"></script>

                        <div class="jd_body">
                            <div id="jdAdSlide" class="jd_ad_slide">
                                <a href="/index.php?c=content&amp;a=show&amp;id=634" title="圣大解脱宫开光典礼法照"><img src="${pageContext.request.contextPath}/picture/shouye/1.jpg" alt="圣大解脱宫开光典礼法照" class="jd_ad_img" id="adImage0" style="display: none;"></a>
                                <a href="/index.php?c=content&amp;a=show&amp;id=639" title="朝圣法照"><img src="${pageContext.request.contextPath}/picture/shouye/2.jpg" alt="朝圣法照" class="jd_ad_img" id="adImage1" style="display: none;"></a>
                                <a href="/index.php?c=content&amp;a=show&amp;id=633" title="持明文化讲座法照"><img src="${pageContext.request.contextPath}/picture/shouye/3.jpg" alt="持明文化讲座法照" class="jd_ad_img" id="adImage2" style="display: block;"></a>
                                <a href="/index.php?c=content&amp;a=show&amp;id=425" title="上师法照"><img src="${pageContext.request.contextPath}/picture/shouye/5.jpg" alt="上师法照" class="jd_ad_img" id="adImage3" style="display: none;"></a>
                            </div>
                        </div>
                        <div id="jdAdBtn" class="jd_ad_btn"><a href="javascript:" class="jd_ad_btn_a" data-rel="adImage0"><cite style="visibility:hidden;">1</cite></a><a href="javascript:" class="jd_ad_btn_a" data-rel="adImage1"><cite style="visibility:hidden;">2</cite></a><a href="javascript:" class="jd_ad_btn_a active" data-rel="adImage2"><cite style="visibility:hidden;">3</cite></a><a href="javascript:" class="jd_ad_btn_a" data-rel="adImage3"><cite style="visibility:hidden;">4</cite></a></div><!-- add active -->
                        <script>
                            // 大的图片广告
                            // 根据图片创建id,按钮元素等，实际开发建议使用JSON数据类似
                            var htmlAdBtn = '';
                            $("#jdAdSlide img").each(function(index, image) {
                                var id = "adImage" + index;
                                htmlAdBtn = htmlAdBtn + '<a href="javascript:" class="jd_ad_btn_a" data-rel="'+ id +'"><cite style="visibility:hidden;">'+ (index + 1) +'</cite></a>';
                                image.id = id;
                            });
                            $("#jdAdBtn").html(htmlAdBtn).find("a").powerSwitch({
                                eventType: "hover",
                                classAdd: "active",
                                animation: "fade",
                                autoTime: 5000,
                                onSwitch: function(image) {
                                    if (!image.attr("src")) {
                                        image.attr("src", image.attr("data-src"));
                                    }
                                }
                            }).eq(0).trigger("mouseover");
                        </script>
                    </div>
                </div>
            </div>
        </div>

    </div>
    <div class="panel-body">
        <h4 class="panel-title">吉祥妙音</h4>
    </div>
    <div class="panel-footer">
        页脚
    </div>
</div>

</body>
</html>