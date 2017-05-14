<div class="table-responsive">
	<table class="table table-striped">
	  <thead>
	    <tr>
	      <th>用户名称</th>
	      <th>所属角色</th>
	      <th>性别</th>
	      <th>电话</th>
	      <th>邮箱</th>
	      <th>操作</th>
	    </tr>
	  </thead>
	  <tbody>
		<#if userList?? && userList?size gt 0>
			<#list userList as user>
				<tr>
			        <td>${(user.name)!}</td>
			        <td>${(user.roleName)!}</td>
			        <td>${(user.gender)!}</td>
			        <td>${(user.phone)!}</td>
			        <td>${(user.email)!}</td>
			        <td>
					    <div class="btn-group" role="group" aria-label="...">
					        <button type="button" class="btn btn-default btn-sm user-update-btn" user-id="${(user.id)!}">修改</button>
					        <button type="button" class="btn btn-default btn-sm user-del-btn" user-id="${(user.id)!}">删除</button>
					    </div>
			        </td>
		        </tr>
			</#list>
		</#if>
	  </tbody>
	</table>
</div>
<#-- include the pager tool -->
<#if userList?? && userList?size gt 0>
	<#include "../template/pager.ftl">
</#if>
