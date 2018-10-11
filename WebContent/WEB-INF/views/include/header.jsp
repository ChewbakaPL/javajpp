<%@ page language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	<div class="container">
		<a class="navbar-brand" href="index.html" >QCM ENI</a>
        </div>
</nav>

<!-- Navigation -->
<%

/*
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
  <div class="container">
    <a class="navbar-brand" href="/${applicationScope['appName']}">${applicationScope['appName']}</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarResponsive">
      <ul class="navbar-nav ml-auto">
        <li class="nav-item active">
          <a class="nav-link" href="/${applicationScope['appName']}">Accueil
            <span class="sr-only">(current)</span>
          </a>
        </li>
      </ul>
    </div>
  </div>
</nav>
*/

%>


<!-- flash messages -->
<c:if test="${ !empty sessionScope.error}">
	<div class="alert alert-danger" id="alert-message"> ${sessionScope.error}</div>
</c:if>