<#import "../layout.ftl" as layout/>
<!DOCTYPE html>
<html>
<@layout.header "SmartAir - Keep your area co2 free">
	<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
	<meta name="google-site-verification" content="-2u6NzbFCW-LpLIE6tLmyjfpPfWbOmCe9nP-wS2hHj0" />


<!-- Custom CSS -->
<link href="/resources/css/landingpage.css" rel="stylesheet">
<!-- Custom Fonts -->
<link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->

</@layout.header>
<body>
<@layout.common_menu/>
<#--<div class="container" style="width: 100%; background-color: #fff">-->
        <#--<div  id="header">-->
          <#--<div id="slogan">-->
            <#--<span id="bigtext">Keep you area CO<sub>2</sub> free</span>-->
            <#--<span id="smalltext">Service that helps you to keep track of air quality</span>-->
           <#--</div>-->
          <#--<div id="header-buttons">-->
            <#--<a class="btn btn-index green-btn" href="/user">Order</a><br>-->
            <#--<a class="btn btn-index white-btn" href="/user">Details</a>-->
          <#--</div>-->
        <#--</div>-->
<#--</div>-->

<!-- Header -->
<a name="about"></a>
<div class="intro-header">
    <div class="container">

        <div class="row">
            <div class="col-lg-12">
                <div class="intro-message">
                    <h1>Keep you area CO<sub>2</sub> free</h1>
                    <h3>Service that helps you to keep track of air quality</h3>
                    <hr class="intro-divider">
                    <ul class="list-inline intro-social-buttons">
                        <li>
                            <a href="#" class="btn btn-default btn-lg btn-index green-btn"><i class="fa fa-twitter fa-fw"></i> <span class="network-name">Order</span></a>
                        </li>
                        <li>
                            <a href="#" class="btn btn-default btn-lg btn-index white-btn"><i class="fa fa-github fa-fw"></i> <span class="network-name">Details</span></a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>

    </div>
    <!-- /.container -->

</div>
<!-- /.intro-header -->

<!-- Page Content -->

<a  name="services"></a>
<div class="content-section-a">

    <div class="container">
        <div class="row">
            <div class="col-lg-5 col-sm-6">
                <h2 class="section-heading">What we do</h2>
                <hr class="section-heading-spacer">
                <div class="clearfix"></div>
                <h3>Be aware of you are breathing in!</h3>
                <p class="lead">SmartAir is sensors that tracks down the quality of your air condition.</p>
            </div>
            <div class="col-lg-5 col-lg-offset-2 col-sm-6">
                <img class="img-responsive" src="/resources/images/lamp_small.png" alt="">
            </div>
        </div>

    </div>
    <!-- /.container -->

</div>
<!-- /.content-section-a -->

<div class="content-section-b">

    <div class="container">

        <div class="row">
            <div class="col-lg-5 col-lg-offset-1 col-sm-push-6  col-sm-6">
                <h2 class="section-heading">Why should we care?</h2>
                <hr class="section-heading-spacer">
                <div class="clearfix"></div>
                <h3>Reasons :</h3>
                <p class="lead">reason1</p>
                <p class="lead">reason2</p>
                <p class="lead">reason3</p>
            </div>
            <div class="col-lg-5 col-sm-pull-6  col-sm-6">
                <img class="img-responsive" src="/resources/images/lamp_small.png" alt="">
            </div>
        </div>

    </div>
    <!-- /.container -->

</div>
<!-- /.content-section-b -->

<div class="content-section-a">
    <div class="container">
        <div class="row">
            <div class="col-lg-5 col-sm-6">
                <hr class="section-heading-spacer">
                <div class="clearfix"></div>
                <h2 class="section-heading"></h2>
                <p class="lead"></p>
            </div>
            <div class="col-lg-5 col-lg-offset-2 col-sm-6">
                <img class="img-responsive" src="/resources/images/lamp_small.png" alt="">
            </div>
        </div>
    </div>
    <!-- /.container -->
</div>
<!-- /.content-section-a -->

<a  name="contact"></a>
<div class="banner">
    <div class="container">
        <div class="row">
            <div class="col-lg-6">
                <h2>Connect to Start Bootstrap:</h2>
            </div>
            <div class="col-lg-6">
                <ul class="list-inline banner-social-buttons">
                    <li>
                        <a href="https://twitter.com/SBootstrap" class="btn btn-default btn-lg"><i class="fa fa-twitter fa-fw"></i> <span class="network-name">Twitter</span></a>
                    </li>
                    <li>
                        <a href="https://github.com/IronSummitMedia/startbootstrap" class="btn btn-default btn-lg"><i class="fa fa-github fa-fw"></i> <span class="network-name">Github</span></a>
                    </li>
                    <li>
                        <a href="#" class="btn btn-default btn-lg"><i class="fa fa-linkedin fa-fw"></i> <span class="network-name">Linkedin</span></a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <!-- /.container -->
</div>
<!-- /.banner -->
<!-- jQuery -->
<script src="js/jquery.js"></script>
<!-- Bootstrap Core JavaScript -->
<script src="js/bootstrap.min.js"></script>
</body>
<@layout.footer />
</html>
