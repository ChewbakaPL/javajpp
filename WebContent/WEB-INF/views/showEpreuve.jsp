<%@ page language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    errorPage="error"
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
		<jsp:param name="title" value="Epreuve"/>
    </jsp:include>

	<div class="container">
		<div class="row">
			<div class="list-group col-2">
			  <a href="${pageContext.request.contextPath }/disconnect" class="list-group-item list-group-item-action"> Deconnecter </a>
			</div>
			<div class="col-md-8 col-md-offset-2">
				<div class="mainBox">
					<div class="row">
						<h3>${ epreuve.test.libelle }</h3>
						<p>${ epreuve.test.description } (${ epreuve.test.duree } - minutes) </p>
						<div id="questionsPanel">
							<!-- Boucle pour chaque question -->
							<span class="quetionCarret"></span>
						</div>
					</div> 
					<!-- Boucle pour chaque question -->
					<div class="row">
						<fieldset>
							<legend>question</legend>
							<form action="?id=445">
								<input type="checkbox" value="IDQUESTION"> question
								<input type="checkbox" value="IDQUESTION"> question 
								<input type="checkbox" value="IDQUESTION"> question 
								<input name="questions[1][P1]" type="checkbox" value="IDQUESTION"> question  
							</form>
						</fieldset>
						<input type="submit" value="Envoyer">
					</div>
				</div>
			</div>
		</div>
	</div>

	<%@include file="include/footer.jsp" %>
	<%@include file="include/scripts.jsp" %>
</body>