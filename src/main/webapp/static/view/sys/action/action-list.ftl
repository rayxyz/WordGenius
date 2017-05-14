<div class="table-responsive">
	<table class="table table-striped">
	  <thead>
	    <tr>
	      <th>功能名</th>
	      <th>代码</th>
	      <th>状态</th>
	      <th>描述</th>
	      <th>创建时间</th>
	      <#--
	      <th>更新时间</th>
	      -->
	      <th>操作</th>
	    </tr>
	  </thead>
	  <tbody>
		<#if actionList?? && actionList?size gt 0>
			<#list actionList as action>
				<tr action-id="${(action.id)!}" action-code="${(action.code)!}">
			        <td>${(action.name)!}</td>
			        <td>${(action.code)!}</td>
			        <#if action.state?? && action.state>
			        	<td><span class="label label-success">已启用</span></td>
			        	<#else>
			        		<td><span class="label label-danger">未启用</span></td>
			        </#if>
			        <td>${(action.desc)!}</td>
			        <td>${(action.createTime?string("yyyy-MM-dd hh:mm"))!}</td>
			        <#--
			        <td>${(action.updateTime?string("yyyy-MM-dd hh:mm:ss"))!}</td>
			        -->
			        <td>
			        	<div class="btn-group" role="group" aria-label="...">
					        <button type="button" class="btn btn-default btn-sm action-update-btn" action-id="${(action.id)!}" action-code="${(action.code)!}" module-id="${(action.moduleId)!}">修改</button>
					        <button type="button" class="btn btn-default btn-sm action-del-btn" action-id="${(action.id)!}" action-code="${(action.code)!}" module-id="${(action.moduleId)!}">删除</button>
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
<#if actionList?? && actionList?size gt 0>
	<#include "../template/pager.ftl">
</#if>
