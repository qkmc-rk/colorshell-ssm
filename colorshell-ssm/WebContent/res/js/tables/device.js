$(document).ready(function(){
	refreshDeviceTable();
	
	//监听bootstrap table选择，好改变按钮的状态
	$('#devicetable').on('check.bs.table',function (row,elem) {
		openDeviceBtn();
	});
	$('#devicetable').on('uncheck.bs.table', function (row,elem) {
        closeDeviceBtn();
    });
});

function refreshDeviceTable(){
	var ls = window.localStorage;
	//1.ajax获取数据
	$.ajax({
		type:"get",
		url:"../admin/device/all",
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
			$('#devicetable').bootstrapTable('load', data);
		},
		error:function(){
			alert('访问错误');
		}
	});
}


function closeDeviceBtn() {
    $("button[name='deletedevice']").attr('disabled', 'disabled');
    $("button[name='updatedevice']").attr('disabled', 'disabled');
}
function openDeviceBtn() {
    $("button[name='deletedevice']").removeAttr('disabled');
    $("button[name='updatedevice']").removeAttr('disabled');
}