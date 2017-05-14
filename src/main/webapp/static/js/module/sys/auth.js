/**
 * Authority management module.
 */

define(["message"], function(message) {
	var queryObj = new Object();
	var countAllActions = 0; // Count of actions queried.

	$(document).on("click", "#auth-mgmt-role-dropdown-list .dropdown-menu li", function(){
	    $("#auth-mgmt-role-dropdown-list button span:first").text($($(this).find("a")).text());
	    $("#auth-mgmt-role-dropdown-list .dropdown-menu li a").removeClass("selected").removeClass("bg-primary");
	    $($(this).find('a')).addClass("selected").addClass("bg-primary");
	    $("#auth-mgmt-set-btn").attr("disabled", "disabled");
	    getList($("#auth-mgmt-page .action-list-area"));
    });
	
	$(document).on("click", "#auth-mgmt-module-dropdown-list .dropdown-menu li", function(){
	    $("#auth-mgmt-module-dropdown-list button span:first").text($($(this).find("a")).text());
	    $("#auth-mgmt-module-dropdown-list .dropdown-menu li a").removeClass("selected").removeClass("bg-primary");
	    $($(this).find('a')).addClass("selected").addClass("bg-primary");
	    $("#auth-mgmt-set-btn").attr("disabled", "disabled");
	    getList($("#auth-mgmt-page .action-list-area"));
    });
	
	$(document).on("click", "#auth-mgmt-page .action-list-area input[type='checkbox']", function(evt) {
		$("#auth-mgmt-set-btn").removeAttr("disabled");
		if ($(this).hasClass("check-all")) {
			if ($(this).prop("checked")) {
				$("#auth-mgmt-page .action-list-area input[type='checkbox']").prop("checked", true);
			} else {
				$("#auth-mgmt-page .action-list-area input[type='checkbox']").prop("checked", false);
			}
		} else {
			if ($(this).prop("checked")) {
				if (getCountCheckedbox() == countAllActions) {
					$("#auth-mgmt-page .action-list-area .check-all").prop("checked", true);
				}
			} else {
				$("#auth-mgmt-page .action-list-area .check-all").prop("checked", false);
			}
		}
	});
	
	$(document).on("click", "#auth-mgmt-set-btn", function(evt) {
		saveAuthes();
	});
	
	function getCountCheckedbox() {
		var countChecked = 0;
		var inputs = $("#auth-mgmt-page .action-list-area tbody input");
		for (var i = 0; i < inputs.length; i++) {
			if ($(inputs[i]).prop("checked")) {
				countChecked++;
			}
		}
		return countChecked;
	}
	
	function getCheckedCheckboxes() {
		var checkedboxes = new Array();
		var inputs = $("#auth-mgmt-page .action-list-area tbody input");
		for (var i = 0; i < inputs.length; i++) {
			if ($(inputs[i]).prop("checked")) {
				checkedboxes[checkedboxes.length] = inputs[i];
			}
		}
		return checkedboxes
	}
	
	function getList(ele) {
		var queryObj = new Object();
		var roleSelEle = $("#auth-mgmt-role-dropdown-list .dropdown-menu li .selected");
		if (roleSelEle.length > 0) {
			queryObj.roleId = $(roleSelEle).attr("role-id");
		}
		var moduleSelEle = $("#auth-mgmt-module-dropdown-list .dropdown-menu li .selected");
		if (moduleSelEle.length > 0) {
			queryObj.moduleId = $(moduleSelEle).attr("module-id");
			if (queryObj.moduleId == "-1") {
				queryObj.moduleId = null;
			}
		}
		$.ajax({
			url: "action/getListAuth",
			type: "GET",
			data: {
				queryParams: JSON.stringify(queryObj)
			},
			success: function(data) {
				$(ele).html(data);
				countAllActions = $("#auth-mgmt-page .action-list-area tbody input").length;
			},
			error: function(error) {
				console.log("Connection error.");
			}
		});
	}
	
	function saveAuthes() {
		var saveObj = new Object();
		var actionIds = new Array();
		var roleSelEle = $("#auth-mgmt-role-dropdown-list .dropdown-menu li .selected");
		if (roleSelEle.length > 0) {
			saveObj.roleId = $(roleSelEle).attr("role-id");
		}
		var checkedCheckboxes = getCheckedCheckboxes();
//		if (checkedCheckboxes.length == 0) {
//			message.warning("请选择功能权限！");
//			return;
//		}
		for (var i = 0; i < checkedCheckboxes.length; i++) {
			actionIds[actionIds.length] = $(checkedCheckboxes[i]).attr("action-id");
		}
		saveObj.actionIds = actionIds;
		console.log("The save params: ");
		console.log(saveObj);
		var msgBox = message.loading("保存中...");
		$.ajax({
			url: "auth/save",
			type: "POST",
			data: {
				params: JSON.stringify(saveObj)
			},
			success: function(data) {
				message.hide(msgBox);
				message.success("设置成功！");
			},
			error: function(error) {
				message.hide(msgBox);
				console.log("Connection error.");
			}
		});
	}
	
	return {
		getList: getList
	}
});