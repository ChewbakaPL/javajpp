<%@ page language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- page title -->
<div class="container center">
	<div class="col-md-12">
    	<h1 class="mt-5">${ param.title }</h1>
    	<div class="alert alert-danger" id="alert-message" style="visibility: hidden"></div>
    	<div class="alert alert-info" id="info-message" role="alert" style="visibility: hidden"></div>
    </div>
</div>

<!-- flash messages -->
<c:if test="${ !empty sessionScope.error}">
	<div class="alert alert-danger" id="alert-message"> ${sessionScope.error}</div>
</c:if>
