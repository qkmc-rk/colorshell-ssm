$(document).ready(function(){
	haslogined();
});

function haslogined(){
	
	if(window.localStorage){
		var ls = window.localStorage;
		if(ls.getItem("token") == null){
			alert('not login yet');
			window.location.href='login.html';
		}
	}else{
		alert('your broswer is not support web storage!');
		window.close();
	}
	
	
}