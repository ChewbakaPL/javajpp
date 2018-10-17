$(document).ready(function (){
	$("#question_num_1").css('display', 'contents');
	$('.form_num_1').css('display', 'contents');
	$("span:contains('1')").addClass('active');
	let nbSpans = $(".box_question span").length;
	
	$('.previous').click(function (){
		$('#saveEpreuve').hide();
		let idQuestion = $(this).data('question_tirage');
		idQuestion = idQuestion - 2;
		toggleForm(idQuestion);
		activeQuestionSpan(idQuestion);
		if (idQuestion == "1"){
			$('.previous').hide();
			$('.next').show();
		} else {
			$('.previous').show();
		}
		saveForm(idQuestion);
	});
	
	$('.next').click(function (){
		$('.previous').css('display', 'inline-block');
		let idQuestion = $(this).data('question_tirage');
		idQuestion = idQuestion + 1;
		toggleForm(idQuestion);
		activeQuestionSpan(idQuestion);
		if (idQuestion == nbSpans){
			$('.next').hide();
			$('#saveEpreuve').show();
		} else {
			$('.next').show();
			$('#saveEpreuve').hide();
		}
		saveForm(idQuestion-1);
	});
	
	$('.sendAjax').click(function (){
		let idQuestionTirage = $(this).data("question_tirage");
		saveForm(idQuestionTirage);
	});
	
	$('#saveEpreuve').click(function (){
		saveForm(nbSpans);
	});
	
	function activeQuestionSpan(idQuestion){
		$(".box_question span").each(function(){
			$(this).removeClass(' active');
		});
		$(".box_question span:contains('" + idQuestion + "')").addClass("active");
	}
	
	function saveForm(idQuestionTirage) {
		let formData = $('.form_num_'+ idQuestionTirage).serializeArray();
		$.ajax({
		  type: "POST",
		  url: 'processEpreuve',
		  data: formData,
		  success: function(){
			  if (formData.length > 2){
				  colorQuestionBox(idQuestionTirage);
			  } else {
				  unColorQuestionBox(idQuestionTirage);
			  }
		  }
		});
	}
	
	function toggleForm(idForm){
		$(".form_question").each(function(){
			$(this).css('display', 'none');
		});
		$('.form_num_' + idForm).css('display', 'contents');	
	}
	
	function colorQuestionBox(idQuestion){
		$("span:contains('" + idQuestion + "')").addClass('anwsered');
	}
	function unColorQuestionBox(idQuestion){
		$("span:contains('" + idQuestion + "')").removeClass('anwsered');
	}
});
