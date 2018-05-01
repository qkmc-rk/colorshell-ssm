$(document).ready(function(){
	if(!window.localStorage){
		alert('your broswer is not support web storage!');
		window.close();
	}
});

function ajaxLogin(){
	//首先获取用户名和密码
	var mail = $("#mail").val();
	var password = $("#password").val();
	//alert(account+password);
	
	//发送ajax请求进行登录
	$.ajax({
		type:"post",
		url:"../admin/login",
		async:true,
		data:{
			'mail':mail,
			'password':password
		},
		//根据返回值
		success:function(datas){
			var datas = JSON.parse(datas);
			console.log(datas);
			if(datas.code == 0){
				var ls = window.localStorage;
				ls.setItem("token", datas.data);
				window.location.href='index.html';
			}else if(datas.code == -1){
				alert('登录失败');
			}
		},
		error:function(){
			alert('访问错误');
		}
	});
}