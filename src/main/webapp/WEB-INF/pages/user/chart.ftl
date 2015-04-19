<#import "../layout.ftl" as layout>
<!DOCTYPE html>
<html>
    <@layout.header "SmartAir - Statistics">
        <link rel="stylesheet" href="/resources/css/chart/nv.d3.min.css" type="text/css">
        <script src="/resources/js/chart/d3.v3.min.js" charset="utf-8"></script>
        <script src="/resources/js/chart/nv.d3.min.js"></script>
        <script src="/resources/js/jquery-2.1.3.min.js"></script>
        <script src="/resources/js/chart/chart.js"></script>
    </@layout.header>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a href="/"><img src="/resources/images/common/leave.png" height="50px" alt="logo" border="0"><img src="/resources/images/common/logo.png" height="50px" alt="logo" border="0"></a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <div id="auth" class="menu-text-style">
                <a href="#">User</a>
                <a href="#">Log Out</a>
            </div>

        </div><!--/.nav-collapse -->


    </div>
</nav>


<div class="container" style="margin-top: 50px; width: 100%">
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


         <ul id="pagination" class="pagination">
                <li> <a id="prevPage" class="navButton" href="#" page=1>&lt prev</a></li>
         </ul>
    </div>

    <div id="Statistic" style="height: 400px;"></div>

    <script type="text/javascript">
        $("#searchFilter").on("change", ".chartFilter", function ( event ) {
           getChartData();
        });
        $("#searchFilter").on("click", ".navButton", function (event) {
                    event.preventDefault();
                    var page = this.getAttribute("page");
                    getChartData(page);
                }
        );
    </script>

</div>

</body>

<@layout.footer/>
</html>