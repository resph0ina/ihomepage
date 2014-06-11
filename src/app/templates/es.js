

$(document).ready(function(){
	$('.egg_slideshow').EggSlideshow();
	$('.egg_accordion').EggAccordion({width:516});
	$('.egg_imagedropdown').EggImageDropdown();
	$('.egg_preload').EggPreload({complete:function(){alert('This is a demo of the EggPreload plugin - all images are now loaded!');}});
});