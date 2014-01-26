function goToByScroll(id){
    $('html,body').stop().animate({scrollTop: ($("#"+id).offset().top)-=50},{duration: 1500, easing: "easeInOutExpo"});
}