// 基于准备好的dom，初始化echarts实例
var barchart = echarts.init(document.getElementById('china1'));

    // 指定图表的配置项和数据
    var option = {
        title: {
            text: '第一个 ECharts 实例'
        },
        tooltip: {},
        //        legend: {
        //            data: ['销量']
        //        },
        xAxis: {
            data: ["衬衫 ", "羊毛衫 ", "雪纺衫 "]
        },
        yAxis: {},

        series: [{
            name: '销量',
            type: 'bar',
            data: [5, 20, 36]
        }]
    };

    // 使用刚指定的配置项和数据显示图表。
    barchart.setOption(option);