<#import "../layout.ftl" as layout>
<!DOCTYPE html>
<html lang="en">
    <@layout.header "Create a new test entity" />
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
            <a class="navbar-brand" href="/user">Statistics home page</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <#if username??>
                 <li class="active">  <a href="/logout">Logout</a> </u> </li>
                <#else>
                 <li>  <a href="/login">Login</a> </u> </li>
                </#if>
            </ul>
        </div>
    </div>
</nav>
<div class="container" style="margin-top: 50px">
    <div class="starter-template">
        <form name="statistic" action="/user/addData" method="POST">
        ${errors!""}
            <h2>Device_name</h2>
            <input class="form-control" type="text" name="deviceId" width="120"><br>

            <h2>Temperature</h2>
            <input class="form-control" type="text" name="temperature" width="120"><br>

            <h2>Co2 ppm</h2>
            <input class="form-control" type="text" name="co2" width="120" ><br>
            <p>
                <input type="submit" class="btn btn-primary" value="Submit">
		</form>
    </div>
</div>
</body>
<@layout.footer/>
</html>
