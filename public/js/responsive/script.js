$(document).ready(function() {
	
	$('.menu-nav a').live('click', function() {
		var thehref = $(this).attr('href');
		if ( thehref == '#home' ) {
			$.scrollTo( 0, 900 );
		} else {
			$.scrollTo( thehref , 900, {offset:-45} );
		}
		return false;
	});
	
	$('body').scrollspy({'offset':50});
    
    $('[rel=tooltip]').tooltip();
    $('.carousel').carousel();
	
});

