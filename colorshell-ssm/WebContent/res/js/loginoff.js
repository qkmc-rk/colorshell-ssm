function loginoff(){
	var ls = window.localStorage;
	localStorage.clear();
	window.location.href = 'login.html';
}