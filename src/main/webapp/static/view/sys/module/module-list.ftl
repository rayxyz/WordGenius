<div class="table-responsive">
	<table class="table table-striped">
	  <thead>
	    <tr>
	      <th>模块名</th>
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
		<#if moduleList?? && moduleList?size gt 0>
			<#list moduleList as module>
				<tr module-id="${(module.id)!}" module-code="${(module.code)!}">
			        <td>${(module.name)!}</td>
			        <td>${(module.code)!}</td>
			        <#if module?? && module.state?? && module.state>
			        	<td><span class="label label-success">已启用</span></td>
			        	<#else>
			        		<td><span class="label label-danger">未启用</span></td>
			        </#if>
			        <td>${(module.desc)!}</td>
			        <td>${(module.createTime?string("yyyy-MM-dd hh:mm"))!}</td>
			        <#--
			        <td>${(module.updateTime?string("yyyy-MM-dd hh:mm:ss"))!}</td>
			        -->
			        <td>
			        	<div class="btn-group" role="group" aria-label="...">
					        <button type="button" class="btn btn-default btn-sm module-action-mgmt-btn" module-id="${(module.id)!}" module-code="${(module.code)!}">管理功能</button>
					        <button type="button" class="btn btn-default btn-sm module-update-btn" module-id="${(module.id)!}" module-code="${(module.code)!}">修改</button>
					        <button type="button" class="btn btn-default btn-sm module-del-btn" module-id="${(module.id)!}" module-code="${(module.code)!}">删除</button>
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
<#if moduleList?? && moduleList?size gt 0>
	<#include "../template/pager.ftl">
</#if>
