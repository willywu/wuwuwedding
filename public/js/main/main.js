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

function loadMapScript() {
    var script = document.createElement('script');
    script.type = 'text/javascript';
    script.src = 'https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&callback=initialize&key=AIzaSyDpXihQIfDZCUZkAypRBCsqqOYvbRUEwTQ';
    document.body.appendChild(script);
}

function confirmRsvp() {
    $('#rsvp-success').fadeIn('slow');
}

function runPageLoadedScripts() {
    //loadMapScript();
    $('#rsvp-success-button').click(function() {
        $('#rsvp-success').fadeOut('slow');
    });
    $('#rsvp-error-button').click(function() {
        $('#rsvp-error').fadeOut('slow');
    });
    $('#rsvp-check').submit(function(event) {
       var rsvpCodeObj = $('#rsvp-check').serialize();
       $.get('/guests/check_rsvp?'+rsvpCodeObj, function(data) {
           resp = eval(data)
           if (resp.status==='bad') {
               $('#rsvp-error').fadeIn('slow');
           } else {
               $('#rsvp').animate({
                   height:'460px'
               });
               $('#hidden-rsvp-code').val(resp.guest.rsvpCode);
               $('#rsvp-email').val(resp.guest.email);
               $('#rsvp-phone').val(resp.guest.phoneNumber);
               $('#rsvp-comment-area').val(resp.guest.comments);
               $('#rsvp_container').slideUp(1000, function() {
                   if (resp.guest.hasExtraGuest) {
                       $('#two_guests_container').slideDown(250, function() {
                           $('#extra-rsvp-info-container').slideDown(750);
                       });
                       $('#rsvp-guest-one-name').val(resp.guest.guestOneName);
                       $('#rsvp-guest-two-name').val(resp.guest.guestTwoName);
                   } else {
                       $('#one_guest_container').slideDown(250, function() {
                           $('#extra-rsvp-info-container').slideDown(750);
                       });
                       $('#rsvp-guest-name').val(resp.guest.guestOneName);
                   }
               });
           }
       })
       return false;
    });
    $('#rsvp-update-guests').submit(function(event) {
        var rsvpGuestsObj = $('#rsvp-update-guests').serialize();
        $.post('/guests/update_guests', rsvpGuestsObj, function(data) {
            resp = eval(data)
            if (resp.status==='good') {
                confirmRsvp();
            } else {
                alert("Something unexpected happened.  Please try again.");
            }
        })
        return false;
     });
}

window.onload = runPageLoadedScripts;

$.get('photo_section', function(data) {
    $('#photos').append(data);
    $(window).load(function() {
        $('#slider').nivoSlider({
            effect: 'fade'
        });
    });
});