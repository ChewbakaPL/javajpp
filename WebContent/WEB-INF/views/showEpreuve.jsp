<%@ page language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
	isErrorPage="false"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!doctype html>
<html lang="fr">
<head>
	 <%@include file="include/meta.jsp" %>
</head>

<body>
	<%@include file="include/menu.jsp" %>
	<jsp:include page="/WEB-INF/views/include/header.jsp">
		<jsp:param name="title" value="${ epreuve.test.libelle } : ${ epreuve.test.description }"/>
    </jsp:include>

	<div class="page-wrapper">
		<div class="page-content container-fluid">
			<div class="row">
				<div class="list-group col-2">
				  <a id="btnLogout" href="${pageContext.request.contextPath }/disconnect" class="list-group-item list-group-item-action"> Deconnecter </a>
				</div>
				<div class="col-md-8 col-md-offset-2">
					<div class="mainBox">
						
						<div class="row">
							<div id="clockdiv">
							  <div>
							    <span class="hours"></span>
							    <div class="smalltext">Heures</div>
							  </div>
							  <div>
							    <span class="minutes"></span>
							    <div class="smalltext">Minutes</div>
							  </div>
							  <div>
							    <span class="seconds"></span>
							    <div class="smalltext">Secondes</div>
							  </div>
							</div>
						</div>
					
						<div class="row">
							<div class="box_question">
								<c:forEach items="${ questionTirages }" var="questionTirage">
									<span data-question_tirage="${ questionTirage.getIdQuestionTirage() }">${ questionTirage.getNumOrdre() }</span>
								</c:forEach>
							</div>
						</div>
						<div class="container">
						<c:forEach items="${ questionTirages }" var="questionTirage">
							<form class="form_question form_num_${ questionTirage.getNumOrdre() }" id="form_question_${ questionTirage.getIdQuestionTirage() }" action="processEpreuve" method="POST">
								<input type="hidden" name="idQuestionTirage" value="${ questionTirage.getIdQuestionTirage() }"/>
								<input type="hidden" name="idQuestion" value="${ questionTirage.getQuestion().getIdQuestion() }"/>
								<h2>Question n° ${ questionTirage.getNumOrdre() } - ${ questionTirage.getQuestion().getTheme().getLibelle() }</h2>
								<div class="row">									
									<p>${ questionTirage.getQuestion().getEnonce() }</p>
								</div>
								<div class="row">	
									<ul>
										<c:forEach items="${ questionTirage.getQuestion().getPropositions() }" var="proposition">
											<li>
												<label>
													<input type="checkbox" name="reponseUtilisateur" value="${ proposition.getIdProposition() }" />
													${ proposition.getEnonce() }
												</label>
											</li>
										</c:forEach>
									</ul>
								</div>
								<button type="button" data-question_tirage="${ questionTirage.getIdQuestionTirage() }" class="btn btn-primary previous"><i class="fa fa-arrow-left"></i> Précédent</button>
								<button type="button" data-question_tirage="${ questionTirage.getIdQuestionTirage() }" class="btn btn-primary next">Suivant <i class="fa fa-arrow-right"></i> </button>
							</form>
						</c:forEach>
						<a id="saveEpreuve" class="btn btn-secondary" href="#"> Terminer</a>
					</div>
					</div>
				</div>
			</div>

		</div>
		
		<%@include file="include/footer.jsp" %>
	</div>
	
	<script>
		/***** START CLOCK TIMER *******/
		function getTimeRemaining(endtime) {
			var t = Date.parse(endtime) - Date.parse(new Date());
			var seconds = Math.floor((t / 1000) % 60);
			var minutes = Math.floor((t / 1000 / 60) % 60);
			var hours = Math.floor((t / (1000 * 60 * 60)) % 24);
			return {
				'total': t,
				'hours': hours,
				'minutes': minutes,
				'seconds': seconds
			};
		}
		function initializeClock(id, endtime) {
			var clock = document.getElementById(id);
			var hoursSpan = clock.querySelector('.hours');
			var minutesSpan = clock.querySelector('.minutes');
			var secondsSpan = clock.querySelector('.seconds');
	
			function updateClock() {
				var t = getTimeRemaining(endtime);
				hoursSpan.innerHTML = ('0' + t.hours).slice(-2);
				minutesSpan.innerHTML = ('0' + t.minutes).slice(-2);
				secondsSpan.innerHTML = ('0' + t.seconds).slice(-2);
				if (t.total <= 0) {
					clearInterval(timeinterval);
				}
			}
	
			updateClock();
			var timeinterval = setInterval(updateClock, 1000);
		}
		var deadline = new Date(Date.parse(new Date()) + ${ epreuve.test.duree }*60*1000 );
		initializeClock('clockdiv', deadline);
		/***** END CLOCK TIMER *******/
	</script>
	<%@include file="include/scripts.jsp" %>
</body>