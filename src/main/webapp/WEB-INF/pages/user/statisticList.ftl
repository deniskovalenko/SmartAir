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
    <link type="text/css" rel="stylesheet" href="/resources/css/stylesheetOverBootstrap.css"/>
    <link rel="icon" href="/resources/images/favicon.ico">
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
               <a href="/"><img src="/resources/images/leave.png" height="50px" alt="logo" border="0"><img src="/resources/images/logo.png" height="50px" alt="logo" border="0"></a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <#--<ul class="nav navbar-nav">-->
                <#--<li class="active"><a href="#">Home</a></li>-->

                 <#--<li>   <a href="/user/addData">New entity</a> </li> -->
            <#--</ul>-->
            <div id="auth" class="menu-text-style">
                <a href="#">User</a>
                <a href="#">Log Out</a>
            </div>

        </div><!--/.nav-collapse -->


    </div>
</nav>

<div class="container" style="margin-top: 50px">



    <div class="starter-template">
        <h1 align="center" class="dark-gray-text">Statistics</h1>
        <h2 align="center" class="gray-text" style="font-size: 16px">Show statistics as a <a href="/user/statistic">chart</a></h2>

    <#assign  size =  data?size >
    <#if per_page?? >
        <#assign rows_perpage = per_page>
    <#else>
        <#assign rows_perpage = 10>
    </#if>

    <ul class="pagination">
        <#if 0 < page >
            <li> <a href="/user/page/${page-1}">prev</a></li>
        </#if>

        <#if rows_perpage-1 < size >
            <li><a href="/user/page/${page+1}">next</a>  </li>
        </#if>

    </ul>

    <#assign elementCount =0>

        <#--<table bordercolor="black" border="1">-->
            <#--<tr style="background: #84e5ba;">-->
                <#--<th style="padding:10px; text-align:center;">Date/Time</th>-->
                <#--<th style="padding:10px; text-align:center;">Posted by</th>-->
                <#--<th style="padding:10px; text-align:center;">Temperature (*C)</th>-->
                <#--<th style="padding:10px; text-align:center;">CO2 (ppm)</th>-->
                <#--<th style="padding:10px; text-align:center;">Humidity</th>-->
            <#--</tr>-->
        <#--<#list data as entity>-->

            <#--<tr>-->
                <#--&lt;#&ndash;<td style="padding-left:10px; padding-right:10px;">&ndash;&gt;-->
                    <#--&lt;#&ndash;${entity["date"]?datetime?string("dd/MM/yyyy hh:mm:ss a")}&ndash;&gt;-->
                <#--&lt;#&ndash;</td>&ndash;&gt;-->
                <#--<td style="padding-left:10px; padding-right:10px;">-->
                    <#--<#if entity["date"]??>-->
                    <#--${entity["date"]?datetime?string("dd/MM/yyyy hh:mm:ss a")}-->
                    <#--<#else>-->
                        <#--no data-->
                    <#--</#if>-->
                <#--</td>-->
                <#--<td style="padding-left:10px; padding-right:10px;">-->
                    <#--<#if entity["deviceId"]??>-->
                         <#--${entity["deviceId"]}-->
                    <#--<#else>-->
                        <#--no data-->
                    <#--</#if>-->
                <#--</td>-->
                <#--<td style="text-align:center;">-->
                    <#--<#if entity["temperature"]??>-->
                         <#--${entity["temperature"]}-->
                    <#--<#else>-->
                        <#--no data-->
                    <#--</#if>-->
                <#--</td>-->
                <#--<td style="text-align:center;">-->
                    <#--<#if entity["co2"]??>-->
                         <#--${entity["co2"]}-->
                    <#--<#else>-->
                        <#--no data-->
                    <#--</#if>-->
                <#--</td>-->
                <#--<td style="text-align:center;">-->
                    <#--<#if entity["humidity"]??>-->
                        <#--${entity["humidity"]}-->
                    <#--<#else>-->
                        <#--no data-->
                    <#--</#if>-->
                <#--</td>-->
            <#--</tr>-->

            <#--<#assign elementCount=elementCount+1>-->
        <#--</#list>-->
        <#--</table>-->
        <table class="table table-striped" >
            <tr>
                <th>Date/Time</th>
                <th>Posted by</th>
                <th>Temperature (*C)</th>
                <th>CO<sub>2</sub> (ppm)</th>
                <th>Humidity</th>
            </tr>
        <#list data as entity>

            <tr>
                <td >
                    <#if entity["date"]??>
                    ${entity["date"]?datetime?string("dd/MM/yyyy hh:mm:ss a")}
                    <#else>
                        no data
                    </#if>
               </td>
                <td ><#if entity["deviceId"]??>
                ${entity["deviceId"]}
                <#else>
                    no data
                </#if>
                </td>
                <td> <#if entity["temperature"]??>
                ${entity["temperature"]}
                <#else>
                    no data
                </#if></td>
                <td > <#if entity["co2"]??>
                ${entity["co2"]}
                <#else>
                    no data
                </#if></td>

                <td style="text-align:center;">
                    <#if entity["humidity"]??>
                    ${entity["humidity"]}
                    <#else>
                        no data
                    </#if>
                </td>
            </tr>

            <#assign elementCount=elementCount+1>
        </#list>
        </table>
        <br>

    <ul class="pagination">
        <#if tag??>
            <#if 0 < page >
                <li><a href="/tag/${tag}/${page-1}">prev</a></li>
            </#if>

            <li><a href="/tag/${tag}/${page+1}">next</a></li>
        <#else>
            <#if 0 < page >
                <li> <a href="/user/page/${page-1}">prev</a></li>
            </#if>

            <#if rows_perpage -1 < elementCount>
                <li><a href="/user/page/${page+1}">next</a></li>
            </#if>
        </#if>
    </ul>


        </div>

</div><!-- /.container -->

</body>

<footer class="footer">
    <div class="container">
        <p class="text-muted" style="text-align: center">PZ-12-1 2015</p>
        <p class="text-muted" style="text-align: center">Tatarchenko, Sheremet, Kovalenko, Pleshkanovskiy, Sych, Sovgyr</p>
    </div>
</footer>
</html>
