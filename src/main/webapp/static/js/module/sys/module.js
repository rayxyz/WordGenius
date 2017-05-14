/**
 * Module management
 */
define(["pager", "message"], function(pager, message) {
	$(document).on("click", "#module-mgmt-page .pagination li", function(evt) {
		getList($("#module-mgmt-page .module-list-area"));
	});
	
	$(document).on("click", "#module-mgmt-page .module-add-btn", function(evt) {
		var dlg = $("#module-mgmt-add-dlg");
		if (dlg.length > 0) {
			$(dlg).modal({keyboard: false});
			return;
		}
		$(document).load("module/add_dlg", null, function(data) {
//			$('#myModal').modal({keyboard: false});
			console.log("loading data...");
			$(data).modal({keyboard: false});
		});
	});
	// Save the module info.
	$(document).on("click", "#module-mgmt-add-dlg .modal-footer button:last", function(evt) {
		var moduleObj = new Object();
		moduleObj.name = $("#module-name").val();
		moduleObj.code = $("#module-code").val();
		moduleObj.desc = $("#module-desc").val();
		console.log(moduleObj);
		if (!Boolean(moduleObj.name)) {
			message.warning("模块名不能为空！");
			return;
		}
		if (!Boolean(moduleObj.code)) {
			message.warning("模块路径不能为空！");
			return;
		}
		var loadingBox = message.loading("保存中...");
		$.ajax({
			url: "module/save",
			type: "POST",
			data: {
				params: JSON.stringify(moduleObj)
			},
			success: function(data) {
				message.hide(loadingBox);
				if (data.success) {
					message.success("保存成功！");
					$("#module-mgmt-add-dlg").modal("hide");
					$("#sys-index-main-area").load("module/index", null, function() {
						getList($("#module-mgmt-page .module-list-area"));
					});
				} else {
					message.danger("保存失败！");
				}
			},
			error: function(error) {
				message.warning("连接接出错！");
				message.hide(loadingBox);
			}
		});
	});
	
	$(document).on("click", "#module-mgmt-page .module-list-area table button", function(evt) {
		var moduleId = $(this).attr("module-id");
		if ($(this).hasClass("module-action-mgmt-btn")) {
			console.log("Module action mgmt.");
			require(["action"], function(action) {
				action.init(moduleId);
			});
		}
		if ($(this).hasClass("module-update-btn")) {
			var dlg = $("#module-mgmt-update-dlg");
			if (dlg.length > 0) {
				dlg.remove();
			}
			var moduleId = $(this).attr("module-id");
			$(document).load("module/update_dlg/" + moduleId, null, function(data) {
				console.log("loading data...");
				$(data).modal({keyboard: false});
			});
		}
		if ($(this).hasClass("module-del-btn")) {
			del($(this).attr("module-id"));
		}
	});
	
	$(document).on("click", "#module-mgmt-update-state-dropdown-list .dropdown-menu li", function(){
	    $("#module-mgmt-update-state-dropdown-list button span:first").text($($(this).find("a")).text());
	    $("#module-mgmt-update-state-dropdown-list .dropdown-menu li a").removeClass("selected").removeClass("bg-primary");
	    $($(this).find('a')).addClass("selected").addClass("bg-primary");
    });
	
	$(document).on("click", "#module-mgmt-update-dlg .modal-footer button:last", function(evt) {
		update($(this).attr("module-id"));
	});
	
	function update(moduleId) {
		console.log("Module update.");
		var moduleObj = new Object();
		moduleObj.id = moduleId;
		moduleObj.name = $("#module-mgmt-update-name").val();
		moduleObj.code = $("#module-mgmt-update-code").val();
		moduleObj.state = $("#module-mgmt-update-state-dropdown-list .dropdown-menu .selected").attr("module-state");
		moduleObj.desc = $("#module-mgmt-update-desc").val();
		console.log(moduleObj);
		if (!Boolean(moduleObj.name)) {
			message.warning("模块名不能为空！");
			return;
		}
		if (!Boolean(moduleObj.code)) {
			message.warning("模块路径不能为空！");
			return;
		}
		var loadingBox = message.loading("保存中...");
		$.ajax({
			url: "module/update",
			type: "POST",
			data: {
				params: JSON.stringify(moduleObj)
			},
			success: function(data) {
				message.hide(loadingBox);
				if (data.success) {
					message.success("保存成功！");
					$("#module-mgmt-update-dlg").modal("hide");
					getList($("#module-mgmt-page .module-list-area"));
				} else {
					message.danger("保存失败！");
				}
			},
			error: function(error) {
				message.warning("连接接出错！");
				message.hide(loadingBox);
			}
		});
	}
	
	function del(moduleId) {
		console.log("Module delete");
		bootbox.confirm({
		    message: "是否确认删除模块？",
		    buttons: {
		        confirm: {
		            label: '删除',
		            className: 'btn-danger'
		        },
		        cancel: {
		            label: '取消',
		            className: 'btn-primary'
		        }
		    },
		    callback: function (result) {
		    	if (result) {
		    		var dlg = message.loading("操作中，请稍候...");
			    	$.ajax({
						url: "module/delete",
						type: "POST",
						data: {
							moduleId: moduleId
						},
						success: function(data) {
							if (data.success) {
								message.success("删除成功！");
								getList($("#module-mgmt-page .module-list-area"), 1);
							} else {
								message.error(data.msg);
							}
							message.hide(dlg);
						},
						error: function(error) {
							console.log("Connection error.");
							message.hide(dlg);
						}
					});
		    	}
		    }
		});
	}
	
	function getList(ele) {
		var moduleObj = new Object();
		moduleObj.id = "dddd";
		moduleObj.name = "ffffff";
		$.ajax({
			url: "module/getList",
			type: "GET",
			data: {
				queryParams: JSON.stringify(moduleObj)
			},
			success: function(data) {
				$(ele).html(data);
			},
			error: function(error) {
				console.log("Connection error.");
			}
		});
	}
	
	return {
		getList: getList
	}
});