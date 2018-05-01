function getIllumination(){
	var illuminations;
	var ls = window.localStorage;
	//1.ajax获取数据
	$.ajax({
		type:"get",
		url:"../admin/data/illumination",
		async:false,
		data:{
			'token':ls.getItem("token"),
			'from':0,
			'to':100
		},
		//根据返回值
		success:function(datas){
			var datas = JSON.parse(datas);
			var data = datas.data;
			var i = 0;
			for(;i<data.length;i++){
				data[i].creatime = timestampToTime(data[i].creatime);
			}
			illuminations = data;
		},
		error:function(){
			alert('访问错误');
		}
	});
	return illuminations;
}

function getTemperature(){
	var temperatures;
	var ls = window.localStorage;
	//1.ajax获取数据
	$.ajax({
		type:"get",
		url:"../admin/data/temperature",
		async:false,
		data:{
			'token':ls.getItem("token"),
			'from':0,
			'to':100
		},
		//根据返回值
		success:function(datas){
			var datas = JSON.parse(datas);
			var data = datas.data;
			var i = 0;
			for(;i<data.length;i++){
				data[i].creatime = timestampToTime(data[i].creatime);
			}
			temperatures = data;
		},
		error:function(){
			alert('访问错误');
		}
	});
	return temperatures;
}

function getHumidity(){
	var humidities;
	var ls = window.localStorage;
	//1.ajax获取数据
	$.ajax({
		type:"get",
		url:"../admin/data/humidity",
		async:false,
		data:{
			'token':ls.getItem("token"),
			'from':0,
			'to':100
		},
		//根据返回值
		success:function(datas){
			var datas = JSON.parse(datas);
			var data = datas.data;
			var i = 0;
			for(;i<data.length;i++){
				data[i].creatime = timestampToTime(data[i].creatime);
			}
			humidities = data;
		},
		error:function(){
			alert('访问错误');
		}
	});
	return humidities;
}

function getGas(){
	var gases;
	var ls = window.localStorage;
	//1.ajax获取数据
	$.ajax({
		type:"get",
		url:"../admin/data/gas",
		async:false,
		data:{
			'token':ls.getItem("token"),
			'from':0,
			'to':100
		},
		//根据返回值
		success:function(datas){
			var datas = JSON.parse(datas);
			var data = datas.data;
			var i = 0;
			for(;i<data.length;i++){
				data[i].creatime = timestampToTime(data[i].creatime);
			}
			gases = data;
		},
		error:function(){
			alert('访问错误');
		}
	});
	return gases;
}

function getPh(){
	var phs;
	var ls = window.localStorage;
	//1.ajax获取数据
	$.ajax({
		type:"get",
		url:"../admin/data/ph",
		async:false,
		data:{
			'token':ls.getItem("token"),
			'from':0,
			'to':100
		},
		//根据返回值
		success:function(datas){
			var datas = JSON.parse(datas);
			var data = datas.data;
			var i = 0;
			for(;i<data.length;i++){
				data[i].creatime = timestampToTime(data[i].creatime);
			}
			phs = data;
		},
		error:function(){
			alert('访问错误');
		}
	});
	return phs;
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