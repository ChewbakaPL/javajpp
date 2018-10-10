<!doctype html>
<html lang="fr">
<head>
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
	<script src="js/jquery-3.2.1.min.js"></script>
	<script src="js/popper.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/toastr.min.js"></script>
	<script src="js/app.js?noCache=<%=noCache%>"></script>
	
</head>

<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container">
            <a class="navbar-brand" href="index.html" >QCM ENI</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
              <span class="navbar-toggler-icon"></span>
            </button>
          
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
              
            </div>
        </div>
      </nav>
      
      <style>
  
          img {
              cursor: pointer;
          }
          .center {
              float: none; 
              margin-left: auto;
              margin-right: auto;
          }
          
          .text-center {
              text-align: center;
          }
          
          .page-footer {
              position: absolute;
              bottom: 0;
              text-align: center;
              width: 100%;
          }
      </style>

<div class="container center">

    <div class="row">
    
    	<div class="col-12">
        	<h1 class="mt-5">Acceuil (Utilisateur)</h1>

        	<div class="alert alert-danger" id="alert-message" style="visibility: hidden"></div>
        	<div class="alert alert-info" id="info-message" role="alert" style="visibility: hidden"> </div>
        </div>
        
    </div>
</div>


<footer class="page-footer font-small">
    <div class="footer-copyright text-center py-3">&copy; 2018 Copyright:
      <a href="https://www.eni-ecole.fr"> ENI Ecole</a>
    </div>
  </footer>

</body>