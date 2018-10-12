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
		<jsp:param name="title" value="Acceuil (Utilisateur)"/>
    </jsp:include>
	
	<div class="container">
		<div class="row">
			<div class="list-group col-2">
			  <a href="${pageContext.request.contextPath }/disconnect" class="list-group-item list-group-item-action"> Deconnecter </a>
			</div>
			<div class="col-8">
				<div class="mainBox">
					<table class="table table-hover">
						<thead>
							<tr>
								<th>Libelle</th>
								<th>Description</th>
								<th>Duree</th>
								<th>Start</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${tests}" var="test">
								<tr>
									<td>${ test.libelle }</td>
									<td>${ test.description }</td>
									<td>${ test.duree }</td>
									<td><a class="btn btn-primary" href="${pageContext.request.contextPath }/showEpreuve?id=${ test.idTest }"> Demarrer le test</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>

	<%@include file="include/footer.jsp" %>
	<%@include file="include/scripts.jsp" %>
</body>