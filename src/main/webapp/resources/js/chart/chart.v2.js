(function() {
    var chartData = {};

    var chartLabels = [];
    var chartValues = [];

    function tikToDateString(date) {
        var formatter = new Intl.DateTimeFormat("ru", {
            year: "numeric",
            month: "numeric",
            day: "numeric",
            hour: "numeric",
            minute: "numeric"
        });
        return formatter.format(date);
    }

    function latestCo2Level(value) {
        document.getElementById("latestCo2Value").innerHTML = value + ' ppm';
    }

    function initErrorBar() {
        $("#error-bar").hide();
    }

    function showErrorbar(message) {
        var errorBar = $("#error-bar");
        errorBar.show();
        errorBar.text("<strong>Error:</strong>" + message);
    }

    function showCharts() {
        $('#charts').show();
    }

    function hideCharts() {
        $('#charts').hide();
    }

    $.ajax({
        xhr: function () {

                console.log("helo");
                var xhr = new window.XMLHttpRequest();
                //Upload progress
                xhr.upload.addEventListener("progress", function(evt){
                    if (evt.lengthComputable) {
                        var percentComplete = evt.loaded / evt.total;
                        //Do something with upload progress
                        console.log(percentComplete);
                    }
                }, false);
                //Download progress
                xhr.addEventListener("progress", function(evt){
                    console.log("listener added");
                    console.log(evt.lengthComputable);
                    if (evt.lengthComputable) {

                        var percentComplete = evt.loaded / evt.total;
                        //Do something with download progress
                        console.log(percentComplete);
                    }
                }, false);
                return xhr;

        },
        type: 'GET',
        url: '/user/chartData?count=1&mode=2&page=0',

        success:function(data){
            if (data.length > 0) {
                console.log(data);

                chartLabels.push('');
                chartValues.push(0);
                var length = data[0].values.length;
                if (length>20)
                {
                    length = 20;
                }
                for (var i=length; i>0; i--) {
                    var date = new Date(data[0].values[i].x);
                        //date.setMilliseconds(data[0].values[i].x);

                    chartLabels.push(tikToDateString(date));
                    //chartLabels.push('');
                }
                for (var i=length; i>0; i--) {
                    chartValues.push(data[0].values[i].y);
                }
                console.log(chartLabels);
                console.log(chartValues);

                showCharts();
                buildChart(chartLabels, chartValues);

                //document.getElementById("status").innerHTML = "Success";
            } else {
                showErrorbar("No data");
                hideCharts();
            }
        },
        error:function(){
            hideCharts();
            showError("Can't get data");
        }
    });

    function buildChart(labels, values) {
        var ctx = document.getElementById("testChart").getContext("2d");

        var options = {
            datasetFill: true,
            bezierCurve: true,
            pointDot: true,
            animation: true,
            animationSteps: 10,
            responsive: true,
            showScale: true
        };

        chartData = {
            labels: labels,
            datasets: [
                {
                    label: "My Second dataset",
                    fillColor: "rgba(151,187,205,0.2)",
                    strokeColor: "rgba(151,187,205,1)",
                    pointColor: "rgba(151,187,205,1)",
                    pointStrokeColor: "#fff",
                    pointHighlightFill: "#fff",
                    pointHighlightStroke: "rgba(151,187,205,1)",
                    data: values
                }
            ]
        };

        var myLineChart = new Chart(ctx).Line(chartData, options);

        latestCo2Level(values[values.length-1]);

        setInterval(function () {
            var date = Date.now();
            var value = [Math.round(400 + Math.random(1) * 1500)];
            myLineChart.addData(value, tikToDateString(date));
            myLineChart.removeData();

            latestCo2Level(value);
            //myLineChart.update();
        }, 5000);
    }

    initErrorBar();
    hideCharts();

    var $loading = $('#loadingDiv');
    $(document)
        .ajaxStart(function () {
            $loading.show();
        })
        .ajaxStop(function () {
            $loading.hide();
        });
})();

