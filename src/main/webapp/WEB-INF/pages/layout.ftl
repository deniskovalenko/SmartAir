<#macro header title="SmartAir">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <meta name="google-site-verification" content="yqEVAdMcIMlxJlEa-IbHZak0JerbA8uLyOVRFUFKLhM" />

    <title>${title}</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
    <link type="text/css" rel="stylesheet" href="/resources/css/stylesheetOverBootstrap.css"/>
    <link rel="icon" href="/resources/images/favicon.ico">
    <#nested/>
</head>
</#macro>

<#macro footer>
<footer class="footer">
    <div class="container">
        <p class="text-muted" style="text-align: center">PZ-12-1 2015</p>
        <p class="text-muted" style="text-align: center">Tatarchenko, Sheremet, Kovalenko, Pleshkanovskiy, Sych, Sovgyr</p>
    </div>
</footer>
</#macro>

<#macro user_menu user_id username>
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
        <ul class="nav navbar-nav navbar-left menu-text-style">
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Statistics<span class="caret"></span></a>
                <ul class="dropdown-menu" role="menu">
                    <li><a href="/user/statistic"><div class="dropdown-ico"><img src="/resources/images/ico-chart.png"/></div>Chart</a></li>
                    <li><a href="/user"><div class="dropdown-ico"><img src="/resources/images/ico-table.png"/></div>Table</a></li>
                </ul>
            </li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Devices<span class="caret"></span></a>
                <ul class="dropdown-menu" role="menu">
                    <li><a href="#"><div class="dropdown-ico"><img src="/resources/images/ico-list.png"/></div>List</a></li>
                    <li class="divider"></li>
                    <li><a href="/user/addDevice?user_id=${user_id}"><div class="dropdown-ico"><img src="/resources/images/ico-add.png"/></div>Add</a></li>
                    <li><a href="#"><div class="dropdown-ico"><img src="/resources/images/ico-buy.png"/></div>Order</a></li>
                </ul>
            </li>
        </ul>
        <ul class="nav navbar-nav navbar-right menu-text-style">
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false" style="background-color: #bbd095; color: #fff"><b><#if username??>${username}<#else>User</#if></b><span class="caret"></span></a>
                <ul class="dropdown-menu" role="menu">
                    <li><a href="#"><div class="dropdown-ico"><img src="/resources/images/ico-cogwheel.png"/></div>Account settings</a></li>
                    <li><a href="#"><div class="dropdown-ico"><img src="/resources/images/ico-question-mark.png"/></div>Help</a></li>
                    <li class="divider"></li>
                    <li><a href="/"><div class="dropdown-ico"><img src="/resources/images/ico-log-out.png"/></div>Log out</a></li>
                </ul>
            </li>
        </ul>
    </div>
</div>
</#macro>

<#macro common_menu>
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
    <div id="navbar" class="navbar-left">
        <div id="auth" class="menu-text-style">
            <a href="/user">About</a>
            <a href="/user">Information</a>
            <a href="/user">Order</a>
            <a href="/user">Contacts</a>
        </div>
    </div>
    <div id="navbar" class="navbar-right">
        <div id="auth" class="menu-text-style">
            <a href="/signup">Sign up</a>
            <a href="/login">Log in</a>
        </div>
    </div>
</div>
</#macro>