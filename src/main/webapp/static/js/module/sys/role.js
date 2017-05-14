/**
 * 
 */
define(["pager", "message"], function(pager, message) {
	$(document).on("click", "#role-mgmt-page .pagination li", function(evt) {
		var pageNo = $(evt.currentTarget).attr("page_no");
		getList($("#role-mgmt-page .role-list-area"), pageNo);
	});
	
	$(document).on("click", "#role-mgmt-page .role-add-btn", function(evt) {
		console.log("showing the modal...");
		var dlg = $("#role-mgmt-add-dlg");
		if (dlg.length > 0) {
			$(dlg).modal({keyboard: false});
			return;
		}
		$(document).load("role/add_dlg", null, function(data) {
//			$('#myModal').modal({keyboard: false});
			console.log("loading data...");
			$(data).modal({keyboard: false});
		});
	});
	// Save the role info.
	$(document).on("click", "#role-mgmt-add-dlg .modal-footer button:last", function(evt) {
//		message.info("Hello, my friend!");
		var roleObj = new Object();
		roleObj.name = $("#role-name").val();
		roleObj.desc = $("#role-desc").val();
		console.log(roleObj);
		if (!Boolean(roleObj.name)) {
			message.warning("角色名不能为空！");
			return;
		}
		var loadingBox = message.loading("保存中...");
		$.ajax({
			url: "role/save",
			type: "POST",
			data: {
				params: JSON.stringify(roleObj)
			},
			success: function(data) {
				message.hide(loadingBox);
				if (data.success) {
					message.success("保存成功！");
					$("#role-mgmt-add-dlg").modal("hide");
					getList($("#role-mgmt-page .role-list-area"));
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
	
	$(document).on("click", "#role-mgmt-page .role-update-btn", function(evt) {
		var dlg = $("#role-mgmt-update-dlg");
		if (dlg.length > 0) {
			dlg.remove();
		}
		var roleId = $(this).attr("role-id");
		$(document).load("role/update_dlg/" + roleId, null, function(data) {
//			$('#myModal').modal({keyboard: false});
			console.log("loading data...");
			$(data).modal({keyboard: false});
		});
	});
	
	$(document).on("click", "#role-mgmt-update-dlg .modal-footer button:last", function(evt) {
		var roleObj = new Object();
		roleObj.id = $(this).attr("role-id");
		roleObj.name = $("#role-mgmt-update-role-name").val();
		roleObj.desc = $("#role-mgmt-update-role-desc").val();
		console.log(roleObj);
		if (!Boolean(roleObj.name)) {
			message.warning("角色名不能为空！");
			return;
		}
		var loadingBox = message.loading("保存中...");
		$.ajax({
			url: "role/update",
			type: "POST",
			data: {
				params: JSON.stringify(roleObj)
			},
			success: function(data) {
				message.hide(loadingBox);
				if (data.success) {
					message.success("保存成功！");
					$("#role-mgmt-update-dlg").modal("hide");
					getList($("#role-mgmt-page .role-list-area"));
				} else {
					message.error(data.msg);
				}
			},
			error: function(error) {
				message.warning("连接接出错！");
				message.hide(loadingBox);
			}
		});
	});

	$(document).on("click", "#role-mgmt-page .role-del-btn", function(evt) {
		var roleId = $(this).attr("role-id");
		bootbox.confirm({
		    message: "是否确认删除角色？",
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
						url: "role/delete/" + roleId,
						type: "POST",
						success: function(data) {
							if (data.success) {
								message.success("删除成功！");
								getList($("#role-mgmt-page .role-list-area"));
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
	});
	
	function getList(ele) {
		var roleObj = new Object();
		roleObj.id = "dddd";
		roleObj.roleName = "ffffff";
		$.ajax({
			url: "role/getList",
			type: "GET",
			data: {
				queryParams: JSON.stringify(roleObj)
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