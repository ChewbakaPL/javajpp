<%@ page language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>

<% Integer noCacheScripts = (int) (Math.random() * (99999 - 9999)); %>
<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/popper.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/toastr.min.js"></script>
<script src="js/app.js?noCache=<%=noCacheScripts%>"></script>
