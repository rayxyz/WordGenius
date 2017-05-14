
(function() {
	$(document).ready(function() {
		var activeEle = $(".nav-sidebar li").filter(".active");
		var moduleCode = "";
		if (activeEle.length > 0) {
			try {
				var splitedIdFrags = $(activeEle).attr("id").split("-");
				var moduleCode = splitedIdFrags[0];
			} catch (err) {
				console.log(err);
			}
		}
		loadMgmtPage(moduleCode);
		$(".nav-sidebar li").each(function(index, ele) {
			var splitedIdFrags = $(ele).attr("id").split("-");
			var moduleCode = splitedIdFrags[0];
			$("#" + moduleCode + "-mgmt-btn").on("click", function(evt) {
				changeBtnStyle(this);
				loadMgmtPage(moduleCode);
			});
		});
	});
	
	function changeBtnStyle(btn) {
		$(".nav-sidebar li").filter(".active").removeClass("active");
		$(btn).addClass("active");
	}
	
	function loadMgmtPage(moduleCode) {
		console.log("module code: " + moduleCode);
		if (moduleCode == "role") {
			loadRolemgmtPage();
		}
		if (moduleCode == "user") {
			loadUsermgmtPage();
		}
		if (moduleCode == "auth") {
			loadAuthmgmtPage();
		}
		if (moduleCode == "module") {
			loadModulemgmtPage();
		}
	}
	
	function loadRolemgmtPage() {
		$("#sys-index-main-area").load("role/index", null, function() {
			require(["role"], function(role) {
				role.getList($("#role-mgmt-page .role-list-area"));
			});
		});
	}
	
	function loadUsermgmtPage() {
		$("#sys-index-main-area").load("user/index", null, function() {
			require(["user"], function(user) {
				user.getList($("#user-mgmt-page .user-list-area"), 1);
			});
		});
	}
	
	function loadAuthmgmtPage() {
		$("#sys-index-main-area").load("auth/index", null, function() {
			require(["auth"], function(auth) {
				console.log("Loading auth list...");
				var firstRole = $("#auth-mgmt-role-dropdown-list .dropdown-menu li a:first");
				if (firstRole.length > 0) {
				    $("#auth-mgmt-role-dropdown-list button span:first").text($(firstRole).text());
				    $(firstRole).addClass("selected").addClass("bg-primary");
				}
				auth.getList($("#auth-mgmt-page .action-list-area"));
			});
		});
	}
	
	function loadModulemgmtPage() {
		$("#sys-index-main-area").load("module/index", null, function() {
			require(["module_"], function(module) {
				module.getList($("#module-mgmt-page .module-list-area"), 1);
			});
		});
	}
	
})();
