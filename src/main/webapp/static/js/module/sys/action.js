/**
 * action management
 */
define(["pager", "message"], function(pager, message) {
	$(document).on("click", "#action-mgmt-page .pagination li", function(evt) {
		var pageNo = $(evt.currentTarget).attr("page_no");
		getList($("#action-mgmt-page .action-list-area"), pageNo);
	});
	
	$(document).on("click", "#action-mgmt-page .action-add-btn", function(evt) {
		var dlg = $("#action-mgmt-add-dlg");
		if (dlg.length > 0) {
			$(dlg).modal({keyboard: false});
			return;
		}
		$(document).load("action/add_dlg", null, function(data) {
//			$('#myModal').modal({keyboard: false});
			console.log("loading data...");
			$(data).modal({keyboard: false});
		});
	});
	// Save the action info.
	$(document).on("click", "#action-mgmt-add-dlg .modal-footer button:last", function(evt) {
		var actionObj = new Object();
		actionObj.name = $("#action-name").val();
		actionObj.code = $("#action-code").val();
		actionObj.desc = $("#action-desc").val();
		actionObj.moduleId = $("#module-id-in-action-mgmt").val();
		console.log(actionObj);
		if (!Boolean(actionObj.name)) {
			message.warning("功能名称不能为空！");
			return;
		}
		if (!Boolean(actionObj.code)) {
			message.warning("功能代码不能为空！");
			return;
		}
		if (!Boolean(actionObj.moduleId)) {
			message.warning("所属模块无效！");
			return;
		}
		var loadingBox = message.loading("保存中...");
		$.ajax({
			url: "action/save",
			type: "POST",
			data: {
				params: JSON.stringify(actionObj)
			},
			success: function(data) {
				message.hide(loadingBox);
				if (data.success) {
					message.success("保存成功！");
					$("#action-mgmt-add-dlg").modal("hide");
					getList($("#action-mgmt-page .action-list-area"), actionObj.moduleId);
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
	
	//action-mgmt-update-name
	// update
	$(document).on("click", "#action-mgmt-page .action-update-btn", function(evt) {
		var dlg = $("#action-mgmt-update-dlg");
		if (dlg.length > 0) {
			dlg.remove();
		}
		var actionId = $(this).attr("action-id");
		$(document).load("action/update_dlg/" + actionId, null, function(data) {
			console.log("loading data...");
			$(data).modal({keyboard: false});
		});
	});
	
	$(document).on("click", "#action-mgmt-update-state-dropdown-list .dropdown-menu li", function(){
	    $("#action-mgmt-update-state-dropdown-list button span:first").text($($(this).find("a")).text());
	    $("#action-mgmt-update-state-dropdown-list .dropdown-menu li a").removeClass("selected").removeClass("bg-primary");
	    $($(this).find('a')).addClass("selected").addClass("bg-primary");
    });
	
	$(document).on("click", "#action-mgmt-update-dlg .modal-footer button:last", function(evt) {
		var actionObj = new Object();
		actionObj.id = $(this).attr("action-id");
		actionObj.moduleId = $(this).attr("module-id");
		actionObj.name = $("#action-mgmt-update-name").val();
		actionObj.code = $("#action-mgmt-update-code").val();
		actionObj.state = $("#action-mgmt-update-state-dropdown-list .dropdown-menu .selected").attr("action-state");
		actionObj.desc = $("#action-mgmt-update-desc").val();
		console.log(actionObj);
		if (!Boolean(actionObj.name)) {
			message.warning("功能名称不能为空！");
			return;
		}
		if (!Boolean(actionObj.code)) {
			message.warning("功能代码不能为空！");
			return;
		}
		var loadingBox = message.loading("保存中...");
		$.ajax({
			url: "action/update",
			type: "POST",
			data: {
				params: JSON.stringify(actionObj)
			},
			success: function(data) {
				if (data.success) {
					message.success("保存成功！");
					$("#action-mgmt-update-dlg").modal("hide");
					getList($("#action-mgmt-page .action-list-area"), actionObj.moduleId);
				} else {
					message.error(data.msg);
				}
				message.hide(loadingBox);
			},
			error: function(error) {
				message.warning("连接接出错！");
				message.hide(loadingBox);
			}
		});
	});
	
	$(document).on("click", "#action-mgmt-page .action-del-btn", function(evt) {
		var moduleId = $(this).attr("module-id");
		var acitonId = $(this).attr("action-id");
		bootbox.confirm({
		    message: "是否确认删除功能？",
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
						url: "action/delete",
						type: "POST",
						data: {
							actionId: acitonId
						},
						success: function(data) {
							if (data.success) {
								message.success("删除成功！");
								getList($("#action-mgmt-page .action-list-area"), moduleId);
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
	
	function init(moduleId) {
		$("#action-mgmt-page").remove();
		$(document).load("action/index/" + moduleId, null, function(data) {
			console.log("loading data...");
			$(data).on("show.bs.modal", function(evt) {
				$(evt.currentTarget).find(".add-action-btn-area").hide();
			}).modal({keyboard: false}).on("shown.bs.modal", function(evt) {
				var target = evt.currentTarget;
				getList($(evt.currentTarget).find(".action-list-area"), moduleId, function() {
					$(target).find(".add-action-btn-area").show();
				});
			});
		});
	}
	
	function getList(ele, moduleId, callback) {
		var actionObj = new Object();
		actionObj.id = "dddd";
		actionObj.name = "ffffff";
		actionObj.moduleId = moduleId;
		$.ajax({
			url: "action/getList",
			type: "GET",
			data: {
				queryParams: JSON.stringify(actionObj)
			},
			success: function(data) {
				$(ele).html(data);
				if (callback) {
					callback.call();
				}
			},
			error: function(error) {
				console.log("Connection error.");
			}
		});
	}
	
	return {
		init: init,
		getList: getList
	}
});