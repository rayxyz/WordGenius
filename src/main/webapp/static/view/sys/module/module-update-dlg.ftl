<div class="modal fade" id="module-mgmt-update-dlg" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">修改模块信息</h4>
      </div>
      <div class="modal-body">
		 <div class="input-group" style="margin-bottom: 10px;">
		     <span class="input-group-addon"><span style="color:red;">*</span>模块名称:</span>
		     <input id="module-mgmt-update-name" type="text" class="form-control" placeholder="请填写模块名" aria-describedby="module-mgmt-update-name" value="${(module.name)!}"/>
		 </div>
		 <div class="input-group" style="margin-bottom: 10px;">
		     <span class="input-group-addon"><span style="color:red;">*</span>模块代码:</span>
		     <input id="module-mgmt-update-code" type="text" class="form-control" placeholder="请填写模块代码" aria-describedby="module-mgmt-update-code" value="${(module.code)!}"/>
		 </div>
		 <div id="module-mgmt-update-state-dropdown-list" class="dropdown" style="margin-bottom: 10px;">
			<label>状态:</label>
			<button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
			  <#if module?? && module.state?? && module.state>
			  	<span>已启用</span>
			    <#else>
			  	  <span>未启用</span>
			  </#if>
			  <span class="caret"></span>
			</button>
			<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
				<#if module?? && module.state?? && module.state>
					<li><a href="#" module-state="true" class="selected bg-primary">已启用</a></li>
					<li><a href="#" module-state="false">未启用</a></li>
					<#else>
						<li><a href="#" module-state="true">已启用</a></li>
						<li><a href="#" module-state="false" class="selected bg-primary">未启用</a></li>
				</#if>
			</ul>
	     </div>
	     <div class="form-group">
	         <label for="module-mgmt-update-desc" class="control-label">描述:</label>
	         <textarea class="form-control" id="module-mgmt-update-desc">${(module.desc)!}</textarea>
	     </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" module-id="${(module.id)!}">保存</button>
      </div>
    </div>
  </div>
</div>