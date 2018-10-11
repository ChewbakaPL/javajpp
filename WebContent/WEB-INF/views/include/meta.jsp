<%@ page language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="author" content="sebeez">
<meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />

<title>QCM</title>

<% Integer noCacheMeta = (int) (Math.random() * (99999 - 9999)); %>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/toastr.min.css" rel="stylesheet">
<link href="css/app.css?noCache=<%=noCacheMeta%>" rel="stylesheet">