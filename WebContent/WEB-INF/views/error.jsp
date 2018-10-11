<%@ page language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isErrorPage="true"
    import="java.io.*" 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<!-- meta -->
		<%@include file="include/meta.jsp" %>
	</head>
	<body>
		<!-- header -->
		<%@include file="include/header.jsp" %>
		
		<!-- content -->
	    <div id="content">
	    	<div class="container">
				<h1>Erreur:</h1>
				<%=request.getAttribute("java.servlet.error.message")%>
				
				StackTrace:
				<%
					StringWriter stringWriter = new StringWriter();
					PrintWriter printWriter = new PrintWriter(stringWriter);
					exception.printStackTrace(printWriter);
					out.println(stringWriter);
					printWriter.close();
					stringWriter.close();
				%>
				
				<div style="display:none">
				 	exception.getMessage()
					request.getAttribute("java.servlet.error.message")
				
					selon exception.getCode()
					accessDenied <h1>Erreur 403: Erreur d'accès</h1>
					pageNotFound <h1>Erreur 404: Page n'existe pas</h1>
					technicalError <h1>Erreur 500: erreurs techniques</h1>
					validationError <h1>Vérifiez ce que vous faites !</h1>
				</div>
				
				
				<a href="./">retour</a>
			</div>
		</div>
		
		<!-- footer -->
		<%@include file="include/footer.jsp" %>
	</body>
</html>