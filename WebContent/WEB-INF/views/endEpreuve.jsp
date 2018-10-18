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
		<jsp:param name="title" value="Resultats ${ epreuve.test.libelle } : ${ epreuve.test.description }"/>
    </jsp:include>

	<div class="page-wrapper">
		<div class="page-content container-fluid">
			<div class="container">
			<c:if test="${total > epreuve.test.seuilHaut }">
				<h1 class="text-center text-success"> TU AS REUSSI!</h1>
			</c:if>
			<c:if test="${total >= epreuve.test.seuilBas && total <= epreuve.test.seuilHaut }">
				<h1 class="text-center text-warning"> TU AS PRESQUE REUSSI!</h1>
			</c:if>
			<c:if test="${total < epreuve.test.seuilBas }">
				<h1 class="text-center text-danger"> TU N'AS PAS REUSSI!</h1>
			</c:if>
			<table class="table table-bordered text-center">
				<thead>
					<tr>
						<th> Question</th>
						<th> Nombre de points</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${ resultats }" var="resultat">
					<c:if test="${ resultat.value }">
						<tr class="bg-success">
							<td>${ resultat.key.getEnonce() }</td>
							<td>${ resultat.key.getPoints() }</td>
						</tr>
					</c:if>
					<c:if test="${ !resultat.value }">
						<tr class="bg-danger">
							<td>${ resultat.key.getEnonce() }</td>
							<td>${ resultat.key.getPoints() }</td>
						</tr>
					</c:if>
				</c:forEach>
				</tbody>
			</table>
			<h3> Points obtenu: <b>${total}</b> / ${ totalEpreuve }</h3>
			</div>
		</div>
		
		<%@include file="include/footer.jsp" %>
	</div>
	
	<%@include file="include/scripts.jsp" %>
</body>