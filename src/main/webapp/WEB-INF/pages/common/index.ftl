<#import "../layout.ftl" as layout/>
<!DOCTYPE html>
<html>
<@layout.header "SmartAir - make your indoor life better!">
<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
<#--<meta name="google-site-verification" content="-2u6NzbFCW-LpLIE6tLmyjfpPfWbOmCe9nP-wS2hHj0" />-->

<!-- Custom Fonts -->
<#--<link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>-->
<#--<link href='http://fonts.googleapis.com/css?family=Merriweather:400,300,300italic,400italic,700,700italic,900,900italic' rel='stylesheet' type='text/css'>-->

<#--
<link rel="stylesheet" href="font-awesome/styles/css/font-awesome.min.css" type="text/css">


<!-- Plugin CSS &ndash;&gt;
<link rel="stylesheet" href="resources/index/styles/css/animate.min.css" type="text/css">

<!-- Custom CSS &ndash;&gt;
<link rel="stylesheet" href="resources/index/styles/css/creative.css" type="text/css">

<!-- My CSS &ndash;&gt;
<link rel="stylesheet" href="resources/index/styles/css/stylesheetOverBootstrap2.css" type="text/css">
-->

<#--<link rel="stylesheet" href="resources/index/styles/styles.css" type="text/css">-->

<!-- inject:css -->
<link rel="stylesheet" href="../../../resources/index/styles/styles.css">
<!-- endinject -->

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->

</@layout.header>
<body id="page-top">
<#--<@layout.common_menu/>-->
<nav id="mainNav" class="navbar navbar-default navbar-fixed-top">
    <div class="container container-fluid" style="width: 100%; background-color: #fff">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="page-scroll" href="#page-top"><img src="resources/images/common/leave.png" height="50px" alt="logo" border="0"><img src="resources/images/common/logo.png" height="50px" alt="logo" border="0"></a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav navbar-left">
            <#--<div id="auth" class="menu-text-style">-->
                <li>
                    <a class="page-scroll" href="#about">About</a>
                </li>
                <li>
                    <a class="page-scroll" href="#info">Motivation</a>
                </li>
                <li>
                    <a class="page-scroll" href="#order">Order</a>
                </li>
                <li>
                    <a class="page-scroll" href="#contact">Contacts</a>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <a href="signup">Sign up</a>
                </li>
                <li>
                    <a href="login">Log in</a>
                </li>
            <#--</div>-->
            </ul>
        </div>
    <#--<div id="navbar" class="navbar-right">-->
    <#--<div id="auth" class="menu-text-style">-->
    <#--<a href="signup">Sign up</a>-->
    <#--<a href="login">Log in</a>-->
    <#--</div>-->
    <#--</div>-->
    </div>
</nav>

<header>
    <div class="header-content">
        <div class="header-content-inner">
            <h1>Make your indoor air CO<sub>2</sub> free</h1>

            <p>Service that helps you to improve your air quality</p>
            <a href="#about" class="page-scroll header-btn white-btn">Find Out More</a><a href="#order" class="page-scroll header-btn order-button green-btn">Order</a>
            <div class="share">
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
        </div>
    </div>
</header>

<section class="bg-primary" id="about">
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-lg-offset-2 text-center">
                <h2 class="section-heading">What is SmartAir?</h2>
                <hr class="light">
                <p class="text-faded">SmartAir is a system of sensors and cloud-based platform that tracks your air quality and improves your indoor air quality by providing you with data about what you are breeting in and taking control of your Smart Home devices like ventilation systems and conditioners.</p>
                <#--<script height="352px" width="540px" src="http://player.ooyala.com/iframe.js#ec=05NHZycTqiSuUIE3KIded0oHhfByfvQA&pbid=59b4de92e6b44145b5b692f41dd00d0a"></script>-->
                <img src="/resources/index/img/movie_icon_small.png">
                <br>
                <p class="text-faded">Movie coming soon...</p>
                <br>
                <iframe width="560" height="315" src="https://www.youtube.com/embed/gtJn5pY4xb4" frameborder="0" allowfullscreen></iframe>
                <#--<div class="alert alert-success alert-dismissible" role="alert">-->
                    <#--<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>-->
                    <#--<strong>T</strong> Better check yourself, you're not looking too good.-->
                <#--</div>-->
                <div class="row">
                    <div class="col-lg-8 col-lg-offset-2 text-center">
                            <div class="input-group input-group-lg " >
                                <input class="form-control" type="email" id="subscribe_email" style="color : #000"  name="email" placeholder="Enter your email">
                                <span class="input-group-btn">
                                    <button class="btn btn-default btn-info" onclick="subscribe()">Subscribe for updates!</button>
                                </span>
                            </div>
                    </div>
                </div>
                <#--<button onclick="subscribe()" id="subscribe_button" class="btn-default btn-xl has-spinner">-->
                    <#--<span id="subscribe_span" class="spinner"><i class="icon-spin icon-refresh"></i></span>-->
                    <#--Subscribe for updates!-->
                <#--</button>-->
                <#--<div class="container">-->
                    <#--<h3>Animated button</h3>-->
                    <#--<button class="btn btn-lg btn-warning"><span class="glyphicon glyphicon-refresh glyphicon-refresh-animate"></span> Loading...</button>-->
                <#--</div>-->
            </div>
        </div>
    </div>

    <#--<div class="row">-->
        <#--<div class="col-md-10">-->
            <#--<input type="email" class="form-control" name="email" placeholder="Enter your email">-->
        <#--</div>-->
        <#--<div class="col-md-10">-->
            <#--<a href="#" class="btn btn-default btn-xl">Subscribe for updates!</a>-->
        <#--</div>-->
    <#--</div>-->
