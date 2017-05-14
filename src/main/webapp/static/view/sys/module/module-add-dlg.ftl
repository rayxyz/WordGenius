<div class="modal fade" id="module-mgmt-add-dlg" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">添加模块</h4>
      </div>
      <div class="modal-body">
	     <div class="input-group" style="margin-bottom: 10px;">
		     <span class="input-group-addon"><span style="color:red;">*</span>模块名称:</span>
		     <input id="module-name" type="text" class="form-control" placeholder="请填写模块名" aria-describedby="module-mgmt-update-name"/>
		 </div>
		 <div class="input-group" style="margin-bottom: 10px;">
		     <span class="input-group-addon"><span style="color:red;">*</span>模块代码:</span>
		     <input id="module-code" type="text" class="form-control" placeholder="请填写模块代码" aria-describedby="module-mgmt-update-code"/>
		 </div>
	      <div class="form-group">
	        <label for="module-desc" class="control-label">描述:</label>
	        <textarea class="form-control" id="module-desc"></textarea>
	      </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary">保存</button>
      </div>
    </div>
  </div>
</div>