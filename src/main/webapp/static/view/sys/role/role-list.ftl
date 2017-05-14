<div class="table-responsive">
	<table class="table table-striped">
	  <thead>
	    <tr>
	      <th>角色名</th>
	      <th>创建时间</th>
	      <th>更新时间</th>
	      <th>描述</th>
	      <th>操作</th>
	    </tr>
	  </thead>
	  <tbody>
		<#if roleList?? && roleList?size gt 0>
			<#list roleList as role>
				<tr>
			        <td>${(role.name)!}</td>
			        <td>${(role.createTime?string("yyyy-MM-dd hh:mm:ss"))!}</td>
			        <td>${(role.updateTime?string("yyyy-MM-dd hh:mm:ss"))!}</td>
			        <td>${(role.desc)!}</td>
			        <td>
					    <div class="btn-group" role="group" aria-label="...">
					        <button type="button" class="btn btn-default btn-sm role-update-btn" role-id="${(role.id)!}">修改</button>
					        <button type="button" class="btn btn-default btn-sm role-del-btn" role-id="${(role.id)!}">删除</button>
					    </div>
			        </td>
		        </tr>
			</#list>
			<#else>
				<tr><td>No data!</td></tr>
		</#if>
	  </tbody>
	</table>
</div>
<#-- include the pager tool -->
<#if roleList?? && roleList?size gt 0>
	<#include "../template/pager.ftl">
</#if>
