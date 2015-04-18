<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <meta name="google-site-verification" content="yqEVAdMcIMlxJlEa-IbHZak0JerbA8uLyOVRFUFKLhM" />

    <title>Profile ${username}</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
    <link type="text/css" rel="stylesheet" href="/resources/css/stylesheetOverBootstrap.css"/>
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
        <div id="auth" class="menu-text-style">
            <a href="#">${username}</a>
            <a href="#">Log Out</a>
        </div>

    </div><!--/.nav-collapse -->


</div>

<div class="container" style="padding: 30px; width: 100%">
    <#if devices_count == 0>
        <div class="row">
            <div class="thumbnail">
                <p align="center">You don't have any devices.</p>
                <p align="center"><a href="#" class="btn btn-primary" role="button">Add a device</a></p>
            </div>
        </div>
    <#else>
        <p align="right" style="margin-top: 10px">You have ${devices_count} devices.</p>
        <div class="row">
            <#assign elementCount =0>
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
                            <#if device["co2min"]??>
                                CO<sub>2</sub> minimum level: ${device["co2min"]} ppm
                            </#if>

                            <#if device["delay"]??>
                                delay ${device["delay"]} milliseconds
                            </#if>
                            </p>
                            <p><a href="#" class="btn btn-primary" role="button">Chart</a> <a href="#" class="btn btn-default" role="button">Table</a></p>
                        </div>
                    </div>
                </div>
                <#assign elementCount=elementCount+1>
            </#list>
        </div>
    </#if>
</div>

</body>

<footer class="footer">
    <div class="container">
        <p class="text-muted" style="text-align: center">PZ-12-1 2015</p>
        <p class="text-muted" style="text-align: center">Tatarchenko, Sheremet, Kovalenko, Pleshkanovskiy, Sych, Sovgyr</p>
    </div>
</footer>
</html>
