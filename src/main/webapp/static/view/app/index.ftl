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
		    <a class="app-nav-item" href="#">你好！${(user.name)!}</a>
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
        <h2 class="app-post-title">Linux下FTP服务器搭建</h2>
        <div>
        <div style="text-align:center">
        <span>&nbsp;</span></span>
        <span>&nbsp;</span></div>
        <span>1. 所需工具</span><br/>
        &nbsp;&nbsp; &nbsp; 操作系统: ubuntu 16.04<br/>
        &nbsp; &nbsp; &nbsp;FTP服务器: vsftpd<br/>
        &nbsp; &nbsp; &nbsp;FTP常规模式（Normal mode): xinetd<br/>
        2. 安装<br/>
        &nbsp; &nbsp; 安装vsftpd: sudo apt-get install vsftpd<br/>
        &nbsp;&nbsp;&nbsp;&nbsp;<img alt="图片" src="http://b385.photo.store.qq.com/psb?/V11dEA6U3qTHQy/5llYPoYx5a66Kt7qON8oaE2sHXuFJnn780.M7Z9zMss!/b/dIEBAAAAAAAA&amp;bo=GwOvAQAAAAADEIM!" style="width:795px;height:431px"><br/>
        &nbsp; &nbsp; 安装完成后，启动下，看是否成功：<br/>
        &nbsp;&nbsp;&nbsp;&nbsp;<img alt="图片" src="http://b365.photo.store.qq.com/psb?/V11dEA6U3qTHQy/Uny.7YDfHfXlP74U9izwN*bUMnYVvRj.Bjiy7YMSua0!/b/dG0BAAAAAAAA&amp;bo=HQOlAQAAAAADEI8!" style="width:797px;height:421px"><br/>
        &nbsp; &nbsp; 如上图，启动没问题，说明已成功安装vsftpd。<br/>
        &nbsp; &nbsp; 安装xinetd: sudo apt-get install xinetd<br/>
        &nbsp;&nbsp;&nbsp;&nbsp;<img alt="图片" src="http://b365.photo.store.qq.com/psb?/V11dEA6U3qTHQy/tbxL3E0URxZ6Ez7sf6Px3kZJownrtT.PN0H3Erd08Hc!/b/dG0BAAAAAAAA&amp;bo=GgOwAQAAAAADEJ0!" style="width:794px;height:432px"><br/>
        &nbsp; &nbsp; 因为我已经安装过了，所以不会再去下载安装。<br/>
        3. 配置<br/>
        &nbsp; &nbsp; 3.1 vsftpd的访问配置：<br/>
        &nbsp; &nbsp; &nbsp;用vim打开vsftpd的配置文件：sudo vim /etc/vsftpd.conf<br/>
        &nbsp;&nbsp;&nbsp;&nbsp;<img alt="图片" src="http://r.photo.store.qq.com/psb?/V11dEA6U3qTHQy/AIOA1xrtUxjcLmeNt*e*ECWc9PhiNBeZXxfl22jqvlI!/o/dG0BAAAAAAAA&amp;bo=GwOsARsDrAEDEDU!" style="width:795px;height:428px"><br/>
        &nbsp; &nbsp; 按i键修改配置：<br/>
        &nbsp;&nbsp;&nbsp;&nbsp;<img alt="图片" src="http://r.photo.store.qq.com/psb?/V11dEA6U3qTHQy/ZtCjB5NiNk2sIPUTuG9PHldhW3P38Zsv32qRdErWPTw!/o/dG0BAAAAAAAA&amp;bo=GwOtARsDrQEDEDU!" style="width:795px;height:429px"><br/>
        &nbsp; &nbsp; 将 listen=YES 和 listen_ipv6=YES 改为 NO，因为我们将用xinetd来管理vsftpd。<br/>
        &nbsp; &nbsp; anonymous_enable=YES 即是否允许匿名访问。<br/>
        &nbsp; &nbsp; 这里主要注意 write_enable，默认是注释掉的并且值为NO。这里代打开注释，将值设为YES，表示可以允许授权用户进行写操作。write_enable值设为YES后，我们就可以用FTP客户端传输文件到FTP服务器了。<br/>
        <span>&nbsp;&nbsp;&nbsp;&nbsp;3.3 xinetd的配置：<br/>
        &nbsp; &nbsp; 在/etc文件夹下创建文件夹xinetd.d<br/>
        &nbsp; &nbsp; sudo mkdir xinetd.d&nbsp; &nbsp;<br/>
        <span>&nbsp; &nbsp; 跳到/etc/xinetd.d/下创建文件vsftpd<br/>
        <span>&nbsp; &nbsp; vim vsftpd<br/>
        &nbsp; &nbsp; 然后加入以下内容：<br/>
        &nbsp;&nbsp;&nbsp;&nbsp;<img alt="图片" src="http://r.photo.store.qq.com/psb?/V11dEA6U3qTHQy/YaxxJZpg1nO.byY7.DsDk*XcbNiHH8RADnDKhFN5l3w!/o/dGoBAAAAAAAA&amp;bo=kAIDAZACAwEDEDU!" style="width:656px;height:259px">&nbsp;<br/>
        <span>&nbsp; &nbsp; 其中no_access是要阻塞的主机，根据实际情况设置。<br/>
        <span>&nbsp; &nbsp; 设置好后，按Esc键，键入:wq退出vim。<br/>
        &nbsp; &nbsp; 停止vstpd服务：<br/>
        &nbsp; &nbsp; sudo service vsftpd stop<br/>
        &nbsp;&nbsp;&nbsp;&nbsp;<img alt="图片" src="http://r.photo.store.qq.com/psb?/V11dEA6U3qTHQy/8mznMq8DPLjRvD*14kX0qldx0CJoehXNG1mvZ6n6xHw!/o/dIQBAAAAAAAA&amp;bo=6QE5AOkBOQADEDU!" style="width:489px;height:57px">&nbsp;<br/>
        <span>&nbsp; &nbsp; 现在我们就可一启动FTP服务器的Normal模式：<br/>
        &nbsp;&nbsp;&nbsp;&nbsp;<img alt="图片" src="http://r.photo.store.qq.com/psb?/V11dEA6U3qTHQy/SDZJ9b0rg2.kZ2xviIlDQtUbQ9V82mkCVRH9MvxgGL0!/o/dG4BAAAAAAAA&amp;bo=cwJLAHMCSwADEDU!" style="width:627px;height:75px">&nbsp;<br/>
        <span>&nbsp;&nbsp;&nbsp;</span></span></span></span></span></span></span></div>

        <div><span>4. 文件传输前的权限配置<br/>
        &nbsp; &nbsp; 在远程服务器上创建文件夹（这里是file）:<br/>
        &nbsp;&nbsp;&nbsp;&nbsp;<img alt="图片" src="http://r.photo.store.qq.com/psb?/V11dEA6U3qTHQy/Xo4X*VRVWN*ldptmHfZ3SZSZrrY.Mn*Hh06xOX3M6lA!/o/dGwBAAAAAAAA&amp;bo=5AKmAOQCpgADEDU!" style="width:740px;height:166px"><br/>
        &nbsp;&nbsp;&nbsp;&nbsp;然后将文件夹的权限付给ftp用户，这里是ubuntu:<br/>
        &nbsp;&nbsp; &nbsp;</span><span style="font-size:13px">sudo chown -R ubuntu file&nbsp;</span><br style="font-size:13px"><span style="font-size:13px">&nbsp; &nbsp; sudo chmod -R 777 file &nbsp; &nbsp;<br/>
        <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img alt="图片" src="http://r.photo.store.qq.com/psb?/V11dEA6U3qTHQy/JeX*9eEH3Zt9iC*wSfxJl0F7cH4bMhioGUnLryuGGBE!/o/dCUAAAAAAAAA&amp;bo=9QLUAPUC1AADEDU!" style="width:757px;height:212px"><br/>
        <span>&nbsp; &nbsp; 到这里，设置基本完成了，接下来检验下能不能传文件，来验证FTP服务器是否搭建成功。&nbsp;</span></span></span><span><br/>
        5. 文件传输<br/>
        &nbsp; &nbsp; 5.1 ftp命令<br/>
        &nbsp; &nbsp; 在本机用ftp命令连接远程FTP服务器：<br/>
        &nbsp;&nbsp;&nbsp;&nbsp;<img alt="图片" src="http://r.photo.store.qq.com/psb?/V11dEA6U3qTHQy/e6HJ3WI7jJ5.ezh4chm1*.2be1f35qN3N5yKX20hSpk!/o/dGwBAAAAAAAA&amp;bo=3AK2AdwCtgEDEDU!" style="width:732px;height:438px"><br/>
        &nbsp; &nbsp; 输入用户名和密码后，我们成功登录了FTP服务器。<br/>
        &nbsp; &nbsp; 用ls命令查看FTP根目录：<br/>
        &nbsp;&nbsp;&nbsp;&nbsp;<img alt="图片" src="http://r.photo.store.qq.com/psb?/V11dEA6U3qTHQy/1HHZrlKdeyY2PwXUYjcUAmcdYvTnm*O3cSjyUJ2yXNw!/o/dIMBAAAAAAAA&amp;bo=3AK2AdwCtgEDEDU!" style="width:732px;height:438px"><br/>
        &nbsp; &nbsp; 我们可以看到我们创建的file文件夹。我们接下来用file文件夹存放要上传的文件。<br/>
        &nbsp; &nbsp; 用put命令上传文件：<br/>
        &nbsp;&nbsp;&nbsp;&nbsp;<img alt="图片" src="http://r.photo.store.qq.com/psb?/V11dEA6U3qTHQy/Qo**wEp35d4NKJcUK6HDOEcImvXtJrYa7EzZ95eam9c!/o/dGwBAAAAAAAA&amp;bo=pAMQAqQDEAIDEDU!" style="width:870px;height:492px"><br/>
        &nbsp; &nbsp;<br/>
        &nbsp; &nbsp; 把本地名为test001.txt的文件成功地上传到了远程服务器上。<br/>
        &nbsp; &nbsp; 用ssh看下文件内容：<br/>
        &nbsp;&nbsp;&nbsp;&nbsp;<img alt="图片" src="http://r.photo.store.qq.com/psb?/V11dEA6U3qTHQy/6c9h7RLDYN63xETC1etBpAeqeHvMyWjTA4eqEe7jkKE!/o/dN8AAAAAAAAA&amp;bo=HQMKAR0DCgEDEDU!" style="width:797px;height:266px"><br/>
        &nbsp; &nbsp; 再看看本地的文件内容：<br/>
        &nbsp;&nbsp;&nbsp;&nbsp;<img alt="图片" src="http://r.photo.store.qq.com/psb?/V11dEA6U3qTHQy/BHSGGOrvy.GYLUnIywH91*wcaetdIgPLF*R006ms18k!/o/dGwBAAAAAAAA&amp;bo=IQNpASEDaQEDEDU!" style="width:801px;height:361px"><br/>
        &nbsp; &nbsp; 两边内容一致，说明FTP服务器可以正常工作了！！！<br/>
         &nbsp;&nbsp;&nbsp;&nbsp;5.2 nautilus文件管理器&nbsp;&nbsp; &nbsp;&nbsp;<br/>
        <span>&nbsp; &nbsp; 我们可以用linux自带的文件管理器nautilus来作为FTP客户端。<br/>
        &nbsp; &nbsp; 用nautilus的connect to server连接远程FTP服务器：<br/>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img alt="图片" src="http://r.photo.store.qq.com/psb?/V11dEA6U3qTHQy/N0sBTpmV7wOmDqzYPawyVapRYfEcr48VA0fE1i*KQAA!/o/dGoBAAAAAAAA&amp;bo=qAFxAagBcQEDEDU!" style="width:424px;height:369px"><br/>
        <span>连接成功后，和用ftp命令一样，打开了ftp的根目录，我们可以查看file文件夹下刚刚上传上去的文件：<br/>
        <img alt="图片" src="http://r.photo.store.qq.com/psb?/V11dEA6U3qTHQy/EpFE5oW6Z.mD0KRwF7uNXb31Gsjad1lW9L9YWL2wG1I!/o/dG0BAAAAAAAA&amp;bo=HwPBAB8DwQADEDU!" style="width:799px;height:193px">&nbsp;<br/>
        <span>用nautilus，就可以像在本地一样管理远程FTP服务器的<span style="font-size:16px"></span>文件了。&nbsp;<br/>
        </span></span></span></span>6. 参考文档<br/>
        <h2 itemprop="name" style="box-sizing:inherit;margin:0px 0px -10px;text-rendering:optimizeLegibility;font-size:1.71rem;color:rgb(51, 51, 51)"><a href="https://linuxconfig.org/how-to-setup-and-use-ftp-server-in-ubuntu-linux" style="font-size:14px" target="_blank" title="https://linuxconfig.org/how-to-setup-and-use-ftp-server-in-ubuntu-linux"><span>How to setup and use FTP Server in Ubuntu Linux<span>&nbsp;</span></span></a></h2><span><a href="https://ubuntuforums.org/showthread.php?t=833829" target="_blank">https://ubuntuforums.org/showthread.php?t=833829</a><br/>
        </span><a href="http://www.tldp.org/HOWTO/FTP-3.html" target="_blank" title="http://www.tldp.org/HOWTO/FTP-3.html">Beginner's guide to using ftp</a><span><br/>
        </span><span>&nbsp;</span></div>
        <br/>
        <br/>
        <br/>
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
<script src="static/js/lib/bootbox.min.js"></script>
<script data-main="static/js/main" src="static/js/require.js"></script>
</body>
</html>