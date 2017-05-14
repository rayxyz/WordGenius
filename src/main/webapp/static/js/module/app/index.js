
(function() {
	$(document).ready(function() {
		console.log("Loading the index page...");
	});
	$(document).on("click", "#signup-gender-dropdown-list .dropdown-menu li", function(){
	    $("#signup-gender-dropdown-list button span:first").text($($(this).find("a")).text());
	    $("#signup-gender-dropdown-list .dropdown-menu li a").removeClass("selected").removeClass("bg-primary");
	    $($(this).find('a')).addClass("selected").addClass("bg-primary");
    });
	$(document).on("click", "#login-btn", function(evt) {
		var dlg = $("#login-dlg");
		if (dlg.length > 0) {
			$(dlg).modal({keyboard: false});
			return;
		}
		$(document).load("login-view", function(data) {
			$(data).modal({keyboard: false});
		});
	});
	$(document).on("click", "#login-dlg .login", function(evt) {
		login();
	});
	$(document).on("click", "#signup-btn", function(evt) {
		var dlg = $("#signup-dlg");
		if (dlg.length > 0) {
			$(dlg).modal({keyboard: false});
			return;
		}
		$(document).load("signup-view", function(data) {
			$(data).modal({keyboard: false});
		});
	});
	$(document).on("click", "#signup-dlg .modal-footer button:last", function(evt) {
		signup();
	});
	$(document).on("click", "#logout-btn", function(evt) {
		logout();
	});
	
	function login() {
		require(["message"], function(message) {
			var paramObj = new Object();
			paramObj.email = $("#login-dlg-account").val();
			paramObj.password = $("#login-dlg-password").val();
			if (!Boolean(paramObj.email)) {
				message.warning("请填写邮箱！");
				return;
			}
			if (!Boolean(paramObj.password)) {
				message.warning("请填写密码！");
				return;
			}
			var msgDlg = message.loading("登录中...");
			$.ajax({
				url: "login",
				type: "POST",
				data: {
					params: JSON.stringify(paramObj)
				},
				success: function(data) {
					console.log(data);
					message.hide(msgDlg);
					if (data.success) {
						$("#signup-dlg").modal("hide");
						window.location.reload();
					} else {
						message.error(data.msg);
					}
				},
				error: function(error) {
					message.error("登录出错！");
					message.hide(msgDlg);
				}
			});
		});
	}
	
	function signup() {
		require(["message"], function(message) {
			var userObj = new Object();
			userObj.name = $("#signup-name").val();
			userObj.email = $("#signup-email").val();
			userObj.phone = $("#signup-mobilephone").val();
			userObj.dateOfBirth = $("#signup-birthdate").val();
			userObj.gender = $("#signup-gender-dropdown-list .dropdown-menu .selected").attr("user-gender");
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
			console.log("Saving...");
			console.log(userObj);
			var msgDlg = message.loading("保存中...");
			$.ajax({
				url: "signup",
				type: "POST",
				data: {
					params: JSON.stringify(userObj)
				},
				success: function(data) {
					console.log(data);
					message.hide(msgDlg);
					if (data.success) {
						message.success("注册成功！");
						$("#signup-dlg").modal("hide");
					} else {
						message.error(data.msg);
					}
				},
				error: function(error) {
					message.error("保存出错！");
					message.hide(msgDlg);
				}
			});
		});
	}
	
	function logout() {
		bootbox.confirm({
		    message: "是否退出？",
		    buttons: {
		        confirm: {
		            label: '是',
		            className: 'btn-danger'
		        },
		        cancel: {
		            label: '否',
		            className: 'btn-primary'
		        }
		    },
		    callback: function (result) {
		    	if (result) {
		    		require(["message"], function(message) {
		    			$.ajax({
		    				url: "logout",
		    				type: "POST",
		    				success: function(data) {
		    					window.location.href = "";
		    				},
		    				error: function(err) {
		    					message.error(err);
		    				}
		    			});
		    		});
		    	}
		    }
		});
	}
	
})();
