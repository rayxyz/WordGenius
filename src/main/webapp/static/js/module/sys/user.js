define(["pager", "message"], function(pager, message) {
	$(document).on("click", "#user-mgmt-page .pagination li", function(evt) {
		var pageNo = $(evt.currentTarget).attr("page_no");
		getList($("#user-mgmt-page .user-list-area"), pageNo);
	});
	
	// add
	$(document).on("click", "#user-mgmt-page .user-add-btn", function(evt) {
		var dlg = $("#user-mgmt-add-dlg");
		if (dlg.length > 0) {
			$(dlg).modal({keyboard: false});
			return;
		}
		$(document).load("user/add_dlg", null, function(data) {
//			$('#myModal').modal({keyboard: false});
			console.log("loading data...");
			$(data).modal({keyboard: false});
		});
	});

	$(document).on("click", "#user-mgmt-add-gender-dropdown-list .dropdown-menu li", function(){
	    $("#user-mgmt-add-gender-dropdown-list button span:first").text($($(this).find("a")).text());
	    $("#user-mgmt-add-gender-dropdown-list .dropdown-menu li a").removeClass("selected").removeClass("bg-primary");
	    $($(this).find('a')).addClass("selected").addClass("bg-primary");
    });
	
	$(document).on("click", "#user-mgmt-add-role-dropdown-list .dropdown-menu li", function(){
	    $("#user-mgmt-add-role-dropdown-list button span:first").text($($(this).find("a")).text());
	    $("#user-mgmt-add-role-dropdown-list .dropdown-menu li a").removeClass("selected").removeClass("bg-primary");
	    $($(this).find('a')).addClass("selected").addClass("bg-primary");
    });
	
	$(document).on("click", "#user-mgmt-add-dlg .modal-footer button:last", function(evt) {
		save();
	});
	
	// update
	$(document).on("click", "#user-mgmt-page .user-update-btn", function(evt) {
		var dlg = $("#user-mgmt-update-dlg");
		if (dlg.length > 0) {
			dlg.remove();
		}
		var userId = $(this).attr("user-id");
		$(document).load("user/update_dlg/" + userId, null, function(data) {
			console.log("loading data...");
			$(data).modal({keyboard: false});
		});
	});
	
	$(document).on("click", "#user-mgmt-update-gender-dropdown-list .dropdown-menu li", function(){
	    $("#user-mgmt-update-gender-dropdown-list button span:first").text($($(this).find("a")).text());
	    $("#user-mgmt-update-gender-dropdown-list .dropdown-menu li a").removeClass("selected").removeClass("bg-primary");
	    $($(this).find('a')).addClass("selected").addClass("bg-primary");
    });
	
	$(document).on("click", "#user-mgmt-update-role-dropdown-list .dropdown-menu li", function(){
	    $("#user-mgmt-update-role-dropdown-list button span:first").text($($(this).find("a")).text());
	    $("#user-mgmt-update-role-dropdown-list .dropdown-menu li a").removeClass("selected").removeClass("bg-primary");
	    $($(this).find('a')).addClass("selected").addClass("bg-primary");
    });

	$(document).on("click", "#user-mgmt-update-dlg .modal-footer button:last", function(evt) {
		update($(this).attr("user-id"));
	});
	
	$(document).on("click", "#user-mgmt-page .user-del-btn", function(evt) {
		var uid = $(this).attr("user-id");
		bootbox.confirm({
		    message: "是否确认删除用户？",
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
						url: "user/delete/" + uid,
						type: "POST",
						success: function(data) {
							if (data.success) {
								message.success("删除成功！");
								getList($("#user-mgmt-page .user-list-area"), 1);
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
	
	function getList(ele, pageNo) {
		pageNo = !pageNo ?  1 : pageNo;
		var pagerObj = pager.getPager();
		pagerObj.pageNo = pageNo;
		var userObj = new Object();
		userObj.id = "333";
		userObj.name = "ekfd";
		$.ajax({
			url: "user/getList",
			type: "GET",
//			contentType: "application/json; charset=utf-8",
			data: {
				queryParams: JSON.stringify(userObj),
				pagerParams: JSON.stringify(pagerObj)
			},
			success: function(data) {
				$(ele).html(data);
			},
			error: function(error) {
				console.log("Connection error.");
			}
		});
	}
	
	function save() {
		var userObj = new Object();
		userObj.name = $("#user-mgmt-add-name").val();
		userObj.email = $("#user-mgmt-add-email").val();
		userObj.phone = $("#user-mgmt-add-mobilephone").val();
		userObj.dateOfBirth = $("#user-mgmt-add-birthdate").val();
		userObj.gender = $("#user-mgmt-add-gender-dropdown-list .dropdown-menu .selected").attr("user-gender");
		userObj.roleId = $("#user-mgmt-add-role-dropdown-list .dropdown-menu .selected").attr("role-id");
		userObj.desc = $("#user-mgmt-add-desc").val();
		if (!Boolean(userObj.name)) {
			message.warning("请填写用户名！");
			return;
		}
		if (!Boolean(userObj.email)) {
			message.warning("请填写正确的电子邮箱！");
			return;
		}
		if (Boolean(userObj.dateOfBirth)) {
			try {
				var transientVar = new Date(userObj.dateOfBirth);
			} catch (err) {
				message.warning("出生日期格式错误！");
				return;
			}
		}
		if (!Boolean(userObj.roleId)) {
			message.warning("请选择用户的角色！");
			return;
		}
		console.log("Saving...");
		console.log(userObj);
		var msgDlg = message.loading("保存中...");
		$.ajax({
			url: "user/save",
			type: "POST",
			data: {
				params: JSON.stringify(userObj)
			},
			success: function(data) {
				console.log(data);
				message.hide(msgDlg);
				if (data.success) {
					message.success("保存成功！");
					$("#user-mgmt-add-dlg").modal("hide");
					getList($("#user-mgmt-page .user-list-area"), 1);
				} else {
					message.danger(data.msg);
				}
			},
			error: function(error) {
				console.log("Connection error.");
				message.hide(msgDlg);
			}
		});
	}
	
	function update(userId) {
		var userObj = new Object();
		userObj.id = userId;
		userObj.name = $("#user-mgmt-update-name").val();
		userObj.email = $("#user-mgmt-update-email").val();
		userObj.dateOfBirth = $("#user-mgmt-update-birthdate").val();
		userObj.phone = $("#user-mgmt-update-mobilephone").val();
		userObj.gender = $("#user-mgmt-update-gender-dropdown-list .dropdown-menu .selected").attr("user-gender");
		userObj.roleId = $("#user-mgmt-update-role-dropdown-list .dropdown-menu .selected").attr("role-id");
		userObj.desc = $("#user-mgmt-update-desc").val();
		if (!Boolean(userObj.name)) {
			message.warning("请填写用户名！");
			return;
		}
		if (!Boolean(userObj.email)) {
			message.warning("请填写正确的电子邮箱！");
			return;
		}
		if (Boolean(userObj.dateOfBirth)) {
			try {
				var transientVar = new Date(userObj.dateOfBirth);
			} catch (err) {
				message.warning("出生日期格式错误！");
				return;
			}
		}
		if (!Boolean(userObj.roleId)) {
			message.warning("请选择用户的角色！");
			return;
		}
		console.log("Saving...");
		console.log(userObj);
		var msgDlg = message.loading("保存中...");
		$.ajax({
			url: "user/update",
			type: "POST",
			data: {
				params: JSON.stringify(userObj)
			},
			success: function(data) {
				console.log(data);
				message.hide(msgDlg);
				if (data.success) {
					message.success("保存成功！");
					$("#user-mgmt-update-dlg").modal("hide");
					getList($("#user-mgmt-page .user-list-area"), 1);
				} else {
					message.danger(data.msg);
				}
			},
			error: function(error) {
				console.log("Connection error.");
				message.hide(msgDlg);
			}
		});
	}
	
	return {
		getList: getList
	}
});