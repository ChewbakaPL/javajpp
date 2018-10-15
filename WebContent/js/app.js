$(document).ready(function (){
	$("#question_num_1").css('display', 'contents');
	$('.form_num_1').css('display', 'contents');
	$("span:contains('1')").addClass('active');
	$(".box_question span").click(function(){
		var value = $(this).text();
		$(".form_question").each(function(){
			$(this).css('display', 'none');
		});
		$('.form_num_' + value).css('display', 'contents');
		if (!$(this).hasClass("active")){
			$(".box_question span").each(function(){
				$(this).removeClass(' active');
			});
			$(this).addClass("active");
		}
	});
});