</section>

<section id="info">
    <div class="container">
        <div class="row">
            <div class="col-lg-12 text-center">
                <h2 class="section-heading">Why should you care?</h2>
                <hr class="primary">
            </div>
        </div>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-lg-offset-2 text-center">
                <p class="text-muted">You are what you are breathing in! <br>High CO<sub>2</sub> level can cause:</p>
            </div>
        </div>
        <div class="row">

            <div class="col-lg-4 col-md-8 text-center">
                <div class="service-box">
                    <i class="fa fa-4x fa-diamond wow bounceIn text-primary"></i>
                    <h3>Health</h3>
                    <p class="text-muted">CO<sub>2</sub> can cause a headache.</p>
                </div>
            </div>
            <div class="col-lg-4 col-md-8 text-center">
                <div class="service-box">
                    <i class="fa fa-4x fa-paper-plane wow bounceIn text-primary" data-wow-delay=".1s"></i>
                    <h3>Work</h3>
                    <p class="text-muted">It decreases your productivity.</p>
                </div>
            </div>
            <div class="col-lg-4 col-md-8 text-center">
                <div class="service-box">
                    <i class="fa fa-4x fa-newspaper-o wow bounceIn text-primary" data-wow-delay=".2s"></i>
                    <h3>Mood</h3>
                    <p class="text-muted">You start feeling exhausted</p>
                </div>
            </div>
    </div>
</section>

<section class="no-padding" id="order">
    <div class="container">
        <div class="row">
            <div class="col-lg-12 text-center">
                <h2 class="section-heading">Why should you use our service?</h2>
                <hr class="primary">
            </div>
        </div>
    </div>
    <div class="container-fluid">
        <div class="row no-gutter">
            <div class="col-lg-4 col-sm-6">
                <a href="/user/statistic" class="portfolio-box">
                    <img src="resources/images/smartcube.jpg" class="img-responsive" alt="">
                    <div class="portfolio-box-caption">
                        <div class="portfolio-box-caption-content">
                            <div class="project-category text-faded">
                                Chart Statistic
                            </div>
                            <div class="project-name">
                                See your air quality data in easy way
                            </div>
                        </div>
                    </div>
                </a>
            </div>
            <div class="col-lg-4 col-sm-6">
                <a href="/user" class="portfolio-box">
                    <img src="resources/images/smartcube.jpg" class="img-responsive" alt="">
                    <div class="portfolio-box-caption">
                        <div class="portfolio-box-caption-content">
                            <div class="project-category text-faded">
                               Managing your SmartHome devices
                            </div>
                            <div class="project-name">
                                Let your home take care of your air!
                            </div>
                        </div>
                    </div>
                </a>
            </div>
            <div class="col-lg-4 col-sm-6">
                <a href="/login" class="portfolio-box">
                    <img src="resources/images/smartcube.jpg" class="img-responsive" alt="">
                    <div class="portfolio-box-caption">
                        <div class="portfolio-box-caption-content">
                            <div class="project-category text-faded">
                                Devices
                            </div>
                            <div class="project-name">
                                Easy to manage
                            </div>
                        </div>
                    </div>
                </a>
            </div>
        </div>
    </div>
</section>

<aside class="bg-primary">
    <div class="container text-center">
        <div class="call-to-action">
            <h2>Start using SmartAir device right now!</h2>
            <a href="/order" class="btn btn-lg btn-success btn-xl wow tada">Order SmartAir</a>
        </div>
    </div>
</aside>

<section id="contact">
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-lg-offset-2 text-center">
                <h2 class="section-heading">Let's Get In Touch!</h2>
                <hr class="primary">
                <p>We are open to contact about any suggestions or questions!</p>
            </div>
            <div class="col-lg-4 col-lg-offset-2 text-center">
                <i class="fa fa-phone fa-3x wow bounceIn"></i>
                <p>(098) 821-20-78 Maksym</p>
                <p>(097) 588-62-77 Dmitry</p>
                <p>(093) 745-97-99 Denys</p>
            </div>
            <div class="col-lg-4 text-center">
                <i class="fa fa-envelope-o fa-3x wow bounceIn" data-wow-delay=".1s"></i>
                <p><a href="mailto:smartair.team@gmail.com">smartair.team@gmail.com</a></p>
            </div>
        </div>
    </div>
</section>



<!-- jQuery -->
<script src="resources/index/js/jquery.js"></script>
<script src="resources/index/js/index_page.js"></script>
<!-- Bootstrap Core JavaScript -->
<script src="resources/index/js/bootstrap.min.js"></script>

<!-- Plugin JavaScript -->
<script src="resources/index/js/jquery.easing.min.js"></script>
<script src="resources/index/js/jquery.fittext.js"></script>
<script src="resources/index/js/wow.min.js"></script>

<!-- Custom Theme JavaScript -->
<script src="resources/index/js/creative.js"></script>
</body>
<@layout.footer />
</html>
