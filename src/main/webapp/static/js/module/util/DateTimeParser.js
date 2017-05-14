
/**
 * @author RayWang
 */

var DateTimeParser = function() {
	
};

DateTimeParser.prototype.millis2YMD = function(millis) {
	var dat = new Date(millis);
	var dateString = dat.getFullYear() + "-" 
			+ ((dat.getMonth() + 1) < 10 ? "0" + (dat.getMonth() + 1) : (dat.getMonth() + 1)) + "-" 
			+ (dat.getDate() < 10 ? "0" + dat.getDate() : dat.getDate());
	return dateString;		
};

DateTimeParser.prototype.millis2YMDHM = function(millis) {
	var dat = new Date(millis);
	var dateTimeString = this.millis2YMD(millis) + " "
			+ (dat.getHours() < 10 ? "0" + dat.getHours() : dat.getHours()) + ":"
			+ (dat.getMinutes() < 10 ? "0" + dat.getMinutes() : dat.getMinutes());
	return dateTimeString;
};

DateTimeParser.prototype.millis2YMDHMS = function(millis) {
	var dat = new Date(millis);
	var dateTimeString = this.millis2YMDHM + ":" + (dat.getSeconds() < 10 ? "0" + dat.getSeconds() : dat.getSeconds());
	return dateTimeString;
};

DateTimeParser.prototype.compare = function(start, end) {
	start = start.replace(/-|\/|\,/g, "\/");
	end = end.replace(/-|\/|\,/g, "\/");
	start = Date.parse(start);
	end = Date.parse(end);
	return start < end ? true : false;
};
