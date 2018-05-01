$(document).ready(function(){
	refreshIlluminationTable();
	refreshTemperatureTable();
	refreshHumidityTable();
	refreshGasTable();
	refreshPhTable();
});


function refreshIlluminationTable(){
	var ls = window.localStorage;
	//1.ajax获取数据
	$.ajax({
		type:"get",
		url:"../admin/data/illumination",
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
			var i = 0;
			for(;i<data.length;i++){
				data[i].creatime = timestampToTime(data[i].creatime);
			}
			//3.将数据放到表中
			$('#illuminationtable').bootstrapTable('load', data);
		},
		error:function(){
			alert('访问错误');
		}
	});
	
}
//温度表刷新数据
function refreshTemperatureTable(){
	var ls = window.localStorage;
	//1.ajax获取数据
	$.ajax({
		type:"get",
		url:"../admin/data/temperature",
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
			var i = 0;
			for(;i<data.length;i++){
				data[i].creatime = timestampToTime(data[i].creatime);
			}
			//3.将数据放到表中
			$('#temperaturetable').bootstrapTable('load', data);
		},
		error:function(){
			alert('访问错误');
		}
	});
	
}

//湿度表刷新数据
function refreshHumidityTable(){
	var ls = window.localStorage;
	//1.ajax获取数据
	$.ajax({
		type:"get",
		url:"../admin/data/humidity",
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
			var i = 0;
			for(;i<data.length;i++){
				data[i].creatime = timestampToTime(data[i].creatime);
			}
			//3.将数据放到表中
			$('#humiditytable').bootstrapTable('load', data);
		},
		error:function(){
			alert('访问错误');
		}
	});
	
}

//二氧化碳浓度表刷新数据
function refreshGasTable(){
	var ls = window.localStorage;
	//1.ajax获取数据
	$.ajax({
		type:"get",
		url:"../admin/data/gas",
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
			var i = 0;
			for(;i<data.length;i++){
				data[i].creatime = timestampToTime(data[i].creatime);
			}
			//3.将数据放到表中
			$('#gastable').bootstrapTable('load', data);
		},
		error:function(){
			alert('访问错误');
		}
	});
	
}

//二氧化碳浓度表刷新数据
function refreshPhTable(){
	var ls = window.localStorage;
	//1.ajax获取数据
	$.ajax({
		type:"get",
		url:"../admin/data/ph",
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
			var i = 0;
			for(;i<data.length;i++){
				data[i].creatime = timestampToTime(data[i].creatime);
			}
			//3.将数据放到表中
			$('#phtable').bootstrapTable('load', data);
		},
		error:function(){
			alert('访问错误');
		}
	});
	
}

//时间戳转时间函数
function timestampToTime(timestamp) {
    var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
    Y = date.getFullYear() + '-';
    M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
    D = date.getDate() + ' ';
    h = date.getHours() + ':';
    m = date.getMinutes() + ':';
    s = date.getSeconds();
    return Y+M+D+h+m+s;
}