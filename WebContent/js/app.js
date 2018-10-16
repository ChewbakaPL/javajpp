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
	
	//let realArray = [];
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
	
	//realArray["nbQuestion"] = realArray.length;
	realObject["nbQuestion"] = Object.keys(realObject).length
	
	
	console.log("realObject:");
	console.log(realObject);
	
	
	//JSON FAIL
	//let jsonString = JSON.stringify(formData);
	//let jsonString = JSON.stringify(realArray);
	//console.log("jsonString:");
	//console.log(jsonString);
	  
	//let dataToSend = {json:jsonString},
	  
	  
	let dataToSend = realObject;
	
	
	$.ajax({
	  type: "POST",
	  url: url,
	  data: dataToSend,
	  success: function(){
		  console.log("AJAX SUCESS");
		  console.log(postData);
	  }
	});
}

