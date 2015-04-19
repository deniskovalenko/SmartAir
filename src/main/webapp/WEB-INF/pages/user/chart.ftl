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

    <link rel="stylesheet" href="/resources/css/stylesheetOverBootstrap.css" type="text/css"/>
    <link rel="icon" href="/resources/images/favicon.ico">
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resources/css/nv.d3.min.css" type="text/css">

    <script src="/resources/js/d3.v3.min.js" charset="utf-8"></script>
    <script src="/resources/js/nv.d3.min.js"></script>
    <script src="/resources/js/jquery-2.1.3.min.js"></script>
    <script src="/resources/js/chart.js"></script>
</head>

<body>
<div class="container" style="width: 100%">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a href="/"><img src="/resources/images/leave.png" height="50px" alt="logo" border="0"><img src="/resources/images/logo.png" height="50px" alt="logo" border="0"></a>
    </div>
    <div id="navbar" class="collapse navbar-collapse">
        <div id="auth" class="menu-text-style">
            <a href="#">User</a>
            <a href="#">Log Out</a>
        </div>

    </div><!--/.nav-collapse -->


</div>

<div class="container" style="width: 100%">
    <h1 align="center">Statistics</h1>
    <h2 align="center" class="gray-text" style="font-size: 16px">Show statistics as a <a href="/user">table</a></h2>

    <div class="row">
         <div class="col-md-4">
             <label class="form-group-sm">Get your statistic for last:</label>
         </div>
    </div>

    <div id="searchFilter">
        <div class="row">
            <div class="col-md-1">
                <input id="count" name="count"  type="number" class="chartFilter form-control" max="24" min="1" value="1">
            </div>
            <div class="col-md-2">
                <select name="mode" class="chartFilter form-control">
                    <option value="0">hour</option>
                    <option value="1">day</option>
                    <option value="2">week</option>
                </select>
            </div>
        </div>

         <div id="pagination">
             <#if currentPage !??>
                 <#assign currentPage = 0>
             </#if>

             <ul class="pagination">
                    <li> <a href="#" page="${currentPage+1}">&lt prev</a></li>
                <#if currentPage &gt; 0>
                    <li> <a href="#" page="${currentPage-1}">next &gt</a></li>
                </#if>
             </ul>
         </div>
    </div>

    <div id="Statistic" style="height: 400px;"></div>

    <script type="text/javascript">
        $("#searchFilter").on("change", ".chartFilter", function ( event ) {
           getChartData();
        });
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