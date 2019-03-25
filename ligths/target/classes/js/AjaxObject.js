function getAjaxHandler() {

	var retval;
	retval=null;
	if (window.XMLHttpRequest) {
		retval = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		retval = new ActiveXObject("Microsoft.XMLHTTP");
	}

	
	return retval;
}