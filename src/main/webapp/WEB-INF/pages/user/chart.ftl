<#import "../layout.ftl" as layout>
<!DOCTYPE html>
<html>
    <@layout.header "SmartAir - Statistics">
        <#--<link rel="stylesheet" href="/resources/css/chart/nv.d3.min.css" type="text/css">
        <script src="/resources/js/chart/d3.v3.min.js" charset="utf-8"></script>
        <script src="/resources/js/chart/nv.d3.min.js"></script>
        <script src="/resources/js/jquery-2.1.3.min.js"></script>
        <script src="/resources/js/chart/chart.js"></script>-->
    </@layout.header>
<body>

<#if username??>
    <@layout.user_menu "${username}"/>
<#else>
    <@layout.user_menu "User" />
</#if>

<#--<nav class="navbar" role="navigation">
    <div class="container menu-container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/"><img src="/resources/images/common/leave.png" height="50px" alt="logo" border="0"><img src="/resources/images/common/logo.png" height="50px" alt="logo" border="0"></a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav navbar-right menu-text-style">
                <li><a href="/user/profile">User</a></li>
                <li><a href="/logout">Log Out</a></li>
            </ul>
        </div><!--/.nav-collapse &ndash;&gt;


    </div>
</nav>-->


<div class="container" style="margin-top: 50px; width: 100%">
    <h1 align="center">Statistic</h1>
    <#--<h2 align="center" class="gray-text" style="font-size: 16px">Show statistics as a <a href="/user">table</a></h2>-->
    <div id="error-bar" class="alert alert-danger" role="alert"></div>
    <div id="charts">
        <div style = "width: 90%; height: 100px; margin-left: auto; margin-right: auto;">
            <div class="alert alert-info" role="alert">
                Latest CO<sub>2</sub> level:
                <strong>
                    <span id="latestCo2Value"></span>
                </strong>
            </div>
            <canvas id="testChart" width="auto" height="100px"></canvas>
        </div>
    </div>
    <div id="loadingDiv" align="center">
        <img src="/resources/images/common/progress.gif">
    </div>
    <!--
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

        <div class="row">
             <div class="col-md-6">
                 <ul id="pagination" class="pagination">
                        <li> <a id="prevPage" class="navButton" href="#" page=1>&lt prev</a></li>
                 </ul>
             </div>
         </div>
    </div>-->

    <div id="chart" style="height: 300px;">
        <svg></svg>
    </div>

    <#--<script type="text/javascript">
        $("#searchFilter").on("change", ".chartFilter", function ( event ) {
           getChartData();
        });
        $("#searchFilter").on("click", ".navButton", function (event) {
                    event.preventDefault();
                    var page = this.getAttribute("page");
                    getChartData(page);
                }
        );
        var $loading = $('#loadingDiv').hide();
        $(document)
                .ajaxStart(function () {
                    $loading.show();
                })
                .ajaxStop(function () {
                    $loading.hide();
                });
    </script>-->


</div>

<script src="/resources/bower_components/jquery/dist/jquery.min.js"></script>
<script src="/resources/bower_components/Chart.js/Chart.min.js"></script>
<script src="/resources/js/chart/chart.v2.js"></script>

<@layout.scripts/>

</body>

</html>