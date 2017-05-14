<div class="modal fade" id="action-mgmt-update-dlg" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">修改功能信息</h4>
      </div>
      <div class="modal-body">
		<div class="input-group" style="margin-bottom: 10px;">
		    <span class="input-group-addon"><span style="color:red;">*</span>功能名称:</span>
		    <input id="action-mgmt-update-name" type="text" class="form-control" placeholder="请填写功能名" aria-describedby="action-mgmt-update-name" value="${(action.name)!}"/>
		</div>
		<div class="input-group" style="margin-bottom: 10px;">
		    <span class="input-group-addon"><span style="color:red;">*</span>功能代码:</span>
		    <input id="action-mgmt-update-code" type="text" class="form-control" placeholder="请填写功能代码" aria-describedby="action-mgmt-update-code" value="${(action.code)!}"/>
		</div>
		<div id="action-mgmt-update-state-dropdown-list" class="dropdown" style="margin-bottom: 10px;">
			<label>状态:</label>
			<button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
			  <#if action.state?? && action.state>
			  	<span>已启用</span>
			    <#else>
			  	  <span>未启用</span>
			  </#if>
			  <span class="caret"></span>
			</button>
			<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
				<#if action.state?? && action.state>
					<li><a href="#" action-state="true" class="selected bg-primary">已启用</a></li>
					<li><a href="#" action-state="false">未启用</a></li>
					<#else>
						<li><a href="#" action-state="true">已启用</a></li>
						<li><a href="#" action-state="false" class="selected bg-primary">未启用</a></li>
				</#if>
			</ul>
		</div>
        <div class="form-group">
            <label for="action-desc" class="control-label">描述:</label>
            <textarea class="form-control" id="action-mgmt-update-desc" placeholer="请填写功能描述">${(action.desc)!}</textarea>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" action-id="${(action.id)!}" module-id="${(action.moduleId)!}">保存</button>
      </div>
    </div>
  </div>
</div>