$(document).ready(function(){
	refreshUserTable();
	
	//监听bootstrap table选择，好改变按钮的状态
	//监听bootstrap table选择，好改变按钮的状态
	$('#usertable').on('check.bs.table',function (row,elem) {
		openUserBtn();
	});
	$('#usertable').on('uncheck.bs.table', function (row,elem) {
        closeUserBtn();
    });
});


function refreshUserTable(){
	var ls = window.localStorage;
	//1.ajax获取数据
	$.ajax({
		type:"get",
		url:"../admin/user/all",
		async:true,
		data:{
			'token':ls.getItem("token"),
		},
		//根据返回值
		success:function(datas){
			var datas = JSON.parse(datas);
			var data = datas.data;
			console.log(data);
			//2.将数据格式进行转换
			//3.将数据放到表中
			$('#usertable').bootstrapTable('load', data);
		},
		error:function(){
			alert('访问错误');
		}
	});
}

function closeUserBtn() {
    $("button[name='deleteuser']").attr('disabled', 'disabled');
    $("button[name='updateuser']").attr('disabled', 'disabled');
}
function openUserBtn() {
    $("button[name='deleteuser']").removeAttr('disabled');
    $("button[name='updateuser']").removeAttr('disabled');
}