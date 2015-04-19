<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
    <link type="text/css" rel="stylesheet" href="/resources/css/custom/stylesheetOverBootstrap.css"/>
    <link rel="icon" href="/resources/images/common/favicon.ico">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>SmartAir - Sign Up</title>

  </head>

  <body>
  <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container extra-padding">
          <div class="navbar-header">
              <a class="navbar-brand" href="/"><img src="/resources/images/common/leave.png" alt="logo" height="50px" border="0"/><img src="/resources/images/common/logo.png" alt="logo" height="50px" border="0"/></a>
          </div>
      </div>
  </nav>

<div align="center" style="margin-top: 100px; margin-bottom: 30px">
    <h3 class="form-signin-heading dark-gray-text">Welcome to our community.</h3>
    <h4 class="gray-text">Already a user? <a href="/login">Login</a></h4>
</div>
<div align="center">
    <form class="form" method="post">

        <label for="inputUsername" class="">Username</label>
        <#if username??>
            <input name="username" value="${username}" type="text" id="inputUsername" class="form-control"  style="width:250px;height:35px" placeholder="Username" required autofocus>
        <#else>
            <input name="username" type="text" id="inputUsername" class="form-control"  style="width:250px;height:35px" placeholder="Username" required autofocus>
        </#if>

        <div class="error">
	        ${username_error!""}
        </div>

        <label for="inputPassword" class="">Password</label>
        <input type="password"name="password" value="" id="inputPassword" class="form-control"  style="width:250px;height:35px" placeholder="Password" required>

        <div class="error">
	            ${password_error!""}
        </div>

        <label for="inputPassword" class="">Verify password</label>
        <input type="password"name="verify" value="" id="inputPassword" class="form-control"  style="width:250px;height:35px" placeholder="Password" required>

        <div class="error">
	             ${verify_error!""}
        </div>

        <label class="">Email (optional)</label>
        <#if email??>
            <input type="text" name="email" value="${email}" class="form-control" placeholder="Email" style="width:250px;height:35px">
        <#else>
            <input type="text" name="email" class="form-control" placeholder="Email" style="width:250px;height:35px">
        </#if>

        <div class="error">
	         ${email_error!""}
        </div>
      <input class="btn btn-primary btn-submit" style="margin-top: 10px" type="submit" value="Sign Up">
 </form>
        </div>


  </body>

</html>
