$(document).ready(function(){
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
	// $(".block").mouseenter(function(){
	// 	$(this).addClass("hover");
	// });
	// $(".block").mouseleave(function(){
	// 	$(this).removeClass("hover");
	// });
});