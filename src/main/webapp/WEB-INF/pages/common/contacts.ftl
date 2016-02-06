<#import "../layout.ftl" as layout/>
<!DOCTYPE html>
<html>
<@layout.header "SmartAir - Contacts">
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

<div class="container">
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
