// Wrapping in nv.addGraph allows for '0 timeout render', stores rendered charts in nv.graphs, and may do more in the future... it's NOT required
var chart;



nv.addGraph(function() {
    chart = nv.models.lineChart()
        .options({
            transitionDuration: 300,
            useInteractiveGuideline: true
        })
    ;

    // chart sub-models (ie. xAxis, yAxis, etc) when accessed directly, return themselves, not the parent chart, so need to chain separately
    chart.xAxis
        .axisLabel("Time")
        .staggerLabels(true)
    ;
    chart.xAxis
        .tickFormat(function(d) { return d3.time.format('%c')(new Date(d)) });

    chart.yAxis
        .axisLabel('CO2')
        .tickFormat(d3.format(',.2f'))
    ;
    d3.select('#Statistic').append('svg');

    getChartData();

    nv.utils.windowResize(chart.update);

    return chart;
});

function refreshButtons(page) {
 //if page = 0 remove next button.
    var intPage = parseInt(page);
    $("#pagination #prevPage").attr("page", intPage + 1);

    if (intPage > 0 ) {
        //if nextPage button don't exist create one
        if (!$("#pagination #nextPage").length) {
            $("#pagination").append("<li> <a id='nextPage' class='navButton' href='#'>next &gt</a></li>");
        }
        $("#pagination #nextPage").attr("page", intPage - 1);
    } else {
        //if page == 0 remove next button
        if ($("#pagination #nextPage").length) {
            $("#nextPage").remove();
        }
    }

}

function getChartData(page) {
    //TODO - get page from button
    page = page || 0;

    $.getJSON( "/user/chartData",
        $('#searchFilter input, #searchFilter select').serialize()+"&page="+page ,
        function( result ) {
        d3.select('#Statistic svg')
        .datum(result)
        .call(chart);
    });

    refreshButtons(page);
}

function sinAndCos() {
    var sin = [],sin2 = [],
        cos = [];

    //Data is represented as an array of {x,y} pairs.
    for (var i = 0; i < 100; i++) {
        sin.push({x: i, y: Math.sin(i/10)});
        sin2.push({x: i, y: Math.sin(i/10) *0.25 + 0.5});
        cos.push({x: i, y: .5 * Math.cos(i/10)});
    }

    //Line chart data should be sent as an array of series objects.
    return [
        {
            values: sin,      //values - represents the array of {x,y} data points
            key: 'CO2 Bedroom', //key  - the name of the series.
            color: '#ff7f0e'  //color - optional: choose your own line color.
        },
        {
            values: cos,
            key: 'CO2 office',
            color: '#2ca02c'
        },
        {
            values: sin2,
            key: 'CO2 Living room',
            color: '#7777ff',
            area: true      //area - set to true if you want this line to turn into a filled area chart.
        }
    ];
}