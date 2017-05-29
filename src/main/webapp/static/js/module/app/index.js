
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
			setRememberVals();
			return;
		}
		$(document).load("login-view", function(data) {
			var obj = $(data).modal("show");
			$(obj).on("shown.bs.modal", function() {
				var rememberMeCheck = window.localStorage.getItem("wordgen_rememberme_check");
				if (rememberMeCheck) {
					console.log("wordgen_account: " + window.localStorage.getItem("wordgen_account"));
					$("#login-dlg-account").val(window.localStorage.getItem("wordgen_account"));
					console.log("wordgen_password: " + window.localStorage.getItem("wordgen_password"));
					$("#login-dlg-password").val(window.localStorage.getItem("wordgen_password"));
					$("#login-dlg-rememberme").prop("checked", true);
				}
			});
		});
	});
	$(document).on("click", "#login-dlg .login", function(evt) {
		login();
	});
	$(document).on("click", "#signup-btn", function(evt) {
		var dlg = $("#signup-dlg");
		if (dlg.length > 0) {
			$(dlg).modal({keyboard: false});
			uploadAvartar();
			return;
		}
		$(document).load("signup-view", function(data) {
			var obj = $(data).modal({keyboard: false});
			$(obj).on("shown.bs.modal", function(evt) {
				uploadAvartar();
			});
		});
	});
	$(document).on("click", "#signup-dlg .modal-footer button:last", function(evt) {
		signup();
	});
	$(document).on("click", "#logout-btn", function(evt) {
		logout();
	});
	
	function login() {
		require(["message", "md5"], function(message, md5) {
			var paramObj = new Object();
			paramObj.email = $("#login-dlg-account").val();
			paramObj.password = $("#login-dlg-password").val();
			var passwordUnhashed = $("#login-dlg-password").val();
			if (!Boolean(paramObj.email)) {
				message.warning("请填写邮箱！");
				return;
			}
			if (!Boolean(paramObj.password)) {
				message.warning("请填写密码！");
				return;
			}
			paramObj.password = md5(paramObj.password);
			console.log("After md5ed: " + paramObj.password)
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
						var rememberMeCheck = $("#login-dlg-rememberme").prop("checked");
						if (rememberMeCheck) {
							window.localStorage.setItem("wordgen_rememberme_check", true);
							window.localStorage.setItem("wordgen_account", paramObj.email);
							window.localStorage.setItem("wordgen_password", passwordUnhashed);
						} else {
							window.localStorage.removeItem("wordgen_rememberme_check");
							window.localStorage.removeItem("wordgen_account");
							window.localStorage.removeItem("wordgen_password");
						}
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
		require(["message", "md5"], function(message, md5) {
			var userObj = new Object();
			userObj.name = $("#signup-name").val().trim();
			userObj.email = $("#signup-email").val().trim();
			userObj.phone = $("#signup-mobilephone").val().trim();
			userObj.dateOfBirth = $("#signup-birthdate").val().trim();
			userObj.gender = $("#signup-gender-dropdown-list .dropdown-menu .selected").attr("user-gender");
			userObj.password = $("#signup-password").val();
			userObj.password_again = $("#signup-password-again").val();
			userObj.avartarId = $("#signup-avartar-area img").attr("avartar-id");
			if (!Boolean(userObj.name)) {
				message.warning("请填写用户名！");
				return;
			}
			if (!Boolean(userObj.email)) {
				message.warning("请填写正确的电子邮箱！");
				return;
			}
			if (!Boolean(userObj.password)) {
				message.warning("登录密码不能为空！")
				return;
			}
			if (userObj.password != userObj.password_again) {
				message.warning("两次密码输入不一致！");
				return;
			}
			delete userObj.password_again;
		
			userObj.password = md5(userObj.password);
			userObj.desc = "用户注册创建";
			
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
					message.error("注册出错！");
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
	
	function uploadAvartar() {
		var progressBarObj = $("#avartar-upload-progressbar");
		$("#signup-avartar-form").ajaxForm({
	        beforeSend: function() {
	            console.log("Uploading...")
	    		progressBarObj.show();
	        },
	        uploadProgress: function(event, position, total, percentComplete) {
	            progressBarObj.attr("aria-valuenow", percentComplete);
	            var percentVal = percentComplete + '%';
	            progressBarObj.find("div[role='progressbar']").css({
	            	width: percentVal
	            });
	            progressBarObj.find("span").text(percentVal);
	            console.log("Upload " + percentVal + " complete");
	        },
	        complete: function(xhr) {
	        	console.log(xhr.responseText);
	    		progressBarObj.css("display", "none");
	    		var obj = JSON.parse(xhr.responseText);
	    		console.log(obj);
	    		if (obj.success) {
	    			var avartarObj = $("#signup-avartar-area");
	    			avartarObj.find("img").attr("src", window.sessionStorage.getItem("fileserver") + "get" + "/" + obj.data.id);
	    			avartarObj.find("img").attr("avartar-id", obj.data.id);
	    			avartarObj.find("img").attr("alt", obj.data.name);
	    			avartarObj.show();
	    		}
	        }
	    });
	}
	
})();
