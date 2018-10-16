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
	<%@include file="../include/meta.jsp" %>
</head>

<body>
	<%@include file="../include/menu.jsp" %>
	<jsp:include page="/WEB-INF/views/include/header.jsp">
		<jsp:param name="title" value="Acceuil (Administrateur)"/>
    </jsp:include>
	
	<div class="page-wrapper">
		<div class="page-content">
			<div class="row">
				<div class="list-group col-2">
				  <a href="#" class="list-group-item list-group-item-action"> Deconnecter </a>
				</div>
				<div class="col-8">
					<div class="mainBox">
					fais tout peter wesh administrator
					</div>
				</div>
			</div>
		</div>
		<%@include file="../include/footer.jsp" %>
	</div>
	
	<%@include file="../include/scripts.jsp" %>
</body>