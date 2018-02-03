// AIzaSyBYWxdp2Hh9_i_7Jew1KhEXe9zMtva_PZo

// 3471 Fifth Ave., Kaufmann Medical Bldg., Suite 1002, Pittsburgh, PA 15213
// (40.440550, -79.960626)

// 201 N Craig St, Pittsburgh, PA 15213
// (40.449913, -79.951201)

// 2000 Mary St., South Side Outpatient Center - First Floor, Pittsburgh, PA 15203
// (40.425592, -79.977756)


function initMap() {

    var place1 = {
        info: '<strong>UPMC Centers for Rehab Services</strong><br>\
                    3471 Fifth Ave., Kaufmann Medical Bldg., Suite 1002, Pittsburgh, PA 15213<br>\
                    <a href="https://goo.gl/maps/Piruqvxvrqu">Get Directions</a>',
        lat: 40.440550,
        long: -79.960626
    };

    var place2 = {
        info: '<strong>NovaCare Rehabilitation</strong><br>\
                    201 N Craig St, Pittsburgh, PA 15213<br>\
                    <a href="https://goo.gl/maps/7PfAtzcSKbA2">Get Directions</a>',
        lat: 40.449913,
        long: -79.951201
    };

    var place3 = {
        info: '<strong>UPMC Centers for Rehab Services South Side </strong><br>\r\
                    2000 Mary St., South Side Outpatient Center - First Floor, Pittsburgh, PA 15203<br>\
                    <a href="https://goo.gl/maps/vYFsY4p2VsA2">Get Directions</a>',
        lat: 40.425592,
        long: -79.977756
    };

    var locations = [
      [place1.info, place1.lat, place1.long, 0],
      [place2.info, place2.lat, place2.long, 1],
      [place3.info, place3.lat, place3.long, 2],
    ];

    var map = new google.maps.Map(document.getElementById('map'), {
        zoom: 13,
        center: new google.maps.LatLng(40.445013, -79.956392),
        mapTypeId: google.maps.MapTypeId.ROADMAP
    });

    var infowindow = new google.maps.InfoWindow({});

    var marker, i;

    for (i = 0; i < locations.length; i++) {
        marker = new google.maps.Marker({
            position: new google.maps.LatLng(locations[i][1], locations[i][2]),
            map: map
        });

        google.maps.event.addListener(marker, 'click', (function (marker, i) {
            return function () {
                infowindow.setContent(locations[i][0]);
                infowindow.open(map, marker);
            }
        })(marker, i));
    }
}