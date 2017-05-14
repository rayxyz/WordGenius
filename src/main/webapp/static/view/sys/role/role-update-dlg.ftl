<div class="modal fade" id="role-mgmt-update-dlg" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">添加角色</h4>
      </div>
      <div class="modal-body">
	     <form>
	      <div class="form-group">
	        <label for="role-mgmt-update-role-name" class="control-label">角色名:</label>
	        <input type="text" class="form-control" id="role-mgmt-update-role-name" value="${(role.name)!}" />
	      </div>
	      <div class="form-group">
	        <label for="role-mgmt-update-role-desc" class="control-label">描述:</label>
	        <textarea class="form-control" id="role-mgmt-update-role-desc">${(role.desc)!}</textarea>
	      </div>
	    </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" role-id="${(role.id)!}">保存</button>
      </div>
    </div>
  </div>
</div>