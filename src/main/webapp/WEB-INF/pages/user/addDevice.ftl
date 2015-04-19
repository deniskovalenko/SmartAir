<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <link rel="icon" href="/resources/images/favicon.ico">

    <title>SmartAir - Add a new device</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
    <link type="text/css" rel="stylesheet" href="/resources/css/stylesheetOverBootstrap.css"/>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container extra-padding">
        <div class="navbar-header">
            <a class="navbar-brand" href="/"><img src="/resources/images/leave.png" alt="logo" height="50px" border="0"/><img src="/resources/images/logo.png" alt="logo" height="50px" border="0"/></a>
        </div>
    </div>
</nav>
<div align="center" style="margin-top: 100px; margin-bottom: 30px">
    <h3 class="form-signin-heading dark-gray-text">Log in.</h3>
    <h4 class="gray-text">Back to <a href="/user/profile?user_id=${user_id}">devices' list</a></h4>
</div>
<div align="center">
    <form class="form" method="post">

        <label for="inputDeviceId" class="">Device Id</label>
        <#if deviceId??>
            <input name="deviceId" value="${deviceId}" type="text" id="inputDeviceId" class="form-control"  style="width:250px;height:35px" placeholder="device id" required autofocus>
        <#else>
            <input name="deviceId" value="" type="text" id="inputDeviceId" class="form-control"  style="width:250px;height:35px" placeholder="device id" required autofocus>
        </#if>

        <label for="inputDeviceName" class="">Device Name</label>
    <#if deviceName??>
        <input name="deviceName" value="${deviceName}" type="text" id="inputDeviceName" class="form-control"  style="width:250px;height:35px" placeholder="device name" required autofocus>
    <#else>
        <input name="deviceName" value="" type="text" id="inputDeviceName" class="form-control"  style="width:250px;height:35px" placeholder="device name" required autofocus>
    </#if>

        <label for="inputDelay" class="">Delay</label>
    <#if delay??>
        <input name="delay" value="${delay}" type="text" id="inputDelay" class="form-control"  style="width:250px;height:35px" placeholder="delay" required autofocus>
    <#else>
        <input name="delay" value="" type="text" id="inputDelay" class="form-control"  style="width:250px;height:35px" placeholder="delay" required autofocus>
    </#if>

        <label for="inputco2MinLevel" class="">CO<sub>2</sub> minimum level</label>
    <#if co2MinLevel??>
        <input name="co2MinLevel" value="${co2MinLevel}" type="text" id="inputco2MinLevel" class="form-control"  style="width:250px;height:35px" placeholder="co2" required autofocus>
    <#else>
        <input name="co2MinLevel" value="" type="text" id="inputco2MinLevel" class="form-control"  style="width:250px;height:35px" placeholder="co2" required autofocus>
    </#if>

        <input class="btn btn-primary btn-submit" style="margin-top: 10px" type="submit" value="Add">
    </form>
</div>

<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>

</body>

</html>


