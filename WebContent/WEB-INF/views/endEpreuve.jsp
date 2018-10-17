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
			<div class="row">
				
				<c:forEach items="${ resultats }" var="resultat">
				
					<br/> ${ resultat.key.getEnonce() }  ->   ${ resultat.value }
				
				</c:forEach>
				
			</div>
		</div>
		
		<%@include file="include/footer.jsp" %>
	</div>
	
	<%@include file="include/scripts.jsp" %>
</body>