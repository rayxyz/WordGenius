<div class="modal fade" id="user-mgmt-update-dlg" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">修改用户信息</h4>
      </div>
      <div class="modal-body">
	      <div class="input-group" style="margin-bottom: 10px;">
		      <span class="input-group-addon"><span style="color:red;">*</span>用户名称:</span>
		      <input id="user-mgmt-update-name" type="text" class="form-control" placeholder="请填写用户名" aria-describedby="user-name" value="${(user.name)!}"/>
		  </div>
	      <div class="input-group" style="margin-bottom: 10px;">
		      <span class="input-group-addon"><span style="color:red;">*</span>电子邮箱:</span>
		      <input id="user-mgmt-update-email" type="text" class="form-control" placeholder="电子邮箱将作为用户账号，保存后将不能更改" aria-describedby="user-email" value="${(user.email)!}">
		  </div>
	      <div class="input-group" style="margin-bottom: 10px;">
		      <span class="input-group-addon">手机号码:</span>
		      <input id="user-mgmt-update-mobilephone" type="text" class="form-control" placeholder="请填写正确的手机号码" aria-describedby="user-mgmt-update-mobilephone" value="${(user.phone)!}">
		  </div>
		  <div class="input-group" style="margin-bottom: 10px;">
		      <span class="input-group-addon">出生日期:</span>
		      <input id="user-mgmt-update-birthdate" type="text" class="form-control" placeholder="请填写出生日期;如：1990-09-13" aria-describedby="user-mgmt-update-birthdate"  value="${(user.birthOfDate)!}">
		  </div>
          <div id="user-mgmt-update-gender-dropdown-list" class="dropdown" style="margin-bottom: 10px;">
			<label>性别:</label>
			<button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
			  <#if user.gender?? && user.gender != "">
			  	<#if user.gender == "male">
			  		<span>男</span>
			  	</#if>
			  	<#if user.gender == "female">
			  		<span>女</span>
			  	</#if>
			    <#else>
			  	  <span>请选择角色</span>
			  </#if>
			  <span class="caret"></span>
			</button>
			<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
				<#if user.gender?? && user.gender != "">
					<#if user.gender == "male">
						<li><a href="#" user-gender="male" class="selected bg-primary">男</a></li>
						<li><a href="#" user-gender="female">女</a></li>
					</#if>
					<#if user.gender == "female">
						<li><a href="#" user-gender="male">男</a></li>
						<li><a href="#" user-gender="female" class="selected bg-primary">女</a></li>
					</#if>
				</#if>
			</ul>
		  </div>
		  <div id="user-mgmt-update-role-dropdown-list" class="dropdown" style="margin-bottom: 10px;">
			<label><span style="color:red;">*</span>用户角色:</label>
			<button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
			  <#if user.roleName?? && user.roleName != "">
		      	<span>${(user.roleName)!}</span>
			  	<#else>
		      		<span>请选择角色</span>
			  </#if>
			  <span class="caret"></span>
			</button>
			<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
			    <#if roleList?? && roleList?size gt 0>
			  		<#list roleList as role>
			  			<#if user.roleId?? && user.roleId == role.id>
			  				<li><a href="#" role-id="${(role.id)!}" class="selected bg-primary">${(role.name)!}</a></li>
			  				<#else>
			  					<li><a href="#" role-id="${(role.id)!}">${(role.name)!}</a></li>
			  			</#if>
			  		</#list>
			    </#if>
			</ul>
		  </div>
	      <div class="form-group">
	        <label for="user-mgmt-add-desc" class="control-label">描述:</label>
	        <textarea class="form-control" id="user-mgmt-update-desc" placeholder="请填写用户描述">${(user.desc)!}</textarea>
	      </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" user-id="${(user.id)!}">保存</button>
      </div>
    </div>
  </div>
</div>