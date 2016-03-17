<#import "../layout.ftl" as layout/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <link rel="icon" href="resources/images/common/favicon.ico">

    <title>SmartAir - Login</title>

<#--
    &lt;#&ndash;wink css&ndash;&gt;
    <link type="text/css" rel="stylesheet" href="resources/css/wink.css"/>

    <!-- Bootstrap core CSS &ndash;&gt;
    <link rel="stylesheet" href="resources/css/bootstrap.min.css">
    <link type="text/css" rel="stylesheet" href="resources/css/custom/stylesheetOverBootstrap2.css"/>
-->

    <!-- inject:css -->
    <link rel="stylesheet" href="../../../resources/index/styles/styles-login.css">
    <!-- endinject -->

    <#--<link rel="stylesheet" href="resources/index/styles/styles-login.css" type="text/css">-->
    <#--<link type="text/css" rel="stylesheet" href="resources/css/custom/stylesheetOverBootstrap2.css"/>-->

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
<nav class="navbar" role="navigation">
    <div class="container menu-container">
        <div class="navbar-header">
            <a class="" href="/"><img src="resources/images/common/leave.png" alt="logo" height="50px" border="0"/><img src="resources/images/common/logo.png" alt="logo" height="50px" border="0"/></a>
        </div>
    </div>
</nav>
<div align="center" style="margin-top: 100px; margin-bottom: 30px">
    <h3 class="form-signin-heading dark-gray-text">Log in.</h3>
    <h4 class="gray-text">Need to create an account? <a href="signup">Sign Up</a></h4>
</div>
<div align="center">

    <form class="form" action="/j_spring_security_check" name="loginForm" method="post">

        <label for="inputEmail" class="">Username</label>
        <#if username??>
            <input name="j_username" value="${username}" type="text" id="inputEmail" class="form-control"  style="width:250px;height:35px" placeholder="Username" required autofocus>
        <#else>
            <input name="j_username" value="" type="text" id="inputEmail" class="form-control"  style="width:250px;height:35px" placeholder="Username" required autofocus>
        </#if>

        <div class="row">
            <div style="width :35%"></div>
            <label for="inputPassword">Password</label>
            <div style="width :30%">
                <input required class="form-control" id="inputPassword" name="j_password"
                       placeholder="Password" type="password"
                       style="width:250px;height:35px" padding-right: 44px;" >

                <div class="error">
                <#if login_error??>
                    <h4 style="color:red;"> ${login_error}</h4>
                </#if>
                </div>
                <#--<button id="hideShowPassword" aria-pressed="false"-->
                        <#--style="position: absolute; right: 15px; top: 0px;"-->
                        <#--class="hideShowPassword-toggle" tabindex="0"-->
                        <#--aria-label="Show Password" role="button" type="button" onclick="showPasswordLogin()">Show-->
                <#--</button>-->
            </div>
        </div>
            <div class="row" align="center">
                <div style="width :35%"></div>
                <div style="width :30%">
                     <input type="checkbox" name="_spring_security_remember_me">
                     Remember me
                </div>
            </div>

        <input class="btn btn-primary btn-submit" style="margin-top: 10px" type="submit" value="Sign In">
        <#--<button class="btn btn-lg btn-primary btn-block" type="submit"  style="width:250px;height:35px">Sign in</button>-->
    </form>
</div>
<script>


    function showPasswordLogin() {
        var showClass = "hideShowPassword-toggle-hide"
        var wink = document.getElementById("hideShowPassword");
        var passwordField = $('#inputPassword').get(0);

        if (passwordField.type == 'password') {
            passwordField.type = 'text';
            addClass(wink, showClass);

        } else {
            passwordField.type = 'password';
            removeClass(wink, showClass);

        }
    }

    function addClass(o, c) {
        var re = new RegExp("(^|\\s)" + c + "(\\s|$)", "g")
        if (re.test(o.className)) return
        o.className = (o.className + " " + c).replace(/\s+/g, " ").replace(/(^ | $)/g, "")
    }

    function removeClass(o, c) {
        var re = new RegExp("(^|\\s)" + c + "(\\s|$)", "g")
        o.className = o.className.replace(re, "$1").replace(/\s+/g, " ").replace(/(^ | $)/g, "")
    }
</script>

<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>

<@layout.scripts/>

</body>
</html>


