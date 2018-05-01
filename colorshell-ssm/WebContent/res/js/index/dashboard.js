$(document).ready(function(){
	//首次加载数据
	refreshTemp();
	refreshHumidity();
	refreshIllumination();
	refreshPh();
	refreshGas();
	//隔一段时间更新一次dashboard中的数据
	setInterval(() => {
		refresh();
	}, 5000);
});

function refresh(){
	refreshTemp();
	refreshHumidity();
	refreshIllumination();
	refreshPh();
	refreshGas();
}
function refreshTemp(){
	var ls = window.localStorage;
	//1.获取温度数据，从后台
	$.ajax({
		type:"get",
		url:"../admin/data/temperature",
		async:true,
		data:{
			'token':ls.getItem("token"),
			'now':'now'
		},
		//根据返回值
		success:function(datas){
			var datas = JSON.parse(datas);
			//2.将数据绑定到dashboard上面
			if(datas.code == 0){
				var data = datas.data;
				$('#temperature-dashboard-span').html(data.temperature);
				$('#temperature-dashboard').attr('data-percent',data.temperature);
				$('#temperature-dashboard').easyPieChart({animate: 1000});
			}else{
				
			}
		},
		error:function(){
			alert('temperature-dashboard 数据访问错误');
		}
	});
}

//刷新湿度
function refreshHumidity(){
	var ls = window.localStorage;
	//1.获取温度数据，从后台
	$.ajax({
		type:"get",
		url:"../admin/data/humidity",
		async:true,
		data:{
			'token':ls.getItem("token"),
			'now':'now'
		},
		//根据返回值
		success:function(datas){
			var datas = JSON.parse(datas);
			//2.将数据绑定到dashboard上面
			if(datas.code == 0){
				var data = datas.data;
				$('#humidity-dashboard-span').html(data.humidity);
				$('#humidity-dashboard').attr('data-percent',data.humidity);
				$('#humidity-dashboard').easyPieChart({animate: 1000});
			}else{
				
			}
		},
		error:function(){
			alert('humidity-dashboard 数据访问错误');
		}
	});
}

//刷新光照度
function refreshIllumination(){
	var ls = window.localStorage;
	//1.获取温度数据，从后台
	$.ajax({
		type:"get",
		url:"../admin/data/illumination",
		async:true,
		data:{
			'token':ls.getItem("token"),
			'now':'now'
		},
		//根据返回值
		success:function(datas){
			var datas = JSON.parse(datas);
			//2.将数据绑定到dashboard上面
			if(datas.code == 0){
				var data = datas.data;
				$('#illumination-dashboard-span').html(data.illumination);
				$('#illumination-dashboard').attr('data-percent',data.illumination/100.0);
				$('#illumination-dashboard').easyPieChart({animate: 1000});
			}else{
				
			}
		},
		error:function(){
			alert('illumination-dashboard 数据访问错误');
		}
	});
}

//刷新光照度
function refreshPh(){
	var ls = window.localStorage;
	//1.获取温度数据，从后台
	$.ajax({
		type:"get",
		url:"../admin/data/ph",
		async:true,
		data:{
			'token':ls.getItem("token"),
			'now':'now'
		},
		//根据返回值
		success:function(datas){
			var datas = JSON.parse(datas);
			//2.将数据绑定到dashboard上面
			if(datas.code == 0){
				var data = datas.data;
				$('#ph-dashboard-span').html(data.ph);
				$('#ph-dashboard').attr('data-percent',data.ph*10);
				$('#ph-dashboard').easyPieChart({animate: 1000});
			}else{
				
			}
		},
		error:function(){
			alert('ph-dashboard 数据访问错误');
		}
	});
}

//刷新光照度
function refreshGas(){
	var ls = window.localStorage;
	//1.获取温度数据，从后台
	$.ajax({
		type:"get",
		url:"../admin/data/gas",
		async:true,
		data:{
			'token':ls.getItem("token"),
			'now':'now'
		},
		//根据返回值
		success:function(datas){
			var datas = JSON.parse(datas);
			//2.将数据绑定到dashboard上面
			if(datas.code == 0){
				var data = datas.data;
				$('#gas-dashboard-span').html(data.gas);
				$('#gas-dashboard').attr('data-percent',data.gas);
				$('#gas-dashboard').easyPieChart({animate: 1000});
			}else{
				
			}
		},
		error:function(){
			alert('gas-dashboard 数据访问错误');
		}
	});
}