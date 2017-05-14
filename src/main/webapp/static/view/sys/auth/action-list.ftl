<div class="table-responsive">
	<table class="table table-striped">
	  <thead>
	    <tr>
	      <th>
	      	<#if allAuthed?? && allAuthed>
	      		<input type="checkbox" class="check-all" checked/>
	      		<#else>
	      			<input type="checkbox" class="check-all"/>
	      	</#if>
	      </th>
	      <th>功能名称</th>
	      <th>功能代码</th>
	      <th>所属模块</th>
	      <th>状态</th>
	      <th>描述</th>
	      <th>创建时间</th>
	    </tr>
	  </thead>
	  <tbody>
		<#if actionList?? && actionList?size gt 0>
			<#list actionList as action>
				<tr>
					<#if action.authed>
			        	<td><input type="checkbox" checked action-id="${(action.id)!}"/></td>
			        	<#else>
			        		<td><input type="checkbox" action-id="${(action.id)!}"/></td>
					</#if>
			        <td>${(action.name)!}</td>
			        <td>${(action.code)!}</td>
			        <td>${(action.moduleName)!}</td>
			        <#if action.state?? && action.state>
			        	<td><span class="label label-success">已启用</span></td>
			        	<#else>
			        		<td><span class="label label-danger">未启用</span></td>
			        </#if>
			        <td>${(action.desc)!}</td>
			        <td>${(action.createTime?string("yyyy-MM-dd hh:mm"))!}</td>
		        </tr>
			</#list>
			<#else>
				<tr><td>No data!</td></tr>
		</#if>
	  </tbody>
	</table>
</div>
