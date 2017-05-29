<div class="modal fade" id="signup-dlg" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">用户注册</h4>
      </div>
      <div class="modal-body">
	      <div class="input-group" style="margin-bottom: 10px;">
		      <span class="input-group-addon"><span style="color:red;">*</span>用户名称:</span>
		      <input id="signup-name" type="text" class="form-control" placeholder="请填写用户名" required="">
		  </div>
	      <div class="input-group" style="margin-bottom: 10px;">
		      <span class="input-group-addon"><span style="color:red;">*</span>电子邮箱:</span>
		      <input id="signup-email" type="email" class="form-control" placeholder="电子邮箱将作为用户账号，保存后将不能更改" required="" />
		  </div>
	      <div class="input-group" style="margin-bottom: 10px;">
		      <span class="input-group-addon">手机号码:</span>
		      <input id="signup-mobilephone" type="text" class="form-control" placeholder="请填写正确的手机号码">
		  </div>
		  <div class="input-group" style="margin-bottom: 10px;">
		      <span class="input-group-addon">出生日期:</span>
		      <input id="signup-birthdate" type="text" class="form-control" placeholder="请填写出生日期;如：1990-09-13">
		  </div>
          <div id="signup-gender-dropdown-list" class="dropdown" style="margin-bottom: 10px;">
			<label>性别:</label>
			<button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
			  <span>请选择性别</span>
			  <span class="caret"></span>
			</button>
			<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
				<li><a href="#" user-gender="male">男</a></li>
				<li><a href="#" user-gender="female">女</a></li>
			</ul>
		  </div>
		  <div class="input-group" style="margin-bottom: 10px;">
		      <span class="input-group-addon"><span style="color:red;">*</span>登录密码:</span>
		      <input id="signup-password" type="password" class="form-control" placeholder="请填写用户名">
	      </div>
	      <div class="input-group" style="margin-bottom: 10px;">
		      <span class="input-group-addon"><span style="color:red;">*</span>确认密码:</span>
		      <input id="signup-password-again" type="password" class="form-control" placeholder="请填写用户名">
	      </div>
	      <div id="avartar-upload-progressbar" class="progress" style="display:none;width:100%;">
		      <div class="progress-bar progress-bar-success progress-bar-striped" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%">
		        <span>0%</span>
		      </div>
		  </div>
		  <div id="signup-avartar-area" style="width:100%;display:none;margin-bottom:10px;">
		  	  <img src="" style="width:60px;height:80px" />
		  </div>
	      <div>
	          <label style="float:left;">头像:</label>
			  <form id="signup-avartar-form" enctype="multipart/form-data" action="${(fileserver)!}upload" method="post">
			  	  <input type="file" name="file" style="float:left"/>
			      <input type="submit" value="上传" />
			  </form>
	      </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary">注册</button>
      </div>
    </div>
  </div>
</div>