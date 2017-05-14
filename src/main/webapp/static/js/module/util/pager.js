/**
 * 
 */
define(function() {
	var pager = new Object();
	pager.pageNo = 1;
	pager.pageSize = 3;
	pager.begin = 0;
	pager.end = 0;
	pager.rows = 0;
	pager.pages = 0;
	return {
		getPager: function() {
			return pager;
		}
	}
});