$(document).ready(function(){
	$('.egg_slideshow').EggSlideshow();
	$('.egg_accordion').EggAccordion({width:516});
	$('.egg_imagedropdown').EggImageDropdown();
	$('.egg_preload').EggPreload({complete:function(){alert('This is a demo of the EggPreload plugin - all images are now loaded!');}});
	$(".block").fadeIn("slow");
	$(".block").each(function(){
		$(this).load('/getblock/'+$(this).attr('id'));
	});
	// $.get('/getblock/'+, {
 //        text: $(sourceId).text(),
 //        sourceLang: sourceLang,
 //        destLang: destLang
 //    }).done(function(translated) {
 //        $(destId).text(translated['text'])
 //        $(loadingId).hide();
 //        $(destId).show();
 //    }).fail(function() {
 //        $(destId).text("{{ _('Error: Could not contact server.') }}");
 //        $(loadingId).hide();
 //        $(destId).show();
 //    });
});