<#macro header title="SmartAir">
<head>
    <#--google analytics-->
    <#--<script>
        (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
                    (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
                m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
        })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

        ga('create', 'UA-71784385-1', 'auto');
        ga('send', 'pageview');
    </script>-->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <#--<meta name="google-site-verification" content="yqEVAdMcIMlxJlEa-IbHZak0JerbA8uLyOVRFUFKLhM" />-->

    <title>${title}</title>

    <!-- Bootstrap core CSS -->

    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
    <link type="text/css" rel="stylesheet" href="/resources/index/styles/css/stylesheetOverBootstrap2.css">
    <link rel="icon" href="/resources/images/common/favicon.ico">
    <#nested/>
</head>
</#macro>

<#macro footer>
<footer class="footer bg-dark">
    <div class="container">
        <div class="share ">
            <ul>
                <li>
                    <a class="fb" href="http://www.facebook.com/share.php?u=http://smartair.tech"></a>
                </li>
                <li>
                    <a class="g_plus" href="https://plus.google.com/share?url=http://smartair.tech"></a>
                </li>
                <li>
                    <a class="tw" href="https://twitter.com/share" data-dnt="true"></a>
                </li>
                <li>
                    <a class="vk" href="http://vkontakte.ru/share.php?url=http://smartair.tech"></a>
                </li>
            </ul>
        </div>
        <#--<p class="text-muted" style="text-align: center">PZ-12-1 2015</p>-->
        <#--<p class="text-muted" style="text-align: center"><span class="faded-people">Tatarchenko</span>, <span class="faded-people">Sheremet</span>, Kovalenko, Pleshkanovskiy, Sych, <span class="faded-people">Sovgyr</span></p>-->
        <div class="copyright">SmartAir © 2016 Rights Reserved</div>
    </div>
</footer>
</#macro>

<#macro user_menu username>
<nav class="navbar">
<div class="container" style="width: 100%; background-color: #fff">
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
<ul class="nav navbar-nav navbar-left menu-text-style">
    <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Statistics<span class="caret"></span></a>
        <ul class="dropdown-menu" role="menu">
            <li><a href="/user/statistic"><div class="dropdown-ico"><img src="/resources/images/userMenu/ico-chart.png"/></div>Chart</a></li>
            <li><a href="/user"><div class="dropdown-ico"><img src="/resources/images/userMenu/ico-table.png"/></div>Table</a></li>
        </ul>
    </li>
    <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Devices<span class="caret"></span></a>
        <ul class="dropdown-menu" role="menu">
            <li><a href="#"><div class="dropdown-ico"><img src="/resources/images/userMenu/ico-list.png"/></div>List</a></li>
            <li class="divider"></li>
            <li><a href="/user/addDevice"><div class="dropdown-ico"><img src="/resources/images/userMenu/ico-add.png"/></div>Add</a></li>
            <li><a href="/order"><div class="dropdown-ico"><img src="/resources/images/userMenu/ico-buy.png"/></div>Order</a></li>
        </ul>
    </li>
</ul>
<ul class="nav navbar-nav navbar-right menu-text-style">
    <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false" style="background-color: #bbd095; color: #fff"><b><#if username??>${username}<#else>User</#if></b><span class="caret"></span></a>
        <ul class="dropdown-menu" role="menu">
            <li><a href="/user/settings"><div class="dropdown-ico"><img src="/resources/images/userMenu/ico-cogwheel.png"/></div>Account settings</a></li>
            <li><a href="#"><div class="dropdown-ico"><img src="/resources/images/userMenu/ico-question-mark.png"/></div>Help</a></li>
            <li class="divider"></li>
            <li><a href="/logout"><div class="dropdown-ico"><img src="/resources/images/userMenu/ico-log-out.png"/></div>Log out</a></li>
        </ul>
    </li>
</ul>
</div>
</div>
</nav>
</#macro>

<#--<#macro common_menu>
<nav class="navbar" role="navigation">
<div class="container" style="width: 100%;">
<div class="navbar-header">
    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
    </button>
    <a class="navbar-brand" href="/"><img src="/resources/images/common/leave.png" height="50px" alt="logo" border="0"><img src="resources/images/common/logo.png" height="50px" alt="logo" border="0"></a>
</div>

<div id="navbar" class="collapse navbar-collapse">
    <ul class="nav navbar-nav navbar-left menu-text-style">
    &lt;#&ndash;<div id="auth" class="menu-text-style">&ndash;&gt;
        <li><a href="/user">About</a></li>
        <li><a href="/user">Information</a></li>
        <li><a href="/user">Order</a></li>
        <li><a href="/contacts">Contacts</a></li>
    </ul>
    <ul class="nav navbar-nav navbar-right menu-text-style">
        <li><a href="/signup">Sign up</a></li>
        <li><a href="/login">Log in</a></li>
        <li></li>
    </ul>
</div>
</div>
</nav>
</#macro>-->

<#macro scripts>
    <script src="/resources/js/jquery-2.1.3.min.js"></script>
    <script src="/resources/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
</#macro>