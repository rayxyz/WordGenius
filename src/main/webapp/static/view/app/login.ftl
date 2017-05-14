<div class="modal fade" id="login-dlg" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
      </div>
      <div class="modal-body">
	   <form class="form-signin">
	      <h2 class="form-signin-heading">用户登录</h2>
	      <label for="login-dlg-account" class="sr-only">邮箱</label>
	      <input id="login-dlg-account" class="form-control" placeholder="帐号（邮箱）" autofocus="">
	      <label for="login-dlg-password" class="sr-only">密码</label>
	      <#--
	      <input type="password" id="login-dlg-password" class="form-control" placeholder="密码" required="">
	      -->
	      <input type="password" id="login-dlg-password" class="form-control" placeholder="密码">
	      <div class="checkbox">
	        <label>
	          <input type="checkbox" value="remember-me" /> 记住我
	        </label>
	      </div>
	      <button class="btn btn-lg btn-primary btn-block login">登录</button>
	    </form>
      </div>
    </div>
  </div>
</div>