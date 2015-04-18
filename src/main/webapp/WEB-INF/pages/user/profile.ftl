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
            <a href="#">User</a>
            <a href="#">Log Out</a>
        </div>

    </div><!--/.nav-collapse -->


</div>

<div class="container" style="margin-top: 30px; width: 100%">

    <p align="center">Hello, ${username}!</p>

</div>

</body>

<footer class="footer">
    <div class="container">
        <p class="text-muted" style="text-align: center">PZ-12-1 2015</p>
        <p class="text-muted" style="text-align: center">Tatarchenko, Sheremet, Kovalenko, Pleshkanovskiy, Sych, Sovgyr</p>
    </div>
</footer>
</html>
