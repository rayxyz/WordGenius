/**
 * 
 */
define([], function() {
	function success(msg) {
		var dialog = bootbox.dialog({
			message: '<div class="alert alert-success" role="alert">' + msg + '</div>',
		    closeButton: false
		});
		setTimeout(function() {
			dialog.modal('hide');
		}, 2000);
	}

	function info(msg) {
		var dialog = bootbox.dialog({
			message: '<div class="alert alert-info" role="alert">' + msg + '</div>',
		    closeButton: false
		});
		setTimeout(function() {
			dialog.modal('hide');
		}, 2000);
	}
	
	function warning(msg) {
		var dialog = bootbox.dialog({
			message: '<div class="alert alert-warning" role="alert">' + msg + '</div>',
		    closeButton: false
		});
		setTimeout(function() {
			dialog.modal('hide');
		}, 2000);
	}

	function danger(msg) {
		var dialog = bootbox.dialog({
			message: '<div class="alert alert-danger" role="alert">' + msg + '</div>',
		    closeButton: false
		});
		setTimeout(function() {
			dialog.modal('hide');
		}, 2000);
	}
	
	function error(msg) {
		var dialog = bootbox.dialog({
			message: '<div class="alert alert-danger" role="alert">' + msg + '</div>',
		    closeButton: false
		});
		setTimeout(function() {
			dialog.modal('hide');
		}, 2000);
	}
	
	function loading(msg) {
		var dialog = bootbox.dialog({
			message: '<div class="text-center"><i class="loading"><img src="static/image/loader.gif" /></i> ' + msg + '</div>',
		    closeButton: false
		});
		return dialog;
	}
	
	function loading_big(msg) {
		var dialog = bootbox.dialog({
			message: '<div class="text-center"><i class="loading"><img src="static/image/loader.gif" /></i> ' + msg + '</div>',
		    closeButton: false
		});
		return dialog;
	}
	
	function hide(box) {
		box.modal("hide");
	}
	
	return {
		info: info,
		success: success,
		warning: warning,
		danger: danger,
		error: error,
		loading: loading,
		loading_big: loading_big,
		hide: hide
	}
});