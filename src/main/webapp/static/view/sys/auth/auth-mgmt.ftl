<div id="auth-mgmt-page">
	<h1 class="page-header">权限管理</h1>
	<div style="margin-bottom:15px;"> 
		<div id="auth-mgmt-role-dropdown-list" class="dropdown" style="float:left;">
			<label>角色：</label>
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
		<div id="auth-mgmt-module-dropdown-list" class="dropdown" style="float:left">
			<label>模块：</label>
			<button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
			  <span>所有模块</span>
			  <span class="caret"></span>
			</button>
			<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
				<li><a href="#" module-id="-1" class="selected bg-primary">所有模块</a></li>
				<li role="separator" class="divider"></li>
				<#if moduleList?? && moduleList?size gt 0>
			  		<#list moduleList as module>
			  			<li><a href="#" module-id="${(module.id)!}">${(module.name)!}</a></li>
			  		</#list>
			    </#if>
			</ul>
		</div>&nbsp;&nbsp;&nbsp;&nbsp;
		<button id="auth-mgmt-set-btn" class="btn btn-primary" disabled="disabled">保存设置</button>
	</div>
	<div class="action-list-area">
	
	</div>
</div>