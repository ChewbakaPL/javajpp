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
	<%@include file="include/header.jsp" %>

	<div class="page-wrapper">
		<div class="page-content container-fluid">
		    <div class="row">
		    	<div class="col-12 text-center">
		        	<h1 class="mb-4">Validateur de compétence</h1>
		        </div>
		        <div class="col-md-4 offset-md-4">
		        	<form action="processLogin" method="POST">
		        		<input name="email" type="text" class="form-control"  placeholder="Utilisateur" required>
		        		<input name="password" type="password" class="form-control"  placeholder="Mot de passe" required>
		        		<button class="btn btn-primary" type="submit">Connexion</button>
		            </form>
		        </div>
		    </div>
		</div>
		<%@include file="include/footer.jsp" %>
	</div>
	
	<%@include file="include/scripts.jsp" %>
</body>