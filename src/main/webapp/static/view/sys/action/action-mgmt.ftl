<div class="modal fade bs-example-modal-lg" id="action-mgmt-page" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">功能管理（所属模块：${(module.name)!}）</h4>
      </div>
      <div class="modal-body">
	    <input type="hidden" id="module-id-in-action-mgmt" value="${(module.id)!}"></input>
	    <div class="btn-group add-action-btn-area" role="group" aria-label="...">
		  <button type="button" class="btn btn-primary action-add-btn">添加
	    	<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
		  </button>
		</div>
        <div class="action-list-area">
	  		<img src="static/image/loader-big.gif" />
	  	</div>
	    <#--
	  	<div>
		  	<div class="btn-group" role="group" aria-label="...">
			  <button type="button" class="btn btn-primary module-add-btn">添加
		    	<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
			  </button>
			</div>
		    <div class="action-list-area">
		  	
		  	</div>
	  	</div>
	  	-->
      </div>
    </div>
  </div>
</div>

