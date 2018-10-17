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
		<jsp:param name="title" value="${ epreuve.test.libelle } : ${ epreuve.test.description } - (${ epreuve.test.duree } minutes)"/>
    </jsp:include>

	<div class="page-wrapper">
		<div class="page-content">
			<div class="row">
				<div class="list-group col-2">
				  <a href="${pageContext.request.contextPath }/disconnect" class="list-group-item list-group-item-action"> Deconnecter </a>
				</div>
				<div class="col-md-8 col-md-offset-2">
					<div class="mainBox">
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
								<button type="button" data-question_tirage="${ questionTirage.getIdQuestionTirage() }" class="btn btn-primary previous">Précédent</button>
								<button type="button" data-question_tirage="${ questionTirage.getIdQuestionTirage() }" class="btn btn-primary next">Suivant</button>
							</form>
						</c:forEach>
						<a id="saveEpreuve" class="btn btn-secondary" href="javascript:;"> Terminer</a>
					</div>
					</div>
				</div>
			</div>

		</div>
		
		<%@include file="include/footer.jsp" %>
	</div>
	
	<%@include file="include/scripts.jsp" %>
</body>