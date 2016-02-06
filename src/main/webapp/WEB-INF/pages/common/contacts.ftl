<#import "../layout.ftl" as layout/>
<!DOCTYPE html>
<html>
<@layout.header "SmartAir - Contacts">

    <!-- inject:css -->
    <link rel="stylesheet" href="../../../resources/index/styles/styles.css">
    <!-- endinject -->
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="http://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7/html5shiv.js"></script>
    <script src="http://cdnjs.cloudflare.com/ajax/libs/respond.js/1.3.0/respond.js"></script>
    <![endif]-->
    <style>
        html { height: 100% }
        body { height: 100%; background-color:#CCC }
        #map-outer {height: 440px; padding: 20px; border: 2px solid #CCC; margin-bottom: 20px; background-color:#FFF }
        #map-container { height: 400px }
        @media all and (max-width: 991px) {
            #map-outer  { height: 650px }
        }
    </style>
</@layout.header>
<body>
<#--<@layout.common_menu />-->

<nav id="mainNav" class="navbar navbar-default navbar-fixed-top">
    <div class="container container-fluid" style="width: 100%; background-color: #fff">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="page-scroll" href="/"><img src="resources/images/common/leave.png" height="50px" alt="logo" border="0"><img src="resources/images/common/logo.png" height="50px" alt="logo" border="0"></a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav navbar-left">
            <#--<div id="auth" class="menu-text-style">-->
                <li>
                    <a class="page-scroll" href="#contacts">Contacts</a>
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
            <h1>Keep you area CO<sub>2</sub> free</h1>

            <p>Service that helps you to keep track of air quality</p>
            <#--<a href="#about" class="page-scroll header-btn white-btn">Find Out More</a><a href="#order" class="page-scroll header-btn order-button green-btn">Order</a>-->
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

<div id="#contacts" class="container">
    <div class="row">
        <div id="map-outer" class="col-md-12">
            <div id="address" class="col-md-4">
                <h2>Contact Information</h2>
                <p>
                    Location: <strong>Ukraine, Dnipropetrovsk</strong><br><br>
                    Students of Software Engineering<br><br>
                    Daria Tatarchenko<br>
                    Amira Sheremet<br>
                    Denys Kovalenko<br>
                    Dmytry Pleshkanovskiy<br>
                    Maxim Sych<br>
                    Dmytry Sovgyr<br>
                </p>
            </div>
            <div id="map-container" class="col-md-8"></div>
        </div><!-- /map-outer -->
    </div> <!-- /row -->
</div>


<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<#--<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>-->
<!-- Include all compiled plugins (below), or include individual files as needed -->
<#--<script src="http://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.0.2/js/bootstrap.min.js"></script>-->
<script src="http://maps.google.com/maps/api/js?sensor=false"></script>
<script>
    function init_map() {
        var myLocation = new google.maps.LatLng(48.458144,35.055871);
        var mapOptions = {
            center: myLocation,
            zoom: 14
        };
        var marker = new google.maps.Marker({
            position: myLocation,
            title:"SmartAir"});
        var map = new google.maps.Map(document.getElementById("map-container"),
                mapOptions);
        marker.setMap(map);
    }
    google.maps.event.addDomListener(window, 'load', init_map);
</script>

<@layout.scripts />

</body>
<@layout.footer />
</html>
