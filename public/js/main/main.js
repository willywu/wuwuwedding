function goToByScroll(id){
    $('html,body').stop().animate({scrollTop: ($("#"+id).offset().top)-=42},{duration: 1500, easing: "easeInOutExpo"});
}
function initialize() {
    var mapOptions = {
      zoom: 17,
      center: new google.maps.LatLng(37.7792, -122.419),
      zoomControl: true,
      mapTypeControl: false
    };

    var map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
}

function loadScript() {
    var script = document.createElement('script');
    script.type = 'text/javascript';
    script.src = 'https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&callback=initialize&key=AIzaSyDpXihQIfDZCUZkAypRBCsqqOYvbRUEwTQ';
    document.body.appendChild(script);
}

window.onload = loadScript;

$.get('photo_section', function(data) {
    $('#photos').append(data)
    $(window).load(function() {
        $('#slider').nivoSlider({
          effect: 'fade'
        });
    });
});
