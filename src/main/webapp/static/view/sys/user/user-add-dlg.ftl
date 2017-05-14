<div class="modal fade" id="user-mgmt-add-dlg" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">添加用户</h4>
      </div>
      <div class="modal-body">
	      <div class="input-group" style="margin-bottom: 10px;">
		      <span class="input-group-addon"><span style="color:red;">*</span>用户名称:</span>
		      <input id="user-mgmt-add-name" type="text" class="form-control" placeholder="请填写用户名" aria-describedby="user-name">
		  </div>
	      <div class="input-group" style="margin-bottom: 10px;">
		      <span class="input-group-addon"><span style="color:red;">*</span>电子邮箱:</span>
		      <input id="user-mgmt-add-email" type="text" class="form-control" placeholder="电子邮箱将作为用户账号，保存后将不能更改" aria-describedby="user-email">
		  </div>
	      <div class="input-group" style="margin-bottom: 10px;">
		      <span class="input-group-addon">手机号码:</span>
		      <input id="user-mgmt-add-mobilephone" type="text" class="form-control" placeholder="请填写正确的手机号码" aria-describedby="user-mgmt-add-mobilephone">
		  </div>
		  <div class="input-group" style="margin-bottom: 10px;">
		      <span class="input-group-addon">出生日期:</span>
		      <input id="user-mgmt-add-birthdate" type="text" class="form-control" placeholder="请填写出生日期;如：1990-09-13" aria-describedby="user-mgmt-add-birthdate">
		  </div>
          <div id="user-mgmt-add-gender-dropdown-list" class="dropdown" style="margin-bottom: 10px;">
			<label>性别:</label>
			<button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
			  <span>请选择角色</span>
			  <span class="caret"></span>
			</button>
			<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
				<li><a href="#" user-gender="male">男</a></li>
				<li><a href="#" user-gender="female">女</a></li>
			</ul>
		  </div>
		  <div id="user-mgmt-add-role-dropdown-list" class="dropdown" style="margin-bottom: 10px;">
			<label><span style="color:red;">*</span>用户角色:</label>
			<button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
			  <span>请选择角色</span>
			  <span class="caret"></span>
			</button>
			<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
			    <#if roleList?? && roleList?size gt 0>
			  		<#list roleList as role>
			  			<li><a href="#" role-id="${(role.id)!}">${(role.name)!}</a></li>
			  		</#list>
			    </#if>
			</ul>
		  </div>
	      <div class="form-group">
	        <label for="user-mgmt-add-desc" class="control-label">描述:</label>
	        <textarea class="form-control" id="user-mgmt-add-desc" placeholder="请填写用户描述"></textarea>
	      </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary">保存</button>
      </div>
    </div>
  </div>
</div>