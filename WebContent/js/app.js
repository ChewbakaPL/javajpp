$(document).ready(function (){
	$("#question_num_1").css('display', 'contents');
	$('.form_num_1').css('display', 'contents');
	$("span:contains('1')").addClass('active');
	let nbSpans = $(".box_question span").length;
	$('.previous').click(function (){
		let previousIdQuestionTirage = Number($(".box_question span.active:first").text());
		saveForm(previousIdQuestionTirage);
		$('#saveEpreuve').hide();
		let newIdQuestionTirage = previousIdQuestionTirage - 1;
		toggleForm(newIdQuestionTirage);
		activeQuestionSpan(newIdQuestionTirage);
		if (newIdQuestionTirage == "1"){
			$('.previous').hide();
			$('.next').show();
		} else {
			$('.previous').show();
		}
	});
	$('.next').click(function (){
		let previousIdQuestionTirage = Number($(".box_question span.active:first").text());
		saveForm(previousIdQuestionTirage);
		$('.previous').css('display', 'inline-block');
		let newIdQuestionTirage = previousIdQuestionTirage + 1;
		toggleForm(newIdQuestionTirage);
		activeQuestionSpan(newIdQuestionTirage);
		if (newIdQuestionTirage == nbSpans){
			$('.next').hide();
			$('#saveEpreuve').show();
		} else {
			$('.next').show();
			$('#saveEpreuve').hide();
		}
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
			  // si il y a pas de reponse de coché
			  if (formData.length <= 2){
				  unColorQuestionBox(idQuestionTirage);
			  } else {
				  colorQuestionBox(idQuestionTirage);
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
