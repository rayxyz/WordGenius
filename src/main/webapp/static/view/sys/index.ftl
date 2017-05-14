<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" href="../../favicon.ico">
<title>Word Genius Home</title>

<!-- Bootstrap -->
<link href="static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="static/css/sys/index.css" rel="stylesheet">

</head>

<body data-gr-c-s-loaded="true">

<nav class="navbar navbar-inverse navbar-fixed-top">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">Word Genius 后台管理系统</a>
    </div>
  </div>
</nav>

<div class="container-fluid">
  <div class="row">
    <div class="col-sm-3 col-md-2 sidebar">
      <ul class="nav nav-sidebar">
      	<#if moduleList?? && moduleList?size gt 0>
      		<#list moduleList as module>
  				<#if module?index == 0>
  					<li id="${(module.code)!}-mgmt-btn" class="active" module-code="${(module.code)!}"><a href="#">${(module.name)!}</a></li>
  					<#else>
  	        			<li id="${(module.code)!}-mgmt-btn" module-code="${(module.code)!}"><a href="#">${(module.name)!}</a></li>
  				</#if>
      		</#list>
      	</#if>
      </ul>
    </div>
    <div id="sys-index-main-area" class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
      
    </div>
  </div>
</div>

<script src="static/js/lib/jquery-3.2.0.min.js"></script>
<script src="static/bootstrap/js/bootstrap.min.js"></script>
<script src="static/js/module/sys/index.js"></script>
<script src="static/js/lib/bootbox.min.js"></script>
<script data-main="static/js/main" src="static/js/require.js"></script>

</body>
</html>