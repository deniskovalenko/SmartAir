<!DOCTYPE html>
<html lang="en">
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <meta name="google-site-verification" content="yqEVAdMcIMlxJlEa-IbHZak0JerbA8uLyOVRFUFKLhM" />

    <title>SmartAir - Statistics</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
    <link rel="icon" href="/resources/images/favicon.ico">

    <link href="/resources/css/nv.d3.css" rel="stylesheet" type="text/css">

    <#--<script src="/resources/js/d3.v3.js"></script>-->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/d3/3.5.2/d3.min.js" charset="utf-8"></script>
    <script src="/resources/js/nv.d3.js"></script>

    <script src="/resources/js/jquery-2.1.3.js"></script>
    <script src="/resources/js/chart.js"></script>


</head>

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
            <a href="/">
                <img src="/resources/images/leave.png" alt="logo" border="0">
                <img src="/resources/images/logo.png" alt="logo" border="0">
            </a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
            <#--<li class="active"><a href="#">Home</a></li>-->

                <#-- <li>   <a href="/user/addData">New entity</a> </li> -->

            </ul>

        </div><!--/.nav-collapse -->
    </div>
</nav>



<div class="container" style="margin-top: 50px">
    <div class="starter-template">
        <h1>Your SmartAir data:</h1>

    <#--<#assign  size =  data?size >-->
    <#--<#if per_page?? >-->
        <#--<#assign rows_perpage = per_page>-->
    <#--<#else>-->
        <#--<#assign rows_perpage = 10>-->
    <#--</#if>-->

    <#--<ul class="pagination">-->
        <#--<#if 0 < page >-->
            <#--<li> <a href="/user/page/${page-1}">prev</a></li>-->
        <#--</#if>-->

        <#--<#if rows_perpage-1 < size >-->
            <#--<li><a href="/user/page/${page+1}">next</a>  </li>-->
        <#--</#if>-->
   <#--</ul>-->

        <div id="Statistic" style="height: 400px;"></div>


</div>

</body>


<footer class="footer">
    <div class="container">
        <p class="text-muted" style="text-align: center">PZ-12-1 2015</p>
        <p class="text-muted" style="text-align: center">Tatarchenko, Sheremet, Kovalenko, Pleshkanovskiy, Sych, Sovgyr</p>
    </div>
</footer>
</html>