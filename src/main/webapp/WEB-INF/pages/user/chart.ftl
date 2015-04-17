<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SmartAir - Statistics</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
    <link type="text/css" rel="stylesheet" href="/resources/css/stylesheetOverBootstrap.css"/>
    <link rel="icon" href="/resources/images/favicon.ico">
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
    <link href="/resources/css/nv.d3.css" rel="stylesheet" type="text/css">

    <script src="https://cdnjs.cloudflare.com/ajax/libs/d3/3.5.2/d3.min.js" charset="utf-8"></script>
    <script src="/resources/js/nv.d3.js"></script>
    <script src="/resources/js/jquery-2.1.3.js"></script>
    <script src="/resources/js/chart.js"></script>
</head>

<body>
<div class="container">
        <h1 align="center">Statistics</h1>

        <div class="row">
             <div class="col-md-4">
                 <label class="form-group-sm">Get your statistic for last:</label>
             </div>
        </div>
        <div class="row">
            <div class="col-md-1">
                <input id="count" type="number" name="count" class="form-control" max="24" min="1" value="1">
            </div>
            <div class="col-md-2">
                <select class="form-control">
                    <option value="0">hour</option>
                    <option value="1">day</option>
                    <option value="2">week</option>
                </select>
            </div>
        </div>

        <ul class="pagination">
            <li> <a href="/user/statistic">&lt prev</a></li>
            <li> <a href="/user/statistic">next &gt</a></li>
        </ul>

        <div id="Statistic" style="height: 400px;"></div>

    <script type="text/javascript">
        $('input').bootstrapNumber({upClass : 'danger', downClass : 'success'});


    </script>
</div>
</body>

<footer class="footer">
    <div class="container">
        <p class="text-muted" style="text-align: center">PZ-12-1 2015</p>
        <p class="text-muted" style="text-align: center">Tatarchenko, Sheremet, Kovalenko, Pleshkanovskiy, Sych, Sovgyr</p>
    </div>
</footer>
</html>