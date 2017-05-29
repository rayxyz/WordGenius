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
<link href="static/css/app/index.css" rel="stylesheet">

<link href="static/css/app/login.css" rel="stylesheet">

</head>

<body data-gr-c-s-loaded="true">

<div class="app-masthead">
  <div class="container">
    <nav class="app-nav">
      <div style="float:left;">
	      <a class="app-nav-item active" href="#">主页</a>
	      <a class="app-nav-item" href="#">关于</a>
      </div>
      <div style="float:right">
	      <#if user??>
		    <a class="app-nav-item" href="#">你好！${(user.name)!}
		    	<#if user.avartarId?? && user.avartarId != "">
		    		&nbsp;<img src="${(fileserver)!}get/${(user.avartarId)!}" style="width:25px;height:20px"/>
		    	</#if>
		    </a>
		    <a id="logout-btn" class="app-nav-item" href="#">退出</a>
		  	<#else>
		      <a id="login-btn" class="app-nav-item" href="#">登录</a>
		      <a id="signup-btn" class="app-nav-item" href="#">注册</a>
	      </#if>
	  </div>
    </nav>
  </div>
</div>

<div class="container">

  <div class="app-header">
    <h1 class="app-title">Rangwang's Tech Site</h1>
    <p class="lead app-description">本站点现在只用于开发实验</p>
  </div>
  
  <div class="row panel panel-info" style="width:45%">
  	<div class="panel-heading">通知：</div>
    <div class="panel-body">
  		<span>系统正在开发中...</span>
    </div>
  </div>

  <div class="row">
    <div class="col-sm-8 app-main">
      <div class="app-post">
        Content here.
      </div><!-- /.app-post -->
    </div><!-- /.app-main -->
  </div><!-- /.row -->

</div><!-- /.container -->

<footer class="app-footer">
  <p>Powered by <a href="https://raywangblog.wordpress.com" target="_blank">@Ray-Wang</a>.</p>
  <p>
    <a href="#">返回顶部</a>
  </p>
</footer>


<!-- JavaScripts-->
<script src="static/js/lib/jquery-3.2.0.min.js"></script>
<script src="static/bootstrap/js/bootstrap.min.js"></script>
<script src="static/js/module/app/index.js"></script>
<script src="static/js/lib/jquery.form.js"></script>
<script src="static/js/lib/bootbox.min.js"></script>
<script data-main="static/js/main" src="static/js/require.js"></script>
<script>
window.sessionStorage.setItem("fileserver", "${(fileserver)!}");
</script>
</body>
</html>