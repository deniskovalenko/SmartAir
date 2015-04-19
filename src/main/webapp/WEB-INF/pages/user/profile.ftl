<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <meta name="google-site-verification" content="yqEVAdMcIMlxJlEa-IbHZak0JerbA8uLyOVRFUFKLhM" />

    <#if username??>
        <title>Profile ${username}</title>
    <#else>
        <title>Profile</title>
    </#if>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
    <link type="text/css" rel="stylesheet" href="/resources/css/stylesheetOverBootstrap.css"/>
    <link rel="icon" href="/resources/images/favicon.ico">
</head>

<body>
<div class="container" style="width: 100%; background-color: #fff">
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
        <#--<div id="auth" class="menu-text-style">-->
        <#--<nav class="navbar navbar-default">-->
            <#--<div class="container-fluid">-->
                <#--<div class="navbar-header">-->

            <ul class="nav navbar-nav navbar-left menu-text-style">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Devices<span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="#"><div class="dropdown-ico"><img src="/resources/images/ico-list.png"/></div>List</a></li>
                        <li class="divider"></li>
                        <li><a href="/user/addDevice?user_id=${user_id}"><div class="dropdown-ico"><img src="/resources/images/ico-add.png"/></div>Add</a></li>
                        <li><a href="#"><div class="dropdown-ico"><img src="/resources/images/ico-buy.png"/></div>Buy</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Statistics<span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="/user/statistic"><div class="dropdown-ico"><img src="/resources/images/ico-chart.png"/></div>Chart</a></li>
                        <li><a href="/user"><div class="dropdown-ico"><img src="/resources/images/ico-table.png"/></div>Table</a></li>
                    </ul>
                </li>
            </ul>


            <ul class="nav navbar-nav navbar-right menu-text-style">
                
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false" style="background-color: #bbd095; color: #fff"><b><#if username??>${username}<#else>User</#if></b><span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="#"><div class="dropdown-ico"><img src="/resources/images/ico-cogwheel.png"/></div> Account settings</a></li>
                        <li><a href="#"><div class="dropdown-ico"><img src="/resources/images/ico-question-mark.png"/></div> Help</a></li>
                        <li class="divider"></li>
                        <li><a href="/"><div class="dropdown-ico"><img src="/resources/images/ico-log-out.png"/></div>Log out</a></li>
                    </ul>
                </li>
            </ul>
        <#--</div>-->
<#--</div></nav>-->
    </div><!--/.nav-collapse -->


</div>

<div class="container" style="padding: 30px; width: 100%">
    <#if devices_count??>
        <#if devices_count == 0>
        <div class="row">
            <div class="thumbnail">
                <p align="center">You don't have any devices.</p>
                <p align="center"><a href="/user/addDevice?user_id=${user_id}" class="btn btn-primary" role="button">Add a device</a></p>
            </div>
        </div>
    <#else>
        <p align="right" style="margin-top: 10px">You have ${devices_count} devices.</p>
        <div class="row">
            <#assign elementCount =0>
            <#if devices??>
            <#list devices as device>
                <div class="col-sm-6 col-md-4">
                    <div class="thumbnail">
                        <img src="/resources/images/lamp_small.png" alt="Living room" border="0">
                        <div class="caption">
                            <h3>
                                <#if device["deviceName"]??>
                                ${device["deviceName"]}
                                <#else>
                                    no data
                                </#if>
                            </h3>
                            <p>
                            <#if device["co2Min"]??>
                                CO<sub>2</sub> minimum level: ${device["co2Min"]} ppm.
                            </#if>
                            </p><p>
                            <#if device["delay"]??>
                                Delay: ${device["delay"]} milliseconds.
                            </#if>
                            </p>
                            <p><a href="/user/statistic" class="btn btn-primary" role="button">Chart</a> <a href="/user" class="btn btn-default" role="button">Table</a></p>
                        </div>
                    </div>
                </div>
                <#assign elementCount=elementCount+1>
            </#list>
            </#if>
        </div>
    </#if>
    </#if>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
</body>

<footer class="footer">
    <div class="container">
        <p class="text-muted" style="text-align: center">PZ-12-1 2015</p>
        <p class="text-muted" style="text-align: center">Tatarchenko, Sheremet, Kovalenko, Pleshkanovskiy, Sych, Sovgyr</p>
    </div>
</footer>
</html>
