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

function sendData(){
	let url = 'processEpreuve';
	let formData = $('#formEpreuve').serializeArray();
	
	let realObject = {};
	Object.entries(formData).forEach(([index, obj]) => {
	  var inputName = obj.name;
	  if(inputName.indexOf("questions") === 0){
		  console.log(inputName);
		  
		  inputName = inputName.replace("questions", "");
		  inputName = inputName.replace("][", ";");
		  inputName = inputName.replace("[", "");
		  inputName = inputName.replace("]", "");
		  
		  var strSplit = inputName.split(";");
		  var idQuestion = strSplit[0];
		  var idPropositionChecked = strSplit[1];
		  
		  if(realObject["question"+idQuestion]){
			  realObject["question"+idQuestion] += ";"+idPropositionChecked;
		  }else{
			  realObject["question"+idQuestion] = idPropositionChecked;
		  }
	  }
	});
	
	realObject["nbQuestion"] = Object.keys(realObject).length
	realObject["idEpreuve"] = $("#formEpreuve").find("input[name='idEpreuve']").val();
		
	console.log("realObject:");
	console.log(realObject);
	
	$.ajax({
	  type: "POST",
	  url: url,
	  data: realObject,
	  success: function(){
		  console.log("AJAX SUCESS");
	  }
	});
}

