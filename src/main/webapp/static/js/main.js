/**
 * @requirejs configuration file
 */
requirejs.config({
    baseUrl: "static/js",
    paths: {
//    	"jquery": ["lib/jquery-3.2.0.min.js"],
    	"md5": ["lib/md5.min"],
    	"ajaxForm": ["lib/jquery.form"],
    	"message": ["module/util/message"],
        "pager": ["module/util/pager"],
    	"role": ["module/sys/role"],
        "user": ["module/sys/user"],
        "auth": ["module/sys/auth"],
        "module_": ["module/sys/module"],
        "action": ["module/sys/action"]
    }
	,
    shim: {
//	  "bootbox": ["jquery"]
    	
	}
});

//require(["bootbox"]);