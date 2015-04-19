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
        .tickFormat(d3.format('d'))
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
        var svg = d3.select('#Statistic svg');
        svg.selectAll("*").remove();
            //TODO remove only lines, not whole graph
        svg.datum(result)
        .call(chart);
    });

    refreshButtons(page);
}