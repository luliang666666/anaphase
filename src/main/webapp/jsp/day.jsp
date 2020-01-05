<%@page isELIgnored="false" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <!-- 引入 echarts.js -->
    <script src="../echarts/echarts.min.js"></script>
    <script src="../echarts/china.js"></script>
    <script src="../boot/js/jquery-3.3.1.min.js"></script>
</head>
<body>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="width: 600px;height:400px;"></div>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    // 指定图表的配置项和数据
    var option = {
        title: {
            text: '一周内的注册人数'
        },
        tooltip: {},
        legend: {
            data:['人数']
        },
        xAxis: {
            data: ["周一","周二","周三","周四","周五","周六","周日"]
        },
        yAxis: {},
        series: [{
            name: '人数',
            data: [],
            type: 'bar',
        }]
    };
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    //动态数据
        $.ajax({
            url:"${pageContext.request.contextPath}/user/day",
            datatype:"json",
            success:function (data) {
                myChart.setOption({
                    series : [
                        {
                            data:data
                        },
                        ]
                });
            }
        })



</script>
</body>
</html>