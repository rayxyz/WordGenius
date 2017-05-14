/**
 * @requirejs configuration file
 */
requirejs.config({
    baseUrl: "static/js",
    paths: {
//    	"jquery": ["lib/jquery-3.2.0.min.js"],
    	"message": ["module/util/message"],
        "pager": ["module/util/pager"],
    	"role": ["module/sys/role"],
        "user": ["module/sys/user"],
        "auth": ["module/sys/auth"],
        "module_": ["module/sys/module"],
        "action": ["module/sys/action"]
    }
//	,
//    shim: {
//	  "bootbox": ["jquery"]
//	}
});

//require(["bootbox"]);