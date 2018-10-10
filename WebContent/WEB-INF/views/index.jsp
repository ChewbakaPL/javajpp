<!doctype html>
<html lang="fr">
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="s"%>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="author" content="sebeez">
	<meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
	 <title>QCM</title>
	<% Integer noCache = (int) (Math.random() * (99999 - 9999)); %>
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/toastr.min.css" rel="stylesheet">
	<link href="css/app.css?noCache=<%=noCache%>" rel="stylesheet">
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container">
            <a class="navbar-brand" href="index.html" >QCM ENI</a>
        </div>
      </nav>
	<div class="container center">
	    <div class="row">
	    	<div class="col-12">
	        	<h1 class="mt-5">Validateur de compétence</h1>
	        </div>
	        <div class="col-md-4 offset-md-4">
		        <s:if test="${ !empty sessionScope.error}">
		        	<div class="alert alert-danger" id="alert-message"> ${sessionScope.error}</div>
		        </s:if>
	        	<form action="processLogin" method="POST">
	        		<input name="email" type="text" class="form-control"  placeholder="Utilisateur" required>
	        		<input name="password" type="password" class="form-control"  placeholder="Mot de passe" required>
	        		<button class="btn btn-primary" type="submit">Connexion</button>
	            </form>
	        </div>
	    </div>
	</div>
	<footer class="page-footer font-small">
	    <div class="footer-copyright text-center py-3">&copy; 2018 Copyright:
	      <a href="https://www.eni-ecole.fr"> ENI Ecole</a>
	    </div>
	</footer>
	<script src="js/jquery-3.2.1.min.js"></script>
	<script src="js/popper.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/toastr.min.js"></script>
	<script src="js/app.js?noCache=<%=noCache%>"></script>
</body